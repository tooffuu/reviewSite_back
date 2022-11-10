package com.project.reviewSite_backend.answer;


import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.detail.DetailDto;
import com.project.reviewSite_backend.detail.DetailRepository;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {

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
//    @PostMapping("/detail/star")
//    public Answer starin(@RequestBody AnswerVo answerVo,@RequestBody User user) {
//
//        return this.answerService.starin(answerVo, user);
//
//    }
//    @PostMapping("/detail/content")
//    public Answer contentin (@RequestBody AnswerVo answerVo){
//
//    }
}