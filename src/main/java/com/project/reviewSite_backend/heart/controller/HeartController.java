package com.project.reviewSite_backend.heart.controller;

import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart")
    public String heart(@RequestBody @Valid HeartDto heartDto) {

        heartService.clickHeart(heartDto);

        System.out.println("좋아요 누른 아이디 : " + heartDto.getUserid());
        System.out.println("좋아요 눌린 게시글 아이디 : " + heartDto.getPostid());

        return "좋아요 눌림";
    }

    @GetMapping("/heart/{userid}/{postid}")
    public ResponseEntity<Boolean> checkClickHeart(@PathVariable String userid, @PathVariable String postid) {
        return ResponseEntity.ok(heartService.checkClickHeartDuplicate(userid, postid));
    }

    @DeleteMapping("/heart/delete/{userid}/{postid}")
    public HeartDto unHeart(@PathVariable String userid, @PathVariable String postid, HeartDto heartDto) {
        HeartDto unHeart = heartService.deleteByUseridAndPostid(userid, postid);

        return unHeart;
    }

}
