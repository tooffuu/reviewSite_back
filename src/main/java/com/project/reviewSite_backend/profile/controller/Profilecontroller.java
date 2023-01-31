package com.project.reviewSite_backend.profile.controller;


import com.project.reviewSite_backend.profile.dto.ProfileDto;
import com.project.reviewSite_backend.profile.service.Profileservice;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Profilecontroller {
    private final Profileservice profilservice;

    @GetMapping("/user/profil")
    public ProfileDto findprfilimg(@RequestParam("userId") User userId) {
        ProfileDto profilDto = profilservice.finByuserid(userId);
        return profilDto;
    }
}
