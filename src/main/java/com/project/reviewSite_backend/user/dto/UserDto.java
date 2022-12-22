package com.project.reviewSite_backend.user.dto;

import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String nickname;

    private String userid;

    private String email;

    private List<HeartDto> heartDtoList;

    private List<AnswerVo> answerVoList;

    public UserDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.userid = user.getUserid();
        this.email = user.getEmail();

        List<HeartDto> heartDtos = user.getHearts()
                .stream()
                .map(heart -> {
                    HeartDto heartDto1 = new HeartDto(heart);
                    return heartDto1;
                })
                .collect(Collectors.toList());

        this.heartDtoList = heartDtos;

        List<AnswerVo> answerList = user.getAnswerList()
                .stream()
                .map(answer -> {
                    AnswerVo answerVo = new AnswerVo(answer);
                    return answerVo;
                })
                .collect(Collectors.toList());

        this.answerVoList = answerList;
    }
}