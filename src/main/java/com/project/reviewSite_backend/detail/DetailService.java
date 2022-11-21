package com.project.reviewSite_backend.detail;



import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;

    private final UserService userService;

    private final EntityManager em;

    public Detail create(DetailVo detailVo) {
        Detail d = new Detail();
        d.setDetail_id(detailVo.getDetail_id());

        this.detailRepository.save(d);

        return d;
    }

    public Detail getDetail(Integer id) {
        Optional<Detail> detail = this.detailRepository.findById(id);//답글 아이디 찾기
        return detail.orElseThrow(() -> new PasswordNotMatchException("answer not found"));//오류 확인문
    }
}


