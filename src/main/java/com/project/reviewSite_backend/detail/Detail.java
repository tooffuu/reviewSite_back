package com.project.reviewSite_backend.detail;



import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer detail_id;

//   @OneToMany(mappedBy = "detail",cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
