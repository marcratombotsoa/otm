package com.co.rc.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.rc.model.User;

public interface UserDao extends CrudRepository<User, Long> {

	User findByFirstName(String firstName);
	User findByUserName(String userName);
}
