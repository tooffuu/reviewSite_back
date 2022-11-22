package com.project.reviewSite_backend.heart.dto;

import com.project.reviewSite_backend.heart.domain.Heart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeartDto {
    private String userid;

    private String postid;

    public HeartDto(Heart heart) {
        userid = heart.getUserid();
        postid = heart.getPostid();
    }

}
