package com.project.reviewSite_backend.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AnswerController {
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    //------------------------------------------------------------------------------------
    //리뷰 생성 컨트롤러
    @PostMapping("/answer/create/post")
    public Answer starin(@RequestBody AnswerVo answerVo) {
        return this.answerService.starin(answerVo);
    }

    //------------------------------------------------------------------------------------
    //백엔드 데이터 전송컨트롤러
    @GetMapping("/detail/get")
    public List<Answer> getAllcontent() {
        return answerRepository.findAll();
    }

    //------------------------------------------------------------------------------------
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

    //-------------------------------------------------------------------------------------
    //디테일 아이디로 데이터값 불러오는 컨트롤러
    @GetMapping("/sartavg")
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


}