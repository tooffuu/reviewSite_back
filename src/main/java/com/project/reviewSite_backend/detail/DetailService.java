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
private final  DetailRepository detailRepository;

private  final UserService userService;

private final EntityManager em;
    public Detail create(DetailVo detailVo) {
        Detail d = new Detail();
        d.setDetail_id(detailVo.getDetail_id());

        this.detailRepository.save(d);

        return d;
    }
    public void likes(Detail detail, User user) {
        System.out.println(detail.getLiek());
        System.out.println("@AuthenticationPrincipal: " + user);
        System.out.println("from userRepository: " + userService.getUser(user.getUsername()));
        System.out.println("em: " + em.find(User.class, user.getId()));
        if(!detail.getLiek().contains(em.find(User.class, user.getId()))) {
            detail.getLiek().add(em.find(User.class, user.getId()));
        } else {
            detail.getLiek().remove(em.find(User.class, user.getId()));
        }
        detailRepository.save(detail);

    }
    public Detail getDetail(Integer id) {
        Optional<Detail> detail = this.detailRepository.findById(id);//답글 아이디 찾기
        return detail.orElseThrow(() -> new PasswordNotMatchException("answer not found"));//오류 확인문
    }

    public List<Detail> findAll(){

        return this.detailRepository.findAll();
    }
}
