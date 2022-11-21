package com.project.reviewSite_backend.heart.service;

import com.project.reviewSite_backend.heart.dao.HeartRepository;
import com.project.reviewSite_backend.heart.domain.Heart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public void clickHeart(Heart heart) {
        Optional<Heart> byHeartAndUser = heartRepository.findByUseridAndPostid(heart.getUserid(), heart.getPostid());

        byHeartAndUser.ifPresentOrElse(
                clickHeart -> {

                    heartRepository.deleteByUseridAndPostid(heart.getUserid(), heart.getPostid());
                },
                () -> {
                    Heart heartUser = Heart.builder()
                            .postid(heart.getPostid())
                            .userid(heart.getUserid())
                            .build();

                    heartRepository.save(heartUser);
                }
        );
    }

    public boolean checkClickHeartDuplicate(String userid, String postid) {
        return heartRepository.existsByUseridAndPostid(userid, postid);
    }

}
