package com.project.reviewSite_backend.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String nickname;

    private String userid;

    private String email;
}
