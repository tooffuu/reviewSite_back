package com.project.reviewSite_backend.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewSite_backend.profile.domain.Profile;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.user.UserRole;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userid;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @ColumnDefault("'https://file-upload-ktw.s3.ap-northeast-2.amazonaws.com/user.png'")
    private String userImgUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookmarkName> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answerList;



    @OneToMany(mappedBy = "user", cascade =CascadeType.ALL, orphanRemoval = true)
    private List<Profile> imageList;

    public void update(String password1, String password2) {
        this.password = password1;
    }

    public void update(String password, String nickname, String email) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;

    }

    public void modify(String nickname, String password, String email){
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }


}
