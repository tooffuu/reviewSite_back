package com.project.reviewSite_backend.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateAnswerForm {

    private Long id;

    private String content;

    private Integer star;

    private LocalDateTime createDate;

    private Long detail_id;

    private String detail_name;

    private String nickname;

    private List<Long> imageIdList;




}
