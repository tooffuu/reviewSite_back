package com.project.reviewSite_backend.photo.dto;

import com.project.reviewSite_backend.photo.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoUrlDto {

    private Long detailId;

    private String imgUrl;

    public PhotoUrlDto(Photo photo) {
        this.detailId = photo.getDetailId();
        this.imgUrl = photo.getImgUrl();
    }


}
