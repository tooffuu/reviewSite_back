package com.project.reviewSite_backend.user.controller;

import com.project.reviewSite_backend.user.dto.UserDto;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String getUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
//        System.out.println("password: " + userDto.getPassword1());
        if(bindingResult.hasErrors()) {
            return "Error";
        }
        if(!userDto.getPassword1().equals(userDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지않습니다.");
            return "2개의 패스워드가 일치하지않습니다.";
        }

        userService.joinUser(userDto);
        return "user";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        System.out.println("userID : " + userDto.getUserid());
        return "로그인";
    }
}
