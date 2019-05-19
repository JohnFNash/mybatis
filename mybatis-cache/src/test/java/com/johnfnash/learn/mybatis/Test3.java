package com.johnfnash.learn.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.johnfnash.learn.mybatis.domain.User;

public class Test3 {

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
		tt1();
		System.out.println(session.hashCode());
		
		session.close();// 使用二级的前提是前一个session执行close()方法
		// 不close，仍然两次查询
		tt2();
		System.out.println(session.hashCode());
	}

	private void tt1() {
		User user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
	}

	private void tt2() {
		session = factory.openSession(true); // 关闭了，需要重新获取
		User user = session.selectOne(STATEMENT, 1);
		System.out.println(user);
		
		session.close();
	}
	
	@Test
	public void test2() {
		SqlSession session1 = factory.openSession();
		SqlSession session2 = factory.openSession();
		SqlSession session3 = factory.openSession();
		
		// 第一次发起请求，查询id为1的用户
		User user1 = session1.selectOne(STATEMENT, 1);
		System.out.println(user1);

		//这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
		session1.close();
		
		//使用session3执行commit()操作
		User user3 = session3.selectOne(STATEMENT, 1);
		System.out.println(user3);
		user3.setAge(new Random().nextInt(60));
		session3.update(UPDATE_STATEMENT, user3);
		//执行提交，清空UserMapper下边的二级缓存
		session3.commit();
		session3.close();

		// 第二次发起请求，查询id为1的用户
		User user2 = session2.selectOne(STATEMENT, 1);
		System.out.println(user2);
		session2.close();
	}

}
