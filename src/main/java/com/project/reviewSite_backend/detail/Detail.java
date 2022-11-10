package com.project.reviewSite_backend.detail;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewSite_backend.answer.Answer;
import com.project.reviewSite_backend.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer detail_id;

    @OneToMany(mappedBy = "detail", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Answer> answerList;

    @ManyToMany
    Set<User> liek; //id값 토대로 생성

    @ManyToMany
    Set<User> heart; //id값 토대로 생성

//   @OneToMany(mappedBy = "detail",cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
