package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
