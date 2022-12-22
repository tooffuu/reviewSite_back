package com.project.reviewSite_backend.answer.dto;

import com.project.reviewSite_backend.answer.domain.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerVo {
    private Long id;
    private String content;
    private Integer star;
    private LocalDateTime createDate;
    private Long detail_id;
    private String detail_name;
    private String nickname;

    public AnswerVo(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.star = answer.getStar();
        this.createDate = answer.getCreateDate();
        this.detail_id = answer.getDetailId();
        this.detail_name = answer.getDetail_name();
        this.nickname = answer.getNickname();
    }
}
