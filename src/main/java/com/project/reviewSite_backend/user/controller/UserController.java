package com.project.reviewSite_backend.user.controller;

import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.CreateForm;
import com.project.reviewSite_backend.user.dto.UpdatePasswordDto;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.jta.UserTransactionAdapter;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/join")
    public String getUser(@RequestBody @Valid CreateForm createForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Error";
        }
        if (!createForm.getPassword1().equals(createForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지않습니다.");
            return "2개의 패스워드가 일치하지않습니다.";
        }
        userService.joinUser(createForm);

        return "user";
    }

    @PostMapping("/login")
    public CreateForm login(@RequestBody User user) {

        CreateForm loginedUser = userService.login(user);

        return loginedUser;
    }

//    전체 유저의 전체 정보 가져오기
//    @GetMapping("/members")
//    public List<CreateForm> getAllUsers() {
//       List<CreateForm> createForm = userService.findAllUser();
//
//       return createForm;
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

    //비빌번회 외 닉네임 이메일 수정
    @PostMapping("/modify")
    public User modifyNickname(@RequestBody User user) {
        userService.modifynickname(user);
        return user;
    }



//    @PostMapping("/login")
//    public CreateForm login(@RequestBody User user) {
//
//        CreateForm loginedUser = userService.login(user);
//
//        return loginedUser;
//    }

    // 마이페이지 진입 전 회원 확인
    @PostMapping("/UserConfirmPwd")
    public User ConfirmPwd(@RequestBody User user) {
        return userService.confirmPwd(user);
    }



    @DeleteMapping("/delete/{id}")
    public CreateForm deleteUser(@PathVariable Long id) {
        CreateForm deleteUser = userService.deleteById(id);

        return deleteUser;
    }

    // 아이디 찾기
    @ResponseBody
    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam("username") String username, @RequestParam("email") String email) {
        String userId = userService.findId(username, email);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ID를 찾지 못했습니다.");
        }
        return ResponseEntity.ok(userId);
    }

    // 비밀번호 변경 전 유저 정보 확인
    @ResponseBody
    @GetMapping("/updatePw")
    public ResponseEntity<CreateForm> findPassword(@RequestParam("username") String username, @RequestParam("userid") String userid, @RequestParam("email") String email) {
        CreateForm userPw = userService.findPw(username, userid, email);
        if (userPw == null) {
            throw new UserNotFoundException("user not found");
        }
        return ResponseEntity.ok(userPw);
    }

    // 비밀번호 변경하기
    @PatchMapping("/updatePw/{id}")
    public Long update(@PathVariable Long id, @RequestBody UpdatePasswordDto updatePasswordDto) {
        userService.updatePW(id, updatePasswordDto);

        return null;
    }
}
