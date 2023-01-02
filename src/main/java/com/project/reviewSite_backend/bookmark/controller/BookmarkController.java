package com.project.reviewSite_backend.bookmark.controller;

import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.bookmark.dto.BookmarkNameDto;
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

    // 북마크 이름 생성
    @PostMapping("")
    public BookmarkNameDto setBookmarkName(@RequestBody @Valid BookmarkNameDto bookmarkNameDto, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        bookmarkService.createBookmarkName(bookmarkNameDto, user);

        return bookmarkNameDto;
    }

    @GetMapping("")
    public List<BookmarkNameDto> getBookmarkNames(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<BookmarkNameDto> bookmarkNameDtoList = bookmarkService.findBookmarkNameByUserId(user);

        return bookmarkNameDtoList;
    }

    // 북마크 생성
    @PostMapping("/list")
    public BookmarkDto setBookmark(@RequestBody @Valid BookmarkDto bookmarkDto, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        bookmarkService.clickBookmark(bookmarkDto, user);

        return bookmarkDto;
    }


    @GetMapping("/{postId}")
    public Boolean checkBookmark(@PathVariable String postId, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        return bookmarkService.checkBookmarkDuplicate(postId, user);
    }

    @GetMapping("/user")
    public List<BookmarkDto> getBookmark(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<BookmarkDto> bookmarkDtoList = bookmarkService.findBookmarksByUserId(user);

        return bookmarkDtoList;
    }

    // 북마크 폴더의 id 조회
//    @GetMapping("/name")
//    public BookmarkNameDto getBookmarkId(@RequestParam("nameId") Long nameId) {
//        BookmarkNameDto nameDto = bookmarkService.findBookmarkNameId(nameId);
//
//        return nameDto;
//    }

    @GetMapping("/name")
    public BookmarkName getBookmarkId(@RequestParam("nameId") Long nameId) {
        return bookmarkService.findBookmarkNameId(nameId);
    }

    // 북마크 폴더에 저장
    // PathVariable id => 북마크 id
    @PatchMapping("/name/set/{id}")
    public void setBookmarkName(@PathVariable Long id, @RequestParam("nameId") Long nameId) {

        BookmarkName markName = bookmarkService.findBookmarkNameId(nameId);

        bookmarkService.setName(id, markName);
    }
}
