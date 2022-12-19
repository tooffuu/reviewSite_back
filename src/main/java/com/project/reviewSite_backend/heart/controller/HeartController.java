package com.project.reviewSite_backend.heart.controller;

import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.heart.service.HeartService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HeartController {
    private final HeartService heartService;
    private final UserService userService;

    @PostMapping("/heart")
    public String heart(@RequestBody @Valid HeartDto heartDto, @RequestParam("userId") Long userId) {

        User user = userService.findUser(userId);

        heartService.clickHeart(heartDto, user);

        return "좋아요 눌림";
    }

    // 좋아요 정보 가져오기
    @GetMapping("/heart/{postid}")
    public Boolean checkClickHeart(@PathVariable String postid, @RequestParam("userId") Long userId) {

        User user = userService.findUser(userId);

        return heartService.checkClickHeartDuplicate(postid, user);
    }

    @GetMapping("/heart/user")
    public List<HeartDto> findHeartByUser(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<HeartDto> heartDto = heartService.findPostByUserId(user);

        return heartDto;
    }
}
