package com.project.reviewSite_backend.bookmark.service;

import com.project.reviewSite_backend.bookmark.dao.BookmarkNameRepository;
import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkNameDto;
import com.project.reviewSite_backend.bookmark.dto.GetBookmarkNameDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkNameService {

    private final BookmarkNameRepository bookmarkNameRepository;

    public void createBookmarkName(BookmarkNameDto bookmarkNameDto, User user) {

        BookmarkName bookmarkName = BookmarkName.builder()
                .bookmarkName(bookmarkNameDto.getBookmarkName())
                .user(user)
                .build();

        bookmarkNameRepository.save(bookmarkName);
    }

    public List<BookmarkNameDto> findBookmarkNameByUserId(User user) {
        return bookmarkNameRepository.findByUser(user);
    }

    // 북마크 id 불러오기
    public BookmarkName findBookmarkNameId(Long nameId) {
        return bookmarkNameRepository.findById(nameId).orElseThrow();
    }

    // 북마크 이름 수정
    public BookmarkNameDto updateName(Long id, BookmarkNameDto bookmarkNameDto) {
        BookmarkName updateMarkName = bookmarkNameRepository.findById(id).orElseThrow();

        updateMarkName.update(bookmarkNameDto.getBookmarkName());
        BookmarkName name = bookmarkNameRepository.save(updateMarkName);
        BookmarkNameDto bookmarkNameDto1 = new BookmarkNameDto(name);

        return bookmarkNameDto1;
    }

    public GetBookmarkNameDto findBookmark(Long id) {
        BookmarkName bookmarkName = bookmarkNameRepository.findById(id).orElseThrow();

        GetBookmarkNameDto getBookmarkNameDto = new GetBookmarkNameDto(bookmarkName);

        return getBookmarkNameDto;
    }

    public void deleteBookmarkFolder(Long id) {
        try {
            bookmarkNameRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }



}
