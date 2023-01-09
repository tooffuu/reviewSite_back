package com.project.reviewSite_backend.Profil.controller;


import com.project.reviewSite_backend.Profil.dto.ProfilDto;
import com.project.reviewSite_backend.Profil.service.Profilservice;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Profilcontroller {
    private final Profilservice profilservice;

    @GetMapping("/user/profil")
    public ProfilDto findprfilimg(@RequestParam("userId") User userId) {
        ProfilDto profilDto = profilservice.finByuserid(userId);
        return profilDto;
    }
}
