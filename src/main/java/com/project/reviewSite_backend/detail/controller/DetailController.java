package com.project.reviewSite_backend.detail.controller;

import com.project.reviewSite_backend.detail.dto.DetailDto;
import com.project.reviewSite_backend.detail.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DetailController {

    private final DetailService detailService;

    @PostMapping("/getDetail")
    public void getPost(@RequestBody DetailDto detailDto) {

        detailService.getPost(detailDto);

    }

}
