package com.project.reviewSite_backend.photo.dao;

import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByIdIn(List<Long> imageIdList);

//    List<PhotoDto> findBydetailId(Long detailId);

    List<PhotoDto> findByDetailId(Long detailId);
}
