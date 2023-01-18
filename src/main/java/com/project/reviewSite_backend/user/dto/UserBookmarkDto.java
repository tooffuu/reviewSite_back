package com.project.reviewSite_backend.user.dto;

import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookmarkDto {
    private Long id;
    private String nickname;

    public UserBookmarkDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
