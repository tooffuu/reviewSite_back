package com.project.reviewSite_backend.bookmark.domain;

import com.project.reviewSite_backend.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private String postName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double locationX;

    @Column(nullable = false)
    private Double locationY;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private BookmarkName bookmarkName;

    public void update(BookmarkName bookmarkName) {
        this.bookmarkName =  bookmarkName;
    }


}
