package com.project.reviewSite_backend.profile.dto;

import com.project.reviewSite_backend.profile.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
    private Long id;

    private String imgUrl;
    public ProfileDto(Profile profile){
        this.id = profile.getId();
        this.imgUrl = profile.getImgUrl();
    }

}
