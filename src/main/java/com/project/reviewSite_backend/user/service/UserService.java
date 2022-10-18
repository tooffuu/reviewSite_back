package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void joinUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .nickname(userDto.getNickname())
                .userid(userDto.getUserid())
                .password(passwordEncoder.encode(userDto.getPassword1()))
                .email(userDto.getEmail())
                .build();

        userRepository.save(user);
    }

//    public User login(User user) {
//        Optional<User> opUser = userRepository.findByUserid(user.getUserid());
//        if(opUser.isPresent()) {
//            return opUser.get();
//        }
//        return null;
//    }

    public User login(User user) {
        Optional<User> opUser = userRepository.findByUserid(user.getUserid());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(opUser.isPresent()) {
            User loginUser = opUser.get();
            System.out.println("boolean : " + passwordEncoder.matches(loginUser.getPassword(), user.getPassword()));
            System.out.println("saved db password : " + loginUser.getPassword());
            System.out.println("logined password : " + user.getPassword());
            return loginUser;
        }
        return null;
    }

}