package com.project.reviewSite_backend.user.dto;

import com.project.reviewSite_backend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateForm {

    private Long id;
    @Size(min = 1)
    @NotEmpty(message = "사용자 이름은 필수항목입니다.")
    private String username;

    @Size(min = 1, max = 15)
    @NotEmpty(message = "닉네임은 필수항목입니다.")
    private String nickname;

    @Size(min = 3, max = 15)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String userid;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    public CreateForm (User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.userid = user.getUserid();
        this.password1 = user.getPassword();
        this.email = user.getEmail();
    }



}
