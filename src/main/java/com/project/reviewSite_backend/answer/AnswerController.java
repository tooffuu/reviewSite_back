package com.project.reviewSite_backend.answer;


import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {

    private  final  AnswerRepository answerRepository;
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
    @PostMapping("/detail/star")
    public Answer starin(@RequestBody AnswerVo answerVo) {

        return this.answerService.starin(answerVo);

    }
    @GetMapping("/detail/get")
    public List<Answer> getAllcontent(){
        return answerRepository.findAll();

    }

}