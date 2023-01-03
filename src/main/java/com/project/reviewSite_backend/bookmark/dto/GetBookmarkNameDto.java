package com.project.reviewSite_backend.bookmark.dto;

import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetBookmarkNameDto {

    private Long id;
    private String bookmarkName;

    private List<BookmarkDto> bookmark;

    public GetBookmarkNameDto(BookmarkName bookmarkName) {
        this.id = bookmarkName.getId();
        this.bookmarkName = bookmarkName.getBookmarkName();

        List<BookmarkDto> bookmarkList = bookmarkName.getBookmarks()
                .stream()
                .map(bookmark1 -> {
                    BookmarkDto bookmarkDto = new BookmarkDto(bookmark1);

                    return bookmarkDto;
                })
                .collect(Collectors.toList());

        this.bookmark = bookmarkList;

    }


}
