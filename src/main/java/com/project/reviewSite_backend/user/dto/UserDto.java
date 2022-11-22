package com.project.reviewSite_backend.user.dto;

import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String nickname;

    private String userid;

    private String email;

    private List<HeartDto> detailDtos;

    public UserDto(User user){
        detailDtos = new ArrayList<>();
        user.getDetails().stream()
                .forEach(dtUser -> {
                    HeartDto heartDto = new HeartDto(dtUser);
                    detailDtos.add(heartDto);
                });
    }

}