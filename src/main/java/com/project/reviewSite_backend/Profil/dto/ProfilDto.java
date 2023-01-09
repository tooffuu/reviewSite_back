package com.project.reviewSite_backend.Profil.dto;

import com.project.reviewSite_backend.Profil.domain.Profil;
import com.project.reviewSite_backend.photo.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfilDto {
    private Long id;

    private String imgUrl;
    public ProfilDto(Profil profil){
        this.id = profil.getId();
        this.imgUrl = profil.getImgUrl();


    }


}
