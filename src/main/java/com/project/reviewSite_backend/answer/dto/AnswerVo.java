package com.project.reviewSite_backend.answer.dto;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.photo.dto.PhotoAnswerDto;
import com.project.reviewSite_backend.user.dto.UserCommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<PhotoAnswerDto> image;

    public AnswerVo(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.star = answer.getStar();
        this.createDate = answer.getCreateDate();
        this.detail_id = answer.getDetailId();
        this.detail_name = answer.getDetail_name();
        this.nickname = answer.getNickname();
        this.user = new UserCommentDto(answer.getUser());

        List<PhotoAnswerDto> photoDtoList = answer.getImageList()
                .stream()
                .map(photo -> {
                    PhotoAnswerDto answerImage = new PhotoAnswerDto(photo);
                    return answerImage;
                })
                .collect(Collectors.toList());
        this.image = photoDtoList;

    }
}
