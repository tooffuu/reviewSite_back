package com.project.reviewSite_backend.detail.service;

import com.project.reviewSite_backend.detail.dao.DetailRepository;
import com.project.reviewSite_backend.detail.domain.Detail;
import com.project.reviewSite_backend.detail.dto.DetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;

    public void getPost(DetailDto detailDto) {
        Detail detail = Detail.builder()
                .detail_id(detailDto.getDetail_id())
                .detail_name(detailDto.getDetail_name())
                .build();

        detailRepository.save(detail);
    }
}
