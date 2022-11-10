package com.project.reviewSite_backend.answer;
import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.exception.PasswordNotMatchException;
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


    public Answer starin(AnswerVo answerVo) {


        Answer d = new Answer();
        d.setStar(answerVo.getStar());
        d.setContent(answerVo.getContent());
        d.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(d);
        return d;
    }


}
