package com.project.reviewSite_backend.bookmark.domain;

import com.project.reviewSite_backend.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookmarkName;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public void update(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    @OneToMany(mappedBy = "bookmarkName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks;

}
