package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.user.UserRole;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.CreateForm;
import com.project.reviewSite_backend.user.dto.UpdatePasswordDto;
import com.project.reviewSite_backend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void joinUser(CreateForm createForm) {
        User user = User.builder()
                .username(createForm.getUsername())
                .nickname(createForm.getNickname())
                .userid(createForm.getUserid())
                .password(passwordEncoder.encode(createForm.getPassword1()))
                .email(createForm.getEmail())
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);
    }

    public CreateForm login(User user) {
        Optional<User> opUser = userRepository.findByUserid(user.getUserid());

        if (opUser.isPresent()) {
            User loginedUser = opUser.get();
            if (passwordEncoder.matches(user.getPassword(), loginedUser.getPassword())) {
                CreateForm createForm = new CreateForm(loginedUser);
                return createForm;
            }
            throw new PasswordNotMatchException(String.format("password do not match"));
        }
        throw new UserNotFoundException(String.format("%s not found", user.getUserid()));
    }

    public boolean checkUseridDuplicate(String userid) {
        return userRepository.existsByUserid(userid);
    }
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUserid(username);
        return user.orElseThrow(() -> new UserNotFoundException("siteuser not found"));
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

//    public CreateForm login(User user) {
//        Optional<User> opUser = userRepository.findByUserid(user.getUserid());
//
//        if (opUser.isPresent()) {
//            User loginedUser = opUser.get();
//            if (passwordEncoder.matches(user.getPassword(), loginedUser.getPassword())) {
//                CreateForm createForm = new CreateForm(loginedUser);
//                return createForm;
//            }
//            throw new PasswordNotMatchException(String.format("password do not match"));
//        }
//        throw new UserNotFoundException(String.format("%s not found", user.getUserid()));
//    }

    //마이페이지 진입전 회원 확인
    public User confirmPwd(User checkUser) {
        Optional<User> ou = userRepository.findByUserid(checkUser.getUserid());

        if(ou.isPresent()) {
            User user= ou.get();
            if(passwordEncoder.matches(checkUser.getPassword(), user.getPassword()))
            {
                return checkUser;
            } throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");

        } throw new UserNotFoundException("등록된 회원이 없습니다.");
    }

    //회원 정보 수정
    @Transactional
    public User modifyUser(User user) {

        Optional<User> ou = userRepository.findByUserid(user.getUserid());

        if(ou.isPresent()){
            User ru = ou.get();

            ru.setEmail(user.getEmail());
            ru.setNickname(user.getNickname());
            ru.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(ru);
            return user;
        } throw new UserNotFoundException("등록된 회원이 없습니다.");


    }


//    @Transactional
//    public User editprofile(User user) {
//        User persistansce = userRepository.findById(user.getId()).orElseThrow(()->{
//            return new IllegalArgumentException("회원 찾기 실패");
//        });
//        String rawPassword = user.getPassword();
//        String encPassword = passwordEncoder.encode(rawPassword);
//        persistansce.setPassword(encPassword);
//        persistansce.setEmail(user.getEmail());
//        persistansce.setNickname(user.getNickname());
//        System.out.println("==========================");
//        System.out.println(user.getNickname());
//        System.out.println(user.getEmail());
//        System.out.println("==========================");
//
//        return user;
//

    public CreateForm deleteById(Long id) {
        try {
            userRepository.deleteById(id);

            return deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    public String findId(String username, String email) {
        User user = userRepository.findByUsernameAndEmail(username, email);

        if (user == null) {
            return null;
        }

        return user.getUserid();
    }

    public CreateForm findPw(String username, String userid, String email) {
        User user = userRepository.findByUsernameAndUseridAndEmail(username, userid, email);

        if (user == null) {
            return null;
        }
        CreateForm createForm = new CreateForm(user);

        return createForm;
    }

    public Long updatePW(Long id, UpdatePasswordDto updatePasswordDto) {
        User modifyPw = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if ((updatePasswordDto.getPassword1()).equals(updatePasswordDto.getPassword2())) {
            modifyPw.update(passwordEncoder.encode(updatePasswordDto.getPassword1()), updatePasswordDto.getPassword2());
            return userRepository.save(modifyPw).getId();
        }
        throw new PasswordNotMatchException(String.format("패스워드가 일치하지 않습니다."));
    }

    public User findUser(Long userId) {

        if (userId == null){
            return null;
        }
        User user = userRepository.findById(userId).orElseThrow();

        return user;
    }
}