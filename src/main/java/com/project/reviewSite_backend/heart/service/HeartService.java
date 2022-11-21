package com.project.reviewSite_backend.heart.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.reviewSite_backend.heart.dao.HeartRepository;
import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public void clickHeart(HeartDto heartDto) {
        Heart heart = Heart.builder()
                .postid(heartDto.getPostid())
                .userid(heartDto.getUserid())
                .build();

        heartRepository.save(heart);
    }

    public boolean checkClickHeartDuplicate(String userid, String postid) {
        return heartRepository.existsByUseridAndPostid(userid, postid);
    }


    public HeartDto deleteByUseridAndPostid(String userid, String postid) {
        try {
            heartRepository.deleteByUseridAndPostid(userid, postid);
            return deleteByUseridAndPostid(userid, postid);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
