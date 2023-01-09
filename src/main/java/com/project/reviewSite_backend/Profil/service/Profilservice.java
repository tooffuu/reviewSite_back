package com.project.reviewSite_backend.Profil.service;

import com.project.reviewSite_backend.Profil.dao.ProfilRepository;
import com.project.reviewSite_backend.Profil.domain.Profil;
import com.project.reviewSite_backend.Profil.dto.ProfilDto;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Profilservice {
    private final ProfilRepository profilRepository;


    public ProfilDto Profil(String imgUrl, User user){
        Profil profil = Profil.builder()
                .imgUrl(imgUrl)
                .user(user)
                .build();
        Profil profil1 = profilRepository.save(profil);
        ProfilDto dto = new ProfilDto(profil1);
        return dto;
    }

    public ProfilDto finByuserid(User user) {
        if (user == null) {
            return null;
        }
        return profilRepository.findByUser(user);
    }

}
