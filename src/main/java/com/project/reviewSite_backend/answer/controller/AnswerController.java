package com.project.reviewSite_backend.answer.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.project.reviewSite_backend.answer.AWS.AwsService;
import com.project.reviewSite_backend.answer.dao.AnswerRepository;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.answer.dto.CreateAnswerForm;
import com.project.reviewSite_backend.answer.dto.StarcountDto;
import com.project.reviewSite_backend.answer.service.AnswerService;
import com.project.reviewSite_backend.photo.service.PhotoService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;
    private final UserService userService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    private final PhotoService photoService;

    private final AwsService awsService;

    //------------------------------------------------------------------------------------
    //리뷰 생성 & 이미지 aws업로드 & URL 저장 컨트롤러
    @PostMapping("/answer/create/post")
    public void starIn(@RequestParam(value = "files", required = false) List<MultipartFile> files, @RequestParam("userId") Long userId, @Valid CreateAnswerForm createAnswerForm) throws IOException {
        User user = userService.findUser(userId);
        
        // 게시물 작성 후 db 저장 로직
        Answer answer = answerService.createAnswer(createAnswerForm, user);
        if (files == null) return;
        files.stream()
                .forEach(file -> {
                    try {
                        // s3 bucket 업로드 로직
                        String imgUrl = awsService.sendFileToS3Bucket(file);
                        // s3 bucket 업로드 후 imgUrl db 저장 로직
                        photoService.photo(imgUrl, answer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

    //------------------------------------------------------------------------------------
    //백엔드 데이터 전송컨트롤러
    @GetMapping("/detail/get")
    public List<Answer> getAllContent() {
        return answerRepository.findAll();
    }

    //------------------------------------------------------------------------------------
    // 겟데이터 리뷰코멘트 가져오기
    @GetMapping("/get")
    public List<AnswerVo> getByDetailId(@RequestParam("detailId") Long detailId) {
        List<AnswerVo> answerVoList = answerService.answers(detailId);

        return answerVoList;
    }

    //-------------------------------------------------------------------------------------
    //-리뷰 삭제 로직
    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id, Answer answer) {

         answerService.deleteArticle(id);
    }

    //-------------------------------------------------------------------------------------
    //디테일 아이디로 데이터값 불러오는 컨트롤러
    @GetMapping("/staravg")
    public StarcountDto staravg(@RequestParam("detailId") Long detailId) {
        return this.answerService.staravg(detailId);
    }

    //-------------------------------------------------------------------------------------
    //리뷰 업데이트 컨트롤러
    @PatchMapping("/update/content")
    public boolean update(@RequestBody CreateAnswerForm createAnswerForm) {
//        System.out.println(answerVo.getId());
        return this.answerService.updateContent(createAnswerForm);
    }

    // 현재 유저의 댓글 작성 목록 가져오기
    @GetMapping("/comment/user")
    public List<AnswerVo> findCommentByUser(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<AnswerVo> answerVoList = answerService.findCommentByUserId(user);

        return answerVoList;
    }
}