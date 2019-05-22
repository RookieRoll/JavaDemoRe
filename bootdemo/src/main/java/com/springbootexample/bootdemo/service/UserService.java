package com.springbootexample.bootdemo.service;

import com.springbootexample.bootdemo.domain.dao.UserDao;
import com.springbootexample.bootdemo.dto.UserDto;

import java.util.List;

public interface UserService {
	List<UserDto> getUsers();

	int createUser(UserDao userDao);
}
