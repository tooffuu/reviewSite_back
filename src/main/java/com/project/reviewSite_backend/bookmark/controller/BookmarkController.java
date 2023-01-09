package com.project.reviewSite_backend.bookmark.controller;

import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.bookmark.dto.BookmarkDto;
import com.project.reviewSite_backend.bookmark.service.BookmarkNameService;
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
    private final BookmarkNameService bookmarkNameService;

    // 북마크 생성
    @PostMapping("/list")
    public BookmarkDto setBookmark(@RequestBody @Valid BookmarkDto bookmarkDto, @RequestParam("userId") Long userId, @RequestParam("nameId") Long nameId) {
        User user = userService.findUser(userId);

        BookmarkName bookmarkName = bookmarkNameService.findBookmarkNameId(nameId);

        bookmarkService.clickBookmark(bookmarkDto, user, bookmarkName);

        return bookmarkDto;
    }

    // 북마크 체크 유무 확인
    @GetMapping("/{postId}")
    public Boolean checkBookmark(@PathVariable String postId, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        return bookmarkService.checkBookmarkDuplicate(postId, user);
    }

    // 유저 별 북마크 가져오기
    @GetMapping("/user")
    public List<BookmarkDto> getBookmark(@RequestParam("userId") Long userId) {
            User user = userService.findUser(userId);

            return bookmarkService.findBookmarksByUserId(user);
    }

    // 북마크 폴더에 저장
    // PathVariable id => 북마크 id
    @PatchMapping("/name/set/{id}")
    public void setBookmarkName(@PathVariable Long id, @RequestParam("nameId") Long nameId) {

        BookmarkName markName = bookmarkNameService.findBookmarkNameId(nameId);

        bookmarkService.setName(id, markName);
    }

    // my place 에서 단독으로 북마크 삭제하기
    @DeleteMapping("/delete/{postId}")
    public String deleteBookmark(@PathVariable String postId) {

        bookmarkService.deleteBookmark(postId);
        return "삭제 완료";
    }



}
