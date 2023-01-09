package com.project.reviewSite_backend.answer.dto;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.user.dto.UserCommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class AnswerVo {
    private Long id;
    private String content;
    private Integer star;
    private LocalDateTime createDate;
    private Long detail_id;
    private String detail_name;

    private String nickname;

    private UserCommentDto user;

    public AnswerVo(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.star = answer.getStar();
        this.createDate = answer.getCreateDate();
        this.detail_id = answer.getDetailId();
        this.detail_name = answer.getDetail_name();
        this.nickname = answer.getNickname();
        this.user = new UserCommentDto(answer.getUser());

    }
}
