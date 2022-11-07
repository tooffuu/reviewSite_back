package com.project.reviewSite_backend.answer;

import lombok.Data;
import lombok.Getter;

@Getter


public class AnswerVo {

    private String get_detail_id;

    private String content;//리뷰냐용

    private Integer star;//평점

    private String userid;

    private String nickname;
}
