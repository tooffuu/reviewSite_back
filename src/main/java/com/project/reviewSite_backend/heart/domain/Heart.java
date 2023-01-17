package com.project.reviewSite_backend.heart.domain;

import com.project.reviewSite_backend.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postid;

    @Column(nullable = false)
    private String postName;

    @Column(nullable = false)
    @ColumnDefault("'https://file-upload-ktw.s3.ap-northeast-2.amazonaws.com/user.png'")
    private String img;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
