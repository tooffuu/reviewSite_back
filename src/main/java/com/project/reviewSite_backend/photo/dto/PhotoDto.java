package com.project.reviewSite_backend.photo.dto;


import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerDto;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.photo.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {
    private Long id;
//    private String content;
//    private String nickname;
    private String imgUrl;
//    private Long detailId;
    private AnswerVo answer;

    public PhotoDto(Photo photo){
        this.id = photo.getId();
        this.imgUrl = photo.getImgUrl();
//        this.detailId = photo.getDetailId();
//        this.content = photo.getContent();
//        this.nickname = photo.getNickname();
        this.answer = new AnswerVo(photo.getAnswer());
    }
}
