package com.springbootexample.bootdemo.controller;

import com.springbootexample.bootdemo.domain.dao.UserDao;
import com.springbootexample.bootdemo.dto.UserDto;
import com.springbootexample.bootdemo.service.UserService;
import com.springbootexample.bootdemo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public HttpEntity<?> getUsers() {
		return new HttpEntity<>(userService.getUsers());
	}

	@PostMapping("/create")
	public HttpEntity<?> createUser(@RequestBody UserVo userVo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new HttpEntity<>("参数错误");
		}
		UserDao dao = new UserDao();
		dao.setEmailAddress(userVo.getEmailAddress());
		dao.setUsername(userVo.getUsername());
		dao.setName(userVo.getName());
		dao.setPassword(userVo.getPassword());
		int result = userService.createUser(dao);
		return new HttpEntity<>(result);
	}

}
