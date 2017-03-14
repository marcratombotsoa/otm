package com.co.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.co.rc.dao.ExamDao;
import com.co.rc.model.Exam;

@Controller
public class ExamController {

	@Autowired
	private ExamDao examDao;
	
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
	
	@RequestMapping(value="/assessment/{id}", method = RequestMethod.GET)
	public String loadQuestions(ModelMap map, @PathVariable(value = "id", required = true) Long examId) {
		initUser(map);
		Exam exam = examDao.findOne(examId);
		map.put("exam", exam);
		
		return "exam/questions";
	}
	
	private void initUser(ModelMap map) {
		map.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
	}

	private void loadAllExams(ModelMap map) {
		Iterable<Exam> exams = examDao.findAll();
		
		map.put("exams", exams);
		map.put("examParamPresent", false);
	}
}
