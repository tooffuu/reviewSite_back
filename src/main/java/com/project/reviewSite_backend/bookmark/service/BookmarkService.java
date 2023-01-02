package com.project.reviewSite_backend.bookmark.service;

import com.project.reviewSite_backend.bookmark.dao.BookmarkNameRepository;
import com.project.reviewSite_backend.bookmark.dao.BookmarkRepository;
import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.bookmark.dto.BookmarkNameDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    //    private final BookmarkService bookmarkService;
    private final BookmarkNameRepository bookmarkNameRepository;
    private final BookmarkRepository bookmarkRepository;

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

    // 북마크 클릭시 생성 / 삭제
    public void clickBookmark(BookmarkDto bookmarkDto, User user) {

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
                            .bookmarkName(null)
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

    // 북마크 id 불러오기
    public BookmarkName findBookmarkNameId(Long nameId) {
        return bookmarkNameRepository.findById(nameId).orElseThrow();
    }

    // 북마크에 북마크 폴더 이름 저장
    public Bookmark setName(Long id, BookmarkName markName) {
        Bookmark bookmark = bookmarkRepository.findById(id).orElseThrow();

//        bookmark.update(bookmark.getBookmarkName());
        bookmark.update(markName);

        Bookmark bookmark1 = bookmarkRepository.save(bookmark);

        return bookmark1;
    }
}
