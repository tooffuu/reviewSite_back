package com.project.reviewSite_backend.Profil.dao;

import com.project.reviewSite_backend.Profil.domain.Profil;


import com.project.reviewSite_backend.Profil.dto.ProfilDto;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfilRepository  extends JpaRepository<Profil, Long> {


    ProfilDto findByUser(User user);
}
