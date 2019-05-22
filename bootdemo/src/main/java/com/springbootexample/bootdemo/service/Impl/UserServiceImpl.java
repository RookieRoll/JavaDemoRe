package com.springbootexample.bootdemo.service.Impl;

import com.springbootexample.bootdemo.domain.dao.UserDao;
import com.springbootexample.bootdemo.dto.UserDto;
import com.springbootexample.bootdemo.mapper.UserMapper;
import com.springbootexample.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDto> getUsers() {
		return userMapper.getAllUsers();
	}

	@Override
	public int createUser(UserDao userDao) {
		UserDao dao = userDao;

		dao.setCreationTime(new Date());
		dao.setLastModificationTime(new Date());
		dao.setUserType(1);
		userMapper.createUser(dao);
		return dao.getId();
	}
}
