package com.co.rc.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.rc.model.Exam;

public interface ExamDao extends CrudRepository<Exam, Long> {
	
	Exam findById(Long id);

}
