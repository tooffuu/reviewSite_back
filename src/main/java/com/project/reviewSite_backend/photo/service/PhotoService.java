package com.project.reviewSite_backend.photo.service;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.photo.dao.PhotoRepository;
import com.project.reviewSite_backend.photo.domain.Photo;
import com.project.reviewSite_backend.photo.dto.PhotoAnswerDto;
import com.project.reviewSite_backend.photo.dto.PhotoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoAnswerDto photo(String imgUrl, Answer answer){
        Photo photo = Photo.builder()
                .imgUrl(imgUrl)
                .answer(answer)
                .detailId(answer.getDetailId())
                .content(answer.getContent())
                .nickname(answer.getNickname())
                .build();
        Photo photo1 = photoRepository.save(photo);
        PhotoAnswerDto dto = new PhotoAnswerDto(photo1);
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


    public List<PhotoDto> findBypostDetailId(Long detailId) {
        return photoRepository.findByDetailId(detailId);
    }

    public List<PhotoDto> getphotoimgByAnswer(Answer answer) {
        return photoRepository.findByAnswer(answer);
    }

    public List<Photo> findByall() {
        return photoRepository.findAll();
    }
}
