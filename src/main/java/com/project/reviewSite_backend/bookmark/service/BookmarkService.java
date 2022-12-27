package com.project.reviewSite_backend.bookmark.service;

import com.project.reviewSite_backend.bookmark.dao.BookmarkRepository;
import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
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
                            .locationX(bookmarkDto.getLocationX())
                            .locationY(bookmarkDto.getLocationY())
                            .user(user)
                            .build();

                    bookmarkRepository.save(bookmark);
                }
        );
    }

    public Boolean checkBookmarkDuplicate(Integer postId, User user) {
        return bookmarkRepository.existsByPostIdAndUser(postId, user);
    }

    public List<BookmarkDto> findBookmarksByUserId(User user) {
        return bookmarkRepository.findByUser(user);
    }
}
