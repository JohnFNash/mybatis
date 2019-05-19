package com.johnfnash.learn.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.johnfnash.learn.mybatis.domain.User;

public class Test2 {

	private SqlSession session = null;
	private SqlSessionFactory factory = null;
	private final String STATEMENT = "com.johnfnash.learn.mybatis.mapper.UserMapper.getUser";// 映射sql的标识字符串
	private final String UPDATE_STATEMENT = "com.johnfnash.learn.mybatis.mapper.UserMapper.updateUser";// 映射sql的标识字符串

	@Before
	public void init() throws IOException {
		// 创建输入流
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		// 创建会话
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		// openSession(true)表示自动提交
		session = factory.openSession(true);
	}
	
	@Test
	public void test() throws IOException {
		User user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		session.close();
	}
	
	@Test
	public void test2() throws IOException {
		User user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		//如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
		//更新user的信息
		user.setAge(35);
		session.update(UPDATE_STATEMENT, user);
		// 执行 commit 去清空缓存
		session.commit();
		
		//第二次发起请求
		user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		session.close();
	}
	
	@Test
	public void test3() throws IOException {
		User user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		System.out.println(session.hashCode());
		
		//同一个sqlsesseion对象，使用一级缓存，查询一次 
		//session.close后再获取是新对象
		session.close();//关不关都一样
		
		session = factory.openSession(true); //获取新的session
		user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		System.out.println(session.hashCode());
		
		session.close();
	}
	
}
