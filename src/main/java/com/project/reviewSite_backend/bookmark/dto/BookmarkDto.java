package com.project.reviewSite_backend.bookmark.dto;

import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {
    private Long id;

    @NotNull
    private Integer postId;
    @NotNull
    private String postName;
    @NotNull
    private Double locationX;
    @NotNull
    private Double locationY;

    public BookmarkDto(Bookmark bookmark) {
        this.id=bookmark.getId();
        this.postId = bookmark.getPostId();
        this.postName = bookmark.getPostName();
        this.locationX = bookmark.getLocationX();
        this.locationY = bookmark.getLocationY();
    }
}
