package com.project.reviewSite_backend.bookmark.dao;


import com.project.reviewSite_backend.bookmark.domain.Bookmark;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndPostId(User user, Integer postId);

    @Transactional
    void deleteByUserAndPostId(User user, Integer postId);

    Boolean existsByPostIdAndUser(Integer postId, User user);

    List<BookmarkDto> findByUser(User user);
}
