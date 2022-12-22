package com.project.reviewSite_backend.answer.dao;

import com.project.reviewSite_backend.answer.domain.Answer;
import com.project.reviewSite_backend.answer.dto.AnswerVo;
import com.project.reviewSite_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByDetailId(Long detailId);//디테일아이디로 찾기

    List<AnswerVo> findByUser(User user);
}
