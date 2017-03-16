package com.co.rc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.rc.dao.AnswerDao;
import com.co.rc.dao.UserExamDao;
import com.co.rc.model.Exam;
import com.co.rc.model.Question;
import com.co.rc.model.User;
import com.co.rc.model.UserExam;
import com.co.rc.model.dto.QuestionDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

@Service
public class ScoreService {

	@Autowired
	private AnswerDao answerDao;
	
	@Autowired
	private UserExamDao userExamDao;
	
	public int computeScore(User user, Exam exam) {
		int total = exam.getQuestions().size();

		if (total == 0) {
			saveScore(user, exam, 0);
			return 0;
		}

		int score = 0;
		for (Question question : exam.getQuestions()) {
			if (isAnswerCorrect(user, question)) {
				score++;
			}
		}

		int scorePercent = BigDecimal.TEN.multiply(BigDecimal.TEN).multiply(new BigDecimal(score))
				.divide(new BigDecimal(total), 0, RoundingMode.HALF_UP).intValue();
		
		saveScore(user, exam, scorePercent);
		
		return scorePercent;
	}
	
	public int computeCorrectAnswers(User user, Exam exam) {
		int total = exam.getQuestions().size();

		if (total == 0) {
			return 0;
		}

		int score = 0;
		for (Question question : exam.getQuestions()) {
			if (isAnswerCorrect(user, question)) {
				score++;
			}
		}
		
		return score;
	}

	private void saveScore(User user, Exam exam, int scorePercent) {
		UserExam ue = userExamDao.findByUserAndExam(user, exam);
		ue.setEndDate(new Date());
		ue.setScore(scorePercent);
		userExamDao.save(ue);
	}
	
	public Collection<QuestionDTO> loadExamSummary(Exam exam, User user) {

		Collection<QuestionDTO> dtos = Lists.newArrayList();
		for (Question question : exam.getQuestions()) {
			dtos.add(new QuestionDTO(question.getId(), question.getTitle(), question.getOrder(),
					isAnswerCorrect(user, question)));
		}

		return dtos;
	}

	private boolean isAnswerCorrect(User user, Question question) {
		List<Long> correctAnswerIds = answerDao.findCorrectAnswers(question);
		List<Long> userAnswerIds = answerDao.findAllByUserAndQuestion(user, question);
		
		SetView<Long> diff = Sets.difference(Sets.newHashSet(correctAnswerIds), Sets.newHashSet(userAnswerIds));
		
		return diff.isEmpty();
	}
}
