package com.project.reviewSite_backend.bookmark.controller;

import com.project.reviewSite_backend.bookmark.dto.BookmarkNameDto;
import com.project.reviewSite_backend.bookmark.dto.GetBookmarkNameDto;
import com.project.reviewSite_backend.bookmark.service.BookmarkNameService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarkname")
public class BookmarkNameController {

    private final UserService userService;
    private final BookmarkNameService bookmarkNameService;

    // 북마크 폴더 생성
    @PostMapping("")
    public BookmarkNameDto setBookmarkName(@RequestBody @Valid BookmarkNameDto bookmarkNameDto, @RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        bookmarkNameService.createBookmarkName(bookmarkNameDto, user);

        return bookmarkNameDto;
    }

    // 유저의 북마크 폴더 불러오기
    // 순환참조 일어나서 GetBookmarkNameDto 하나 더 생성함
    @GetMapping("")
    public List<BookmarkNameDto> getBookmarkNames(@RequestParam("userId") Long userId) {
        User user = userService.findUser(userId);

        List<BookmarkNameDto> bookmarkNameDtoList = bookmarkNameService.findBookmarkNameByUserId(user);

        return bookmarkNameDtoList;
    }

    // 북마크 이름 수정
    @PatchMapping("/{id}")
    public BookmarkNameDto updateName(@PathVariable Long id, @RequestBody BookmarkNameDto bookmarkNameDto) {

        return bookmarkNameService.updateName(id, bookmarkNameDto);
    }

    // 북마크 폴더 별 북마크 조회
    // 순환참조 일어나서 GetBookmarkNameDto 하나 더 생성함
    @GetMapping("/{id}")
    public GetBookmarkNameDto getBookmark(@PathVariable Long id) {

        GetBookmarkNameDto bookmarkList = bookmarkNameService.findBookmark(id);

        return bookmarkList;
    }

    // 북마크 폴더 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteFolder(@PathVariable Long id) {

        bookmarkNameService.deleteBookmarkFolder(id);

        return "삭제 완료";
    }

}
