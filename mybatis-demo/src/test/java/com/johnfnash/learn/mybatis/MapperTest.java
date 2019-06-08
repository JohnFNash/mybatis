package com.johnfnash.learn.mybatis;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.johnfnash.learn.mybatis.entity.User;
import com.johnfnash.learn.mybatis.service.UserService;
import com.johnfnash.learn.mybatis.service.UserServiceImpl;

public class MapperTest {

	@Test
	public void testFindUserById() throws IOException {
		UserService service = new UserServiceImpl();
		User user = service.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByName() throws IOException {
		UserService service = new UserServiceImpl();
		PageInfo<User> pageInfo = service.findUserByUserName("张", 1, 1);
		List<User> users = pageInfo.getList();
		if(users != null) {
			users.stream().forEach(System.out::println);
		}
	}
	
}
