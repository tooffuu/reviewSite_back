package com.project.reviewSite_backend.bookmark.dto;

import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.user.dto.UserBookmarkDto;
import com.project.reviewSite_backend.user.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {
    private Long id;

    private String postId;

    private String postName;

    private String phone;

    private String address;

    private Double locationX;

    private Double locationY;

    private BookmarkNameDto bookmarkName;

    private UserBookmarkDto user;

    public BookmarkDto(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.postId = bookmark.getPostId();
        this.postName = bookmark.getPostName();
        this.phone = bookmark.getPhone();
        this.address = bookmark.getAddress();
        this.locationX = bookmark.getLocationX();
        this.locationY = bookmark.getLocationY();
        this.bookmarkName = new BookmarkNameDto(bookmark.getBookmarkName());
        this.user = new UserBookmarkDto(bookmark.getUser());
    }

}
