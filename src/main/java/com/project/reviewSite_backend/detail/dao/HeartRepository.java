package com.project.reviewSite_backend.detail.dao;

import com.project.reviewSite_backend.detail.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
}
