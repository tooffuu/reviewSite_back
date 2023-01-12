package com.project.reviewSite_backend.heart.dto;

import com.project.reviewSite_backend.heart.domain.Heart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeartDto {
    private String postid;

    private String postName;

//    private List<PhotoUrlDto> photos;

    public HeartDto (Heart heart) {
        this.postid = heart.getPostid();
        this.postName = heart.getPostName();

//        List<PhotoUrlDto> photoList = heart.getPhotos()
//                .stream()
//                .map((photo -> {
//                    PhotoUrlDto photoUrlDto = new PhotoUrlDto(photo);
//
//                    return photoUrlDto;
//                }))
//                .collect(Collectors.toList());
//
//        this.photos = photoList;
    }
}
