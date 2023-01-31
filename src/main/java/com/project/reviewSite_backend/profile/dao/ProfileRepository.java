package com.project.reviewSite_backend.profile.dao;

import com.project.reviewSite_backend.profile.domain.Profile;


import com.project.reviewSite_backend.profile.dto.ProfileDto;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


    ProfileDto findByUser(User user);
}
