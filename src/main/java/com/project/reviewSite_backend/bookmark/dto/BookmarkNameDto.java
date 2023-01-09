package com.project.reviewSite_backend.bookmark.dto;

import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkNameDto {
    private Long id;
    private String bookmarkName;

    public BookmarkNameDto(BookmarkName bookmarkName) {
        this.id = bookmarkName.getId();
        this.bookmarkName = bookmarkName.getBookmarkName();

    }

}
