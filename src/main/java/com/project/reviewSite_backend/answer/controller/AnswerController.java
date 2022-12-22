package com.project.reviewSite_backend.answer.controller;

import com.project.reviewSite_backend.answer.dao.AnswerRepository;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.answer.dto.StarcountDto;
import com.project.reviewSite_backend.answer.service.AnswerService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;
    private final UserService userService;

    //------------------------------------------------------------------------------------
    //리뷰 생성 컨트롤러
    @PostMapping("/answer/create/post")
    public AnswerVo starIn(@RequestParam("userId") Long userId, @RequestBody AnswerVo answerVo) {

        User user = userService.findUser(userId);
        AnswerVo answerVo1 = answerService.starin(answerVo, user);
        return answerVo1;
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
    public Answer deleteAnswer(@PathVariable Long id, Answer answer) {
        Answer deleteanswer = answerService.deleteById(id);
        return deleteanswer;
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
    public boolean update(@RequestBody AnswerVo answerVo) {
        System.out.println(answerVo.getId());
        return this.answerService.updateContent(answerVo);
    }

    // 현재 유저의 댓글 작성 목록 가져오기
    @GetMapping("/comment/user")
    public List<AnswerVo> findCommentByUser(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<AnswerVo> answerVoList = answerService.findCommentByUserId(user);

        return answerVoList;
    }
}