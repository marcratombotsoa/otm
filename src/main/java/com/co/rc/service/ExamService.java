package com.co.rc.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.co.rc.dao.AnswerDao;
import com.co.rc.dao.UserExamDao;
import com.co.rc.model.Exam;
import com.co.rc.model.Question;
import com.co.rc.model.User;
import com.co.rc.model.UserExam;
import com.co.rc.model.dto.QuestionDTO;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ExamService {
	
	@Autowired
	private AnswerDao answerDao;
	
	@Autowired
	private UserExamDao userExamDao;
	
	public Collection<QuestionDTO> loadSummary(Exam exam, User user) {

		Collection<QuestionDTO> result = Lists.newArrayList();
		
		List<Object[]> answers = answerDao.findUserAnswerByQuestion(exam.getId(), user.getId());
		Map<Long, Long> answerByQuestion = mergeResults(answers);
		for (Question question : exam.getQuestions()) {
			boolean answered = answerByQuestion.get(question.getId()) != null && answerByQuestion.get(question.getId()) > 0l;
			result.add(new QuestionDTO(question.getId(), question.getTitle(), question.getOrder(), answered));
		}
		
		return result;
	}
	
	private Map<Long, Long> mergeResults(List<Object[]> answers) {
		Map<Long, Long> result = Maps.newHashMap();
		
		for (Object[] entry : answers) {
			result.put(Long.valueOf(entry[0].toString()), Long.valueOf(entry[0].toString()));
		}
		
		return result;
	}
	
	public void saveUserAnswers(User user, Question question, String data) {
		if (StringUtils.isEmpty(data)) {
			return;
		}
		
		List<Long> answers = answerDao.findAllByUserAndQuestion(user, question);
		if (!CollectionUtils.isEmpty(answers)) {
			answerDao.removeUserAnswers(user.getId(), answers);
		}
		
		Iterable<String> map = Splitter.on("&").split(data);
		for (String entry : map) {
			String[] keyEntry = entry.split("=");
			try {
				Long.valueOf(keyEntry[0]);
				answerDao.insertUserAnswer(user.getId(), Long.valueOf(keyEntry[1]));
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}
	
	public UserExam findByUserAndExam(User user, Exam exam) {
		UserExam ue = userExamDao.findByUserAndExam(user, exam);
		
		if (ue == null) {
			ue = new UserExam();
			ue.setUser(user);
			ue.setExam(exam);
		}
		
		return ue;
	}

	public int findAnsweredQuestions(Collection<QuestionDTO> questions) {
		return Collections2.filter(questions, new Predicate<QuestionDTO>() {

			@Override
			public boolean apply(QuestionDTO input) {
				return input.isAnswered();
			}
		}).size();
	}
}
