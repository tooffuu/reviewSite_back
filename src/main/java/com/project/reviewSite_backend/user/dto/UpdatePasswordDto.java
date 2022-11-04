package com.project.reviewSite_backend.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePasswordDto {

    private String password1;
    private String password2;

//    @Builder
//    public UpdatePasswordDto(String password1, String password2) {
//        this.password1 = password1;
//        this.password2 = password2;
//    }

}
