package com.project.reviewSite_backend.photo.controller;

import com.project.reviewSite_backend.answer.AWS.AwsService;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import com.project.reviewSite_backend.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PhotoController {

    private AwsService awsService;

    private  final PhotoService photoService;

    @PostMapping("/answer/create/postimg")
    public PhotoDto createImageUrl(@RequestParam("file") MultipartFile file) throws IOException {

        String imgUrl = awsService.sendFileToS3Bucket(file);
        return photoService.photo(imgUrl, null);
    }

    //---------------------------------------------------------------------------------------------
    //이미지 디테일ID로 불러오기
    @GetMapping("/answer/image")
    public List<PhotoDto> findHeartByUser(@RequestParam("detailId") Long detailId) {
        List<PhotoDto> photoDto = photoService.findBypostDitailId(detailId);
        return photoDto;
    }
}
