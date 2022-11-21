package com.project.reviewSite_backend.heart.controller;

import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart")
    public String heart(@RequestBody @Valid Heart heart) {

        heartService.clickHeart(heart);

        return "좋아요 눌림";
    }

    @GetMapping("/heart/{userid}/{postid}")
    public ResponseEntity<Boolean> checkClickHeart(@PathVariable String userid, @PathVariable String postid) {
        return ResponseEntity.ok(heartService.checkClickHeartDuplicate(userid, postid));
    }

}
