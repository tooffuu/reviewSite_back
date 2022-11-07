package com.project.reviewSite_backend.detail;


import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.user.CreateForm;
import com.project.reviewSite_backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {
private final  DetailRepository detailRepository;

    public Detail create(DetailVo detailVo) {
        Detail d = new Detail();
        d.setDetail_id(detailVo.getDetail_id());

        this.detailRepository.save(d);

        return d;
    }
    public List<Detail> findAll(){

        return this.detailRepository.findAll();
    }
}
