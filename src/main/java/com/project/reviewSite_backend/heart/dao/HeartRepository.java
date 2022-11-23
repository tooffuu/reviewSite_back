package com.project.reviewSite_backend.heart.dao;

import com.project.reviewSite_backend.heart.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByUseridAndPostid(String userid, String postid);

    @Transactional
    void deleteByUseridAndPostid(String userid, String postid);

    Optional<Heart> findByUseridAndPostid(String userid, String postid);

}
