package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.user.UserRole;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.CreateForm;
import com.project.reviewSite_backend.user.dto.UpdatePasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User modifynickname(User user) {

        User b;

        if (userRepository.save(user) == null) {
            b = null;
        } else {
            b = user;
        }

        return b;
    }

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

//    public List<CreateForm> findAllUser() {
//    }
}