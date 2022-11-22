package com.project.reviewSite_backend.answer;

import com.project.reviewSite_backend.detail.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByDetailId(Long detailId);

    List<Answer> deleteByDetailId(Long detailId);


}
