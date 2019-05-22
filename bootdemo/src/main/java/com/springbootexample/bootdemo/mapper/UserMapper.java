package com.springbootexample.bootdemo.mapper;

import com.springbootexample.bootdemo.domain.dao.UserDao;
import com.springbootexample.bootdemo.dto.UserDto;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
	//	@Select("select * from User where IsDeleted=false")
	List<UserDto> getAllUsers();

	int createUser(@Param("user") UserDao user);
}
