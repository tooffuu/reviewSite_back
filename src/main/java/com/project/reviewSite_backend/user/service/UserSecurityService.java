package com.project.reviewSite_backend.user.service;

import com.project.reviewSite_backend.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService {
    private final UserRepository userRepository;
}
