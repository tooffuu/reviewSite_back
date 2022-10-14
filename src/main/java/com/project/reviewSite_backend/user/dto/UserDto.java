package com.project.reviewSite_backend.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;

    private String nickname;

    private String userid;

    private String password1;

    private String password2;

    private String email;
}
