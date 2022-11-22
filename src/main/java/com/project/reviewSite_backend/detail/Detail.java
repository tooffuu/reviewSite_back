package com.project.reviewSite_backend.detail;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer detail_id;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    private List<Answer> answerList;


//   @OneToMany(mappedBy = "detail",cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
