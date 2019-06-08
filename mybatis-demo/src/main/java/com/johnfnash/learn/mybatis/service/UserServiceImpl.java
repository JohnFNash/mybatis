package com.johnfnash.learn.mybatis.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.johnfnash.learn.mybatis.entity.User;
import com.johnfnash.learn.mybatis.mapper.UserMapper;
import com.johnfnash.learn.mybatis.util.SqlSessionUtil;

public class UserServiceImpl implements UserService {

	@Override
	public User findUserById(Integer id) throws IOException {
		SqlSession session = SqlSessionUtil.getSession();
		//通过getMapper方法来实例化接口
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.findUserById(id);
		session.close();
		return user;
	}

	@Override
	public PageInfo<User> findUserByUserName(String userName, int pageNo, int pageSize) throws IOException {
		PageHelper.startPage(pageNo, pageSize);
		
		SqlSession session = SqlSessionUtil.getSession();
		//通过getMapper方法来实例化接口
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> users = mapper.findUserByUserName(userName);
		session.close();
		
		PageInfo<User> pageInfo = new PageInfo<>(users);
		return pageInfo;
	}

}
