package com.project.reviewSite_backend.heart.dao;

import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByUseridAndPostid(String userid, String postid);

    @Transactional
    void deleteByUseridAndPostid(String userid, String postid);

}
