package com.crossover.recruitment.dao;

import org.springframework.data.repository.CrudRepository;

import com.crossover.recruitment.model.Exam;

public interface ExamDao extends CrudRepository<Exam, Long> {
	
	Exam findById(Long id);

}
