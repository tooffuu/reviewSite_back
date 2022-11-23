package com.project.reviewSite_backend.heart.service;

import com.project.reviewSite_backend.heart.dao.HeartRepository;
import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public void clickHeart(HeartDto heartDto) {
        Optional<Heart> byHeartAndUser = heartRepository.findByUseridAndPostid(heartDto.getUserid(), heartDto.getPostid());

        byHeartAndUser.ifPresentOrElse(
                clickHeart -> {

                    heartRepository.deleteByUseridAndPostid(heartDto.getUserid(), heartDto.getPostid());
                },
                () -> {
                    Heart heartUser = Heart.builder()
                            .postid(heartDto.getPostid())
                            .userid(heartDto.getUserid())
                            .build();

                    heartRepository.save(heartUser);
                }
        );
    }

    public boolean checkClickHeartDuplicate(String userid, String postid) {
        return heartRepository.existsByUseridAndPostid(userid, postid);
    }

}
