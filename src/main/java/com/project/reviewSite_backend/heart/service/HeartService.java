package com.project.reviewSite_backend.heart.service;

import com.project.reviewSite_backend.heart.dao.HeartRepository;
import com.project.reviewSite_backend.heart.domain.Heart;
import com.project.reviewSite_backend.heart.dto.HeartDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
//    private final DetailRepository detailRepository;

    public void clickHeart(HeartDto heartDto, User user) {
        Optional<Heart> byHeartAndUser = heartRepository.findByUserAndPostid(user, heartDto.getPostid());

        byHeartAndUser.ifPresentOrElse(
                clickHeart -> {

                    heartRepository.deleteByUserAndPostid(user, heartDto.getPostid());
                },
                () -> {
                    Heart heartUser = Heart.builder()
                            .postid(heartDto.getPostid())
                            .postName(heartDto.getPostName())
                            .user(user)
                            .build();

                    heartRepository.save(heartUser);
                }
        );
    }

//    public void clickHeart(HeartDto heartDto, User user, Detail detail) {
//        Optional<Heart> byHeartAndUser = heartRepository.findByUserAndPostid(user, heartDto.getPostid());
//
//        byHeartAndUser.ifPresentOrElse(
//                clickHeart -> {
//
//                    heartRepository.deleteByUserAndPostid(user, heartDto.getPostid());
//                },
//                () -> {
//                    Heart heartUser = Heart.builder()
//                            .postid(heartDto.getPostid())
//                            .postName(heartDto.getPostName())
//                            .user(user)
//                            .build();
//
//                    heartRepository.save(heartUser);
//
//                    Detail detail1 = Detail.builder()
//                            .postId(heartDto.getPostid())
//                            .postName(heartDto.getPostName())
//                            .build();
//
//                    if (detail.getPostId() == detail1.getPostId()) {
//                        return;
//                    }
//
//                    detailRepository.save(detail1);
//                }
//        );
//    }

    public Boolean checkClickHeartDuplicate(String postid, User user) {
        return heartRepository.existsByPostidAndUser(postid, user);
    }

    public List<HeartDto> findPostByUserId(User user) {
        return heartRepository.findByUser(user)
                .stream()
                .map(post -> {
                    return new HeartDto((Heart) post);
                })
                .collect(Collectors.toList());
    }
}
