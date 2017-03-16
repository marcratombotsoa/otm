package com.co.rc.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.rc.dao.AnswerDao;
import com.co.rc.dao.CustomExamDao;
import com.co.rc.dao.CustomQuestionDao;
import com.co.rc.dao.UserDao;
import com.co.rc.model.Exam;
import com.co.rc.model.Question;
import com.co.rc.model.User;
import com.co.rc.model.dto.QuestionDTO;
import com.co.rc.service.ExamService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

@Controller
public class QuestionController {

	@Autowired
	private CustomExamDao cExamDao;
	
	@Autowired
	private CustomQuestionDao cQuestionDao;
	
	@Autowired
	private AnswerDao answerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ExamService examService;
	
	private Exam exam;
	private Question question;
	private String username;
	private User user;
	
	@RequestMapping(value="/assessment/question", method = RequestMethod.POST)
	public String loadExamQuestion(ModelMap map, @RequestBody String postData) {
		initUser(map);
		examService.saveUserAnswers(user, question, postData);
		Long questionId = findQuestionId(postData);
		
		question = cQuestionDao.findByIdAndFetchAnswers(questionId);
		map.put("question", question);
		map.put("exam", exam);
		
		List<Long> answers = answerDao.findAllByUserAndQuestion(user, question);
		map.put("selectedAnswers", answers);
		
		return "exam/questions";
	}
	
	@RequestMapping(value="/assessment/question/{id}", method = RequestMethod.GET)
	public String loadExamQuestion(ModelMap map, @PathVariable("id") Long questionId) {
		initUser(map);
		
		question = cQuestionDao.findByIdAndFetchAnswers(questionId);
		map.put("question", question);
		map.put("exam", exam);
		
		List<Long> answers = answerDao.findAllByUserAndQuestion(user, question);
		map.put("selectedAnswers", answers);
		
		return "exam/questions";
	}
	
	@RequestMapping(value="/assessment/question", method = RequestMethod.POST, params = {"validate"})
	public String checkAllAnswers(ModelMap map, @RequestBody String postData) {
		initUser(map);
		examService.saveUserAnswers(user, question, postData);
		
		Collection<QuestionDTO> questions = examService.loadSummary(exam, user);
		int answered = examService.findAnsweredQuestions(questions);
		map.put("exam", exam);
		map.put("questions", questions);
		map.put("rate", answered + " / " + questions.size());
		return "exam/summary";
	}
	
	@RequestMapping(value="/assessment/{id}", method = RequestMethod.GET)
	public String loadQuestions(ModelMap map, @PathVariable(value = "id", required = true) Long examId) {
		initUser(map);
		exam = cExamDao.findByIdAndFetchQuestions(examId);
		answerDao.resetUserAnswers(exam.getId(), user.getId());
		map.put("exam", exam);
		
		question = cQuestionDao.findByIdAndFetchAnswers(exam.getQuestions().first().getId());
		map.put("question", question);
		
		List<Long> answers = answerDao.findAllByUserAndQuestion(user, question);
		map.put("selectedAnswers", answers);
		
		return "exam/questions";
	}
	
	private Long findQuestionId(String postData) {
		Iterable<String> keyValues = Splitter.on("&").split(postData);
		
		for (String kv : keyValues) {
			String[] keyEntry = kv.split("=");
			if ("questionToLoad".equals(keyEntry[0])) {
				return Long.valueOf(keyEntry[1]);
			}
		}
		
		return null;
	}

	private void initUser(ModelMap map) {
		String u = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userDao.findByUserName(u);
		username = Joiner.on(" ").join(user.getFirstName(), user.getLastName());
		map.put("username", username);
	}
}
