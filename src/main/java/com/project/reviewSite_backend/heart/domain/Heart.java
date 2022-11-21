package com.project.reviewSite_backend.heart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewSite_backend.heart.dto.HeartDto;
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
    private String userid;

    @Column(nullable = false)
    private String postid;

}
