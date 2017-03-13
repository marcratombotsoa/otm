package com.crossover.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crossover.recruitment.dao.UserDao;

@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="rekety", method = RequestMethod.GET)
	public String home(ModelMap map) {
		map.put("username", userDao.findByFirstName("Marc").getUserName());
		return "home/index";
	}
}
