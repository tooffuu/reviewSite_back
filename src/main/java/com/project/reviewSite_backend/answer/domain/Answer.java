package com.project.reviewSite_backend.answer.domain;

import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "answer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Photo> imageList;
}