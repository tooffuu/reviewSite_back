package com.project.reviewSite_backend.detail.service;

import com.project.reviewSite_backend.detail.dao.HeartRepository;
import com.project.reviewSite_backend.detail.domain.Heart;
import com.project.reviewSite_backend.detail.dto.HeartDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public void heart(HeartDto heartDto, User user) {
        Heart heart = Heart.builder()
                .id(user.getId())
                .detail_id(heartDto.getDetail_id())
                .build();

        System.out.println("유저 id: " + user.getUserid());
        System.out.println("게시글 id: " + heartDto.getDetail_id());
        heartRepository.save(heart);
        }
    }