package com.crossover.recruitment.dao;

import org.springframework.data.repository.CrudRepository;

import com.crossover.recruitment.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	User findByFirstName(String firstName);
	
}
