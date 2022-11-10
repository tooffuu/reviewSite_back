package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //    @Column(unique = true, nullable = false)
//    private String get_detail_id;

    @Column(columnDefinition = "TEXT")
    private String content;// 리뷰내용

//    private Integer star;//평점

    @CreatedDate
    private LocalDateTime createDate;// 글쓴 시간

    private String nickname;// 사용자 닉네임

//    @Column(length = 1)
//    private Boolean heart;// 찜했는지


//    @Column(length = 1)
//    private Boolean like1;// 추천했는지

//    private String userid;//유저 아이디

//    @ManyToOne
//    private Detail detail;// 디테일페이지 접근

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}