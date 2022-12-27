package com.project.reviewSite_backend.photo.dto;


import com.project.reviewSite_backend.photo.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {

    private Long id;

    private String imgUrl;

    private Long detailId;

    public PhotoDto(Photo photo){
        this.id = photo.getId();
        this.imgUrl = photo.getImgUrl();
        this.detailId = photo.getDetailId();

    }
}
