package com.project.reviewSite_backend.profile.service;

import com.project.reviewSite_backend.profile.dao.ProfileRepository;
import com.project.reviewSite_backend.profile.domain.Profile;
import com.project.reviewSite_backend.profile.dto.ProfileDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Profileservice {
    private final ProfileRepository profilRepository;


    public ProfileDto Profil(String imgUrl, User user){
        Profile profil = Profile.builder()
                .imgUrl(imgUrl)
                .user(user)
                .build();
        Profile profil1 = profilRepository.save(profil);
        ProfileDto dto = new ProfileDto(profil1);
        return dto;
    }

    public ProfileDto finByuserid(User user) {
        if (user == null) {
            return null;
        }
        return profilRepository.findByUser(user);
    }

}
