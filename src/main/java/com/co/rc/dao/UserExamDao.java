package com.co.rc.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.rc.model.Exam;
import com.co.rc.model.User;
import com.co.rc.model.UserExam;

public interface UserExamDao extends CrudRepository<UserExam, Long> {

	UserExam findByUserAndExam(User user, Exam exam);
	
}
