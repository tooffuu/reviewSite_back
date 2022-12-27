package com.project.reviewSite_backend.photo.service;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.photo.dao.PhotoRepository;
import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoDto photo(String imgUrl, Answer answer){
        Photo photo = Photo.builder()
                .imgUrl(imgUrl)
                .answer(answer)
                .detailId(answer.getDetailId())
                .content(answer.getContent())
                .nickname(answer.getNickname())
                .build();
        Photo photo1 = photoRepository.save(photo);
        PhotoDto dto = new PhotoDto(photo1);
        return dto;
    }


    public void setAnswerImageList(Answer answer1, List<Long> imageIdList) {
        List<Photo> answerImageList = photoRepository.findByIdIn(imageIdList);
        System.out.println("게시물에 등록된 사진 개수: " + answerImageList.size());
        answerImageList
                .stream()
                .forEach(
                        photo -> {
                            photo.setAnswer(answer1);
                            photoRepository.save(photo);
                        }
                );
    }


    public List<PhotoDto> findBypostDitailId(Long detailId) {
        return photoRepository.findByDetailId(detailId);

    }


}
