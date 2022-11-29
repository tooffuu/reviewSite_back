package com.project.reviewSite_backend.answer;


import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.detail.DetailRepository;
import com.project.reviewSite_backend.detail.DetailService;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {

    private final DetailService detailService;
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

//    @PostMapping("/create/{id}")
//    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
//        Detail detail = this.detailRepository.get(id);
    // TODO: 답변을 저장한다.
//        return String.format("redirect:/question/detail/%s", id);
//    }

    //    @PostMapping("/detail/idpost")
//    public Answer postid(@RequestBody AnswerVo answerVo){
//
//            return this.answerService.idpost(answerVo);
//
//    }
    @PostMapping("/answer/create/post")
    public Answer starin(@RequestBody AnswerVo answerVo) {

        return this.answerService.starin(answerVo);

    }

    @GetMapping("/detail/get")
    public List<Answer> getAllcontent() {
        return answerRepository.findAll();

    }
//    @PostMapping("/answer/create/{id}")
//    public Detail createAnswer(Model model, @PathVariable("id") Integer id, @RequestBody AnswerVo answerVo) {
//        Detail detail = this.detailService.getDetail(id);
//        this.answerService.create(detail,answerVo);
//        return detail;
//    }

    //-------------------------------------------------------------------------------------
    // 겟데이터 리뷰코멘트 가져오기
    @GetMapping("/get")
    public List<Answer> getdetailid(@RequestParam("detailId") Long detailId) {
        return this.answerService.answers(detailId);
    }
    //-------------------------------------------------------------------------------------
//-리뷰 삭제 로직

    @DeleteMapping("/delete/{id}")
    public Answer deleteAnswer(@PathVariable Long id, Answer answer) {
        Answer deleteanswer = answerService.deleteById(id);

        return deleteanswer;
    }

    @GetMapping("/staravg")
    public StarcountDto staravg(@RequestParam("detailId") Long detailId) {
        return this.answerService.staravg(detailId);

    }

    @PatchMapping("/update/content")
    public boolean update(@RequestBody AnswerVo answerVo) {
        System.out.println(answerVo.getId());
        return this.answerService.updateContent(answerVo);
    }


}