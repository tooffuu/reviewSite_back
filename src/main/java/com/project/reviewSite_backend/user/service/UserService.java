package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void joinUser(UserDto userDto) {
        User user = User.builder()
                .userid(userDto.getUserid())
                .username(userDto.getUsername())
                .password(userDto.getPassword1())
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .build();
        userRepository.save(user);
    }
}
