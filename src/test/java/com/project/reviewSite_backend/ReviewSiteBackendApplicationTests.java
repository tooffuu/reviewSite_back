package com.project.reviewSite_backend;

import com.project.reviewSite_backend.answer.Answer;
import com.project.reviewSite_backend.answer.AnswerRepository;
import com.project.reviewSite_backend.detail.Detail;
import com.project.reviewSite_backend.detail.DetailRepository;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class ReviewSiteBackendApplicationTests {
	@Autowired
	private DetailRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserRepository userRepository;


	@Test
	void create_answer() {
		Optional<User> ou = userRepository.findById(1L);
		assertTrue(ou.isPresent());
		User u = ou.get();

		Answer a = new Answer();
		a.setNickname(u.getNickname());
		a.setContent("맛있어용");
		a.setCreateDate(LocalDateTime.now());
		a.setUser(u);
		answerRepository.save(a);


	}

}