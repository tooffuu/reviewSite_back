package com.project.reviewSite_backend.answer.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class AnswerVo {
    private Long id;//id키값
    private Long detail_id;//가게번호
    private String content;//리뷰냐용
    private Integer star;//평점
    private String userid;//아이디
    private String nickname;//닉네임
}
