package com.project.reviewSite_backend.detail.controller;

import com.project.reviewSite_backend.detail.dto.HeartDto;
import com.project.reviewSite_backend.detail.service.HeartService;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart")
    public String clickHeart(@RequestBody @Valid HeartDto heartDto, User user) {
        heartService.heart(heartDto, user);

        return "좋아요 눌림";
    }

}
