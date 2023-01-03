package com.project.reviewSite_backend.photo.domain;


import com.project.reviewSite_backend.answer.domain.Answer;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private Answer answer;

    private Long detailId;

    private String content;

    private String nickname;




}
