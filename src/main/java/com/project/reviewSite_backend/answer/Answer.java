package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;// 리뷰내용

    private Integer star;//평점

    @CreatedDate
    private LocalDateTime createDate;// 글쓴 시간


    private Long detailId;// 디테일페이지 접근

    private String nickname;//유저 별명


    @ManyToMany
    Set<User> liek; //id값 토대로 생성

    @ManyToMany
    Set<User> heart; //id값 토대로 생성

////
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}