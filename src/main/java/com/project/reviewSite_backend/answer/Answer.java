package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
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
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;// 리뷰내용

    private Integer star;//평점

    @CreatedDate
    private LocalDateTime createDate;// 글쓴 시간

//
    @ManyToOne
    private Detail detail;// 디테일페이지 접근
////
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}