package com.co.rc.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.rc.dao.CustomExamDao;
import com.co.rc.dao.UserDao;
import com.co.rc.model.Exam;
import com.co.rc.model.User;
import com.co.rc.model.dto.QuestionDTO;
import com.co.rc.service.ScoreService;
import com.google.common.base.Joiner;

@Controller
public class SummaryController {

	private User user;
	private Exam exam;
	private String username;
	
	@Autowired
	private CustomExamDao cExamDao;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/assessment/terminate/{id}", method = RequestMethod.GET)
	public String finishExam(ModelMap map, @PathVariable("id") Long examId) {
		initUser(map);
		
		exam = cExamDao.findByIdAndFetchQuestions(examId);
		
		Collection<QuestionDTO> questions = scoreService.loadExamSummary(exam, user);
		int answered = scoreService.computeCorrectAnswers(user, exam);
		int scorePercent = scoreService.computeScore(user, exam);
		int seuil = exam.getMinimumPassScorePercent();
		map.put("exam", exam);
		map.put("scorePercent", scorePercent);
		map.put("status", getStatus(scorePercent, seuil));
		map.put("questions", questions);
		map.put("rate", answered + " / " + questions.size());
		
		return "exam/finish";
	}
	
	private String getStatus(int percent, int seuil) {
		if (percent < seuil) {
			return "Failed";
		}
		return "Success";
	}
	
	private void initUser(ModelMap map) {
		String u = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userDao.findByUserName(u);
		username = Joiner.on(" ").join(user.getFirstName(), user.getLastName());
		map.put("username", username);
	}
}
