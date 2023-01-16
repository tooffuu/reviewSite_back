package com.project.reviewSite_backend.bookmark.dao;

import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserAndPostId(User user, String postId);

    @Transactional
    void deleteByUserAndPostId(User user, String postId);

    Boolean existsByPostIdAndUser(String postId, User user);

    List<BookmarkDto> findByUser(User user);

    @Transactional
    void deleteByPostId(String postId);

}
