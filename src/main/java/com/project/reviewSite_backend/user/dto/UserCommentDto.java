package com.project.reviewSite_backend.user.dto;

import com.project.reviewSite_backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommentDto {

    private Long id;

    private String nickname;

    public UserCommentDto (User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();

    }

}
