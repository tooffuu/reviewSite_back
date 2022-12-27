package com.project.reviewSite_backend.bookmark.controller;

import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.bookmark.service.BookmarkService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
    private final UserService userService;

    private final BookmarkService bookmarkService;

    @PostMapping("")
    public BookmarkDto setBookmark(@RequestBody @Valid BookmarkDto bookmarkDto, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        bookmarkService.clickBookmark(bookmarkDto, user);

        return bookmarkDto;
    }

    @GetMapping("/{postId}")
    public Boolean checkBookmark(@PathVariable Integer postId, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        return bookmarkService.checkBookmarkDuplicate(postId, user);
    }

    @GetMapping("/user")
    public List<BookmarkDto> getBookmark(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<BookmarkDto> bookmarkDtoList = bookmarkService.findBookmarksByUserId(user);

        return bookmarkDtoList;
    }

}
