package com.project.reviewSite_backend.detail;


import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
//    @GetMapping("/get")
//    public List<Detail> get (){
//        return this.detailService.findAll();
//
//    }
