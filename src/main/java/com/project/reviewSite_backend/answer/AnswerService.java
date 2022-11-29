package com.project.reviewSite_backend.answer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    //-----------------------------------------------------------------------------------
    //생성하는 로직
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
            this.answerRepository.save(content);
            return true;
        } else {
            return false;
        }

    }
    //-----------------------------------------------------------------------------------
    //디테일 아이디로 데이터 불러오기
    public List<Answer> answers(Long DetailId) {
        return this.answerRepository.findByDetailId(DetailId);
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


}
