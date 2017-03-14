package com.co.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.rc.dao.ExamDao;
import com.co.rc.model.Exam;

@Controller
public class HomeController {
	
	@Autowired
	private ExamDao examDao;
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(ModelMap map, @RequestParam(value = "id", required = false) Long examId) {
		Exam exam = null;
		if (examId != null) {
			exam = examDao.findOne(examId);
		}
		
		if (exam == null) {
			// show error message to tell that the exam is not found
			map.put("exam", new Exam(null, "N/A", "N/A", 0l));
		} else {
			map.put("exam", exam);
		}
		
		return "home/index";
	}
}
