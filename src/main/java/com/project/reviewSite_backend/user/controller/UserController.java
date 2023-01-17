package com.project.reviewSite_backend.user.controller;

import com.project.reviewSite_backend.answer.AWS.AwsService;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import com.project.reviewSite_backend.photo.service.PhotoService;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.CreateForm;
import com.project.reviewSite_backend.user.dto.UpdatePasswordDto;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final AwsService awsService;
    private final PhotoService photoService;
    private final UserService userService;

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

        return "회원가입 성공";
    }

    @PostMapping("/login")
    public CreateForm login(@RequestBody User user) {

        CreateForm loginedUser = userService.login(user);

        return loginedUser;
    }

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

    // 마이페이지 진입 전 회원 확인
    @PostMapping("/UserConfirmPwd")
    public CreateForm ConfirmPwd(@RequestBody User user) {
        return userService.confirmPwd(user);
    }

    //회원 정보 수정
    @PatchMapping("/editprofile")
    public CreateForm modify(CreateForm user, @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        return userService.modifyUser(user, files);
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
