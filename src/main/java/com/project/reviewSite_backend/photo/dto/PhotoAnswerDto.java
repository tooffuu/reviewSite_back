package com.project.reviewSite_backend.photo.dto;

import com.project.reviewSite_backend.photo.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoAnswerDto {
    private String imgUrl;

    public PhotoAnswerDto(Photo photo) {
        this.imgUrl = photo.getImgUrl();
    }
}
