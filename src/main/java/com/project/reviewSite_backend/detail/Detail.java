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


    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Answer> answerList;



//   @OneToMany(mappedBy = "detail",cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
