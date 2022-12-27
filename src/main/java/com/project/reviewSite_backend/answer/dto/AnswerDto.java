package com.project.reviewSite_backend.answer.dto;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AnswerDto {
    private Long id;

    private String content;

    private Integer star;

    private LocalDateTime createDate;

    private Long detail_id;

    private String detail_name;

    private String nickname;


    private List<PhotoDto> imageList;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();

        this.content = answer.getContent();

        this.star = answer.getStar();

        this.createDate = answer.getCreateDate();

        this.detail_id = answer.getDetailId();

        this.detail_name = answer.getDetail_name();

        this.nickname = answer.getNickname();



        List<PhotoDto> photoeDtoList = answer.getImageList()
                .stream()
                .map(photo -> {
                    PhotoDto photoDto = new PhotoDto(photo);
                    return photoDto;
                })
                .collect(Collectors.toList());
        this.imageList = photoeDtoList;
    }

}
