package com.project.reviewSite_backend.detail;

import com.project.reviewSite_backend.answer.Answer;
import com.project.reviewSite_backend.answer.AnswerVo;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DetailController {

    private final DetailService detailService;


    @PostMapping("/detail")
    public Detail id(@RequestBody DetailVo detailVo) {
//        System.out.println(detail.getDetail_id());
//      this.detailService.create(detailDto);


        return this.detailService.create(detailVo);
    }
//    @GetMapping("/get")
//    public List<Detail> get (){
//        return this.detailService.findAll();
//
//    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/like/{id}")
//    public String detailLike(User user, @PathVariable("id") Integer id) {
//        Detail detail = this.detailService.getDetail(id);
//        this.detailService.likes(detail, user);
//        return "";
//    }

//    @PostMapping("user/{detail_id}")
//    public String detailgetid(Detail detail,@PathVariable("detail_id") Integer detail_id){
//        Detail detail1 = this.detailService.getDetail(detail_id);
//        this.detailService.getDetail_id(detail_id);
//        return "";
//    }

    @PostMapping("/detail/post")
    public Detail getid(@RequestBody DetailVo detailVo) {

        return this.detailService.getDetail_id(detailVo);

    }

}
