package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.detail.DetailDto;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

//    public Answer create(Detail detail, String content) {
//        Answer answer = new Answer();
//        answer.setCreateDate(LocalDateTime.now());
//        answer.setDetail(detail);
//        answer.setContent(content);
//        return answer;
//    }

//    public Answer idpost(AnswerVo answerVo) {
//        Answer d = new Answer();
//        d.setGet_detail_id(answerVo.getGet_detail_id());
//
//        this.answerRepository.save(d);
//
//        return d;
//    }
//
//    public Answer starin(AnswerVo answerVo , User user){
//        Optional<User> user2 = userRepository.findById(1L);
//        assertTrue(user2.isPresent());
//        User u = user2.get();
//
//        Answer d = new Answer();
//        d.setStar(answerVo.getStar());
//        d.setContent(answerVo.getContent());
//        d.setGet_detail_id(answerVo.getGet_detail_id());
////        d.setUserid(user.getUserid());
////        d.setNickname(user.getNickname());
//        this.answerRepository.save(d);
//        return d;
//    }
//public Answer contentin(AnswerVo answerVo){
//        Answer content = new Answer();
//
//        return content;
//}

}