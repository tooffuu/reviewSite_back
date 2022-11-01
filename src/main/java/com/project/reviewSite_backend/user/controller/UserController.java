package com.project.reviewSite_backend.user.controller;

import com.project.reviewSite_backend.user.CreateForm;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String getUser(@RequestBody @Valid CreateForm createForm, BindingResult bindingResult) {
//        System.out.println("password : " + createForm.getPassword1());
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

//    전체 유저의 전체 정보 가져오기
//    @GetMapping("/members")
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

//    전체 유저의 패스워드 제외한 정보 가져오기
//    @GetMapping("/checked")
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping("/{userid}/userid")
    public ResponseEntity<Boolean> checkUseridDuplicate(@PathVariable String userid) {
        return ResponseEntity.ok(userService.checkUseridDuplicate(userid));
    }

    @GetMapping("/{nickname}/nickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));
    }
    @GetMapping("/{email}/email")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }

    @PostMapping("/modify")
    public boolean modifyNickname(@RequestBody User user) {
        userService.modifynickname(user);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id, User user) {
        User deleteUser = userService.deleteById(id);

        return deleteUser;
    }

//    @ResponseBody
//    @GetMapping("/findId")
//    public ResponseEntity<String> findId(@RequestParam("username") String username, @RequestParam("email") String email) {
//        String userId = userService.findId(username, email);
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID를 찾지 못했습니다.");
//        }
//        return ResponseEntity.ok("불러온 유저 아이디 : " + userId);
//    }

//    @PostMapping("/findId")
//    public FindIdDto findId(@RequestBody FindIdDto findIdDto) {
//        System.out.println("user : " + findIdDto.getUserid());
//        System.out.println("controller : " + findIdDto.getUserid());
//        User findUserId = userService.findUserId(findIdDto);
//
//
//        return findIdDto;
//    }













}
