package com.project.reviewSite_backend.heart.dao;

import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPostid(User user, String postid);

    @Transactional
    void deleteByUserAndPostid(User user, String postid);

    Boolean existsByPostidAndUser(String postid, User user);

    List<Object> findByUser(User user);
}
