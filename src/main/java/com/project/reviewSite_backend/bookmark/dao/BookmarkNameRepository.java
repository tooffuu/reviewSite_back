package com.project.reviewSite_backend.bookmark.dao;


import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkNameDto;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkNameRepository extends JpaRepository<BookmarkName, Long> {

    List<BookmarkNameDto> findByUser(User user);

//    GetBookmarkNameDto findByIdAndUser(Long id, User user);
}
