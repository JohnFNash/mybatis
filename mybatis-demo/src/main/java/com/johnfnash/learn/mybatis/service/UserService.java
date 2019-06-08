package com.johnfnash.learn.mybatis.service;

import java.io.IOException;

import com.github.pagehelper.PageInfo;
import com.johnfnash.learn.mybatis.entity.User;

public interface UserService {

	User findUserById(Integer id) throws IOException;
	
	PageInfo<User> findUserByUserName(String userName, int pageNo, int pageSize) throws IOException;
	
}
