package com.project.reviewSite_backend.Profil.domain;


import com.project.reviewSite_backend.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("'https://file-upload-ktw.s3.ap-northeast-2.amazonaws.com/user.png'")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
