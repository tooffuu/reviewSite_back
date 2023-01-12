package com.project.reviewSite_backend.detail.dao;

import com.project.reviewSite_backend.detail.domain.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    void findByPostId(String postId);
}
