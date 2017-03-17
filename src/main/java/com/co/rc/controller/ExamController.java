package com.co.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.rc.dao.ExamDao;
import com.co.rc.dao.UserDao;
import com.co.rc.model.Exam;
import com.co.rc.model.User;
import com.co.rc.service.ExamService;
import com.google.common.base.Joiner;

@Controller
public class ExamController {

	@Autowired
	private ExamDao examDao;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private UserDao userDao;
	
	private User user;
	private String username;
	
	@RequestMapping(value="/exam", method = RequestMethod.GET)
	public String examList(ModelMap map) {
		initUser(map);
		loadAllExams(map);

		return "exam/index";
	}
	
	@RequestMapping(value="/exam/{id}", method = RequestMethod.GET)
	public String loadExam(ModelMap map, @PathVariable(value = "id", required = true) Long examId) {
		initUser(map);
		Exam exam = examDao.findOne(examId);
		map.put("exam", exam);
		map.put("examParamPresent", true);
		
		return "exam/index";
	}
	
	@RequestMapping(value="/exam/timer/{examId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getRemainingTimeInSeconds(ModelMap map, @PathVariable(value = "examId", required = true) Long examId) {
		initUser(map);
		Long remaining = examService.computeRemainingTimeInMilliseconds(user.getId(), examId); 
		return String.valueOf(remaining);
	}
	
	private void initUser(ModelMap map) {
		String u = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userDao.findByUserName(u);
		username = Joiner.on(" ").join(user.getFirstName(), user.getLastName());
		map.put("username", username);
	}

	private void loadAllExams(ModelMap map) {
		Iterable<Exam> exams = examDao.findAll();
		
		map.put("exams", exams);
		map.put("examParamPresent", false);
	}
}
