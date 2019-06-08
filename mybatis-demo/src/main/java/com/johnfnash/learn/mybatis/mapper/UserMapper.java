package com.johnfnash.learn.mybatis.mapper;

import java.util.List;

import com.johnfnash.learn.mybatis.entity.User;

public interface UserMapper {

	User findUserById(Integer id);
	
	List<User> findUserByUserName(String userName);
	
}
