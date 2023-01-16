package com.project.reviewSite_backend.bookmark.service;

import com.project.reviewSite_backend.bookmark.dao.BookmarkNameRepository;
import com.project.reviewSite_backend.bookmark.dao.BookmarkRepository;
import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkNameRepository bookmarkNameRepository;
    private final BookmarkRepository bookmarkRepository;

    // 북마크 클릭시 생성 / 삭제
    public void clickBookmark(BookmarkDto bookmarkDto, User user, BookmarkName bookmarkName) {

        Optional<Bookmark> byBookmarkAndUser = bookmarkRepository.findByUserAndPostId(user, bookmarkDto.getPostId());

        byBookmarkAndUser.ifPresentOrElse(
                clickBookmark -> {
                    bookmarkRepository.deleteByUserAndPostId(user, bookmarkDto.getPostId());
                },
                () -> {

                    Bookmark bookmark = Bookmark.builder()
                            .postId(bookmarkDto.getPostId())
                            .postName(bookmarkDto.getPostName())
                            .phone(bookmarkDto.getPhone())
                            .address(bookmarkDto.getAddress())
                            .locationX(bookmarkDto.getLocationX())
                            .locationY(bookmarkDto.getLocationY())
                            .user(user)
                            .bookmarkName(bookmarkName)
                            .build();

                    bookmarkRepository.save(bookmark);
                }
        );
    }


    public Boolean checkBookmarkDuplicate(String postId, User user) {
        return bookmarkRepository.existsByPostIdAndUser(postId, user);
    }

    public List<BookmarkDto> findBookmarksByUserId(User user) {
            return bookmarkRepository.findByUser(user);
    }

    // 북마크에 북마크 폴더 이름 저장
    public Bookmark setName(Long id, BookmarkName markName) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow();

        bookmark.update(markName);

        return bookmarkRepository.save(bookmark);
    }


    public void deleteBookmark(String postId) {
        try {
            bookmarkRepository.deleteByPostId(postId);

        } catch (EmptyResultDataAccessException e) {

        }
    }


    public List<BookmarkDto> getbookmarkdata(User user) {
        return bookmarkRepository.findByUser(user);
    }
}
