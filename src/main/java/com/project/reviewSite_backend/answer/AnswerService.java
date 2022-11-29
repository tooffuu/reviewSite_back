package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.detail.DetailRepository;
import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final DetailRepository detailRepository;

    @PersistenceContext
    private EntityManager em;


    public Answer starin(AnswerVo answerVo) {

        Answer d = new Answer();
        d.setStar(answerVo.getStar());
        d.setContent(answerVo.getContent());
        d.setCreateDate(LocalDateTime.now());
        d.setDetailId(answerVo.getDetail_id());
        d.setNickname(answerVo.getNickname());
        this.answerRepository.save(d);
        return d;
    }

    public boolean updateContent(AnswerVo answerVo) {
        Optional<Answer> opContent = this.answerRepository.findById(answerVo.getId());
        System.out.println(answerVo);
        if (opContent.isPresent()) {
            Answer content = opContent.get();
            content.setId(answerVo.getId());
            content.setContent(answerVo.getContent());
            content.setStar(answerVo.getStar());
            content.setCreateDate(LocalDateTime.now());
            this.answerRepository.save(content);
            return true;
        } else {
            return false;
        }

    }

//    public Answer getDetail_id(String id) {
//        Optional<Answer> answer = this.answerRepository.findBy(id);//답글 아이디 찾기
//        return answer.orElseThrow(() -> new PasswordNotMatchException("answer not found"));//오류 확인문
//    }


    public List<Answer> answers(Long DetailId) {

        return this.answerRepository.findByDetailId(DetailId);
    }


    public Answer deleteById(Long id) {
        try {
            answerRepository.deleteById(id);

            return deleteById(id);
        } catch (
                EmptyResultDataAccessException e) {

            return null;
        }
    }

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


}
