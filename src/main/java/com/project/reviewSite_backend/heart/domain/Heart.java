package com.project.reviewSite_backend.heart.domain;

import com.project.reviewSite_backend.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
