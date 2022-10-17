package com.project.reviewSite_backend.user.dao;

import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userid);

    Optional<UserDto> findByUserId(String userid);
}
