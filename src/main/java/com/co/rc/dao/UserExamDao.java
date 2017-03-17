package com.co.rc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.rc.model.Exam;
import com.co.rc.model.User;
import com.co.rc.model.UserExam;

public interface UserExamDao extends CrudRepository<UserExam, Long> {

	UserExam findByUserAndExam(User user, Exam exam);
	
	@Query("select ue from UserExam ue where ue.user.id = ?1 and ue.exam.id = ?2")
	UserExam findByUserIdAndExamId(Long userId, Long examId);
	
}
