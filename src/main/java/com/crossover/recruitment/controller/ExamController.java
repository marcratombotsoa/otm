package com.crossover.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crossover.recruitment.dao.ExamDao;
import com.crossover.recruitment.model.Exam;

@Controller
public class ExamController {

	@Autowired
	private ExamDao examDao;
	
	@RequestMapping(value="/exam", method = RequestMethod.GET)
	public String exam(ModelMap map, @RequestParam(value = "id", required = false) Long examId) {
		Exam exam = null;
		if (examId == null) {
			// Load all exams from the database and add a menu for the user to choose which exam he wants to take
			exam = examDao.findOne(1l);
		} else {
			exam = examDao.findOne(examId);
		}
		
		map.put("exam", exam);
		
		return "exam/index";
	}
}
