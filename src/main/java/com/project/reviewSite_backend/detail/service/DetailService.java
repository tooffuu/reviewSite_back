package com.project.reviewSite_backend.detail.service;

import com.project.reviewSite_backend.detail.dao.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailService {

    private DetailRepository detailRepository;
    public void findDetail(String postId) {

        detailRepository.findByPostId(postId);

    }
}
