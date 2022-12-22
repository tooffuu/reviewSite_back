package com.project.reviewSite_backend.answer.service;

import com.project.reviewSite_backend.answer.dao.AnswerRepository;
import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.answer.dto.StarcountDto;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    //-----------------------------------------------------------------------------------
    //생성하는 로직
    public AnswerVo starin(AnswerVo answerVo, User user) {

        Answer d = new Answer();
        d.setStar(answerVo.getStar());
        d.setContent(answerVo.getContent());
        d.setCreateDate(LocalDateTime.now());
        d.setDetailId(answerVo.getDetail_id());
        d.setDetail_name(answerVo.getDetail_name());
        d.setNickname(answerVo.getNickname());
        d.setUser(user);
        Answer answer = answerRepository.save(d);
        AnswerVo answerVo1 = new AnswerVo(answer);

        return answerVo1;
    }
    //-----------------------------------------------------------------------------------
    //리뷰 업데이트 컴포넌트
    public boolean updateContent(AnswerVo answerVo) {
        Optional<Answer> opContent = this.answerRepository.findById(answerVo.getId());
        System.out.println(answerVo);
        if (opContent.isPresent()) {
            Answer content = opContent.get();
            content.setId(answerVo.getId());
            content.setContent(answerVo.getContent());
            content.setStar(answerVo.getStar());
            content.setCreateDate(LocalDateTime.now());
            answerRepository.save(content);
            return true;
        } else {
            return false;
        }

    }
    //-----------------------------------------------------------------------------------
    //디테일 아이디로 데이터 불러오기
//    public List<Answer> answers(Long DetailId) {
//        return this.answerRepository.findByDetailId(DetailId);
//    }

    public List<AnswerVo> answers(Long detailId) {
        return answerRepository.findByDetailId(detailId)
                .stream()
                .map(answer -> {
                    return new AnswerVo(answer);
                })
                .collect(Collectors.toList());
    }

    //-----------------------------------------------------------------------------------
    //id값으로 삭제하는 로직
    public Answer deleteById(Long id) {
        try {
            answerRepository.deleteById(id);

            return deleteById(id);
        } catch (
                EmptyResultDataAccessException e) {//오류 예외
            return null;
        }
    }
    //------------------------------------------------------------------------------------
    //평점 평균구하는 로직
    public StarcountDto staravg(Long detailId) {
        StarcountDto starcountDto = new StarcountDto();
        List<Answer> getstar = answerRepository.findByDetailId(detailId);
        Long avg = 0L;
        for (Answer answer : getstar) {
            avg += answer.getStar();
        }
        starcountDto.setCount(getstar.size());
        starcountDto.setStar((double) avg / getstar.size());
        return starcountDto;
    }

    public List<AnswerVo> findCommentByUserId(User user) {
        if (user == null) {
            return null;
        }
        return answerRepository.findByUser(user);
    }
}
