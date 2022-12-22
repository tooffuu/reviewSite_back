package com.project.reviewSite_backend.answer.domain;

import com.project.reviewSite_backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 생성 id키값

    @Column(columnDefinition = "TEXT")
    private String content;// 리뷰내용

    private Integer star;//평점

    @CreatedDate
    private LocalDateTime createDate;// 글쓴 시간

    private Long detailId;// 디테일페이지 접근

    private String detail_name;

    private String nickname;//유저 별명

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}