package com.project.reviewSite_backend.user.controller;

import com.project.reviewSite_backend.user.CreateForm;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.UserDto;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/join")
    public String getUser(@RequestBody @Valid CreateForm createForm, BindingResult bindingResult) {
//        System.out.println("password: " + userDto.getPassword1());
        if(bindingResult.hasErrors()) {
            return "Error";
        }
        if(!createForm.getPassword1().equals(createForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지않습니다.");
            return "2개의 패스워드가 일치하지않습니다.";
        }
        userService.joinUser(createForm);

        return "user";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
//        System.out.println("user : " + user.getUserid());
//        System.out.println("password : " + user.getPassword());
        User loginedUser = userService.login(user);

        return loginedUser;
    }

//    @GetMapping("/members")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @GetMapping("/members")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }


}
