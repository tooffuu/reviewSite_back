package com.project.reviewSite_backend.heart.controller;

import com.project.reviewSite_backend.detail.domain.Detail;
import com.project.reviewSite_backend.detail.service.DetailService;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.heart.service.HeartService;
import com.project.reviewSite_backend.photo.dao.PhotoRepository;
import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import com.project.reviewSite_backend.photo.service.PhotoService;
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

    private final PhotoService photoService;

    private final DetailService detailService;
    private final PhotoRepository photoRepository;
//    @PostMapping("/heart")
//    public String heart(@RequestBody @Valid HeartDto heartDto, @RequestParam("userId") Long userId, String postId) {
//
//        User user = userService.findUser(userId);
//        Detail detail = detailService.findDetail(postId);
//
//        heartService.clickHeart(heartDto, user, detail);
//
//        return "좋아요 눌림";
//    }

    @PostMapping("/heart")
    public String heart(@RequestBody @Valid HeartDto heartDto, @RequestParam("userId") Long userId,@RequestParam("detailId")Long detailId) {

        try{
            PhotoDto photoDto = this.photoRepository.findByDetailId(detailId).get(0);

            User user = userService.findUser(userId);

            heartService.clickHeart(heartDto, user , photoDto);
        }catch (IndexOutOfBoundsException indexOutOfBoundsException){
            User user = userService.findUser(userId);
            heartService.clickHeart(heartDto, user , null);
        }




        return "좋아요 눌림";
    }



    // detail 페이지의 좋아요 유무 가져오기
    @GetMapping("/heart/{postid}")
    public Boolean checkClickHeart(@PathVariable String postid, @RequestParam("userId") Long userId) {

        User user = userService.findUser(userId);

        return heartService.checkClickHeartDuplicate(postid, user);
    }

    // 현재 유저의 찜 목록 가져오기
    @GetMapping("/heart/user")
    public List<HeartDto> findHeartByUser(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<HeartDto> heartDto = heartService.findPostByUserId(user);

        return heartDto;
    }

    // 다른 유저의 찜 목록 가져오기
@GetMapping("heart/nickname")
public List<HeartDto> findYourHeartByNickname(@RequestParam("nickname")String nickname){
        User user = userService.findnickname(nickname);

        List<HeartDto> heartDtos = heartService.findPostByUserId(user);

        return heartDtos;

}


}
