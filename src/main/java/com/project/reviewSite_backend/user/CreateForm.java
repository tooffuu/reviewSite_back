package com.project.reviewSite_backend.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateForm {
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



}
