package com.johnfnash.learn.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.johnfnash.learn.mybatis.entity.User;

public class MybatisTest {

	private final String RESOURCE = "SqlMapConfig.xml";
	private SqlSession session;
	
	@Before
	public void Before() throws IOException {
		// 通过流将核心配置文件加载进来
        InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
        // 通过配置文件创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过会话工厂获取会话
        session = factory.openSession();
	}
	
	@After
	public void after() {
		// 关闭会话
		if(session != null) {
			session.close();
		}
	}
	
	@Test
    public void test1() {
		 // 通过会话执行sql 第一个参数是名称空间+SqlID 第二个参数表示sql执行需要的参数
		User user = session.selectOne("com.johnfnash.learn.mybatis.mapper.UserMapper.findUserById", 1);
		System.out.println(user);
	}
	
	@Test
    public void test2() {
        // 调用User.xml中的魔化查询方法 返回集合
        List<User> selectList = session.selectList("com.johnfnash.learn.mybatis.mapper.UserMapper.findUserByUserName", "张");
        // 循环结果
        System.out.println(selectList.size());
        for (User user : selectList) {
            System.out.println(user.toString());
        }
    }
	
	@Test
    public void test3() {
        // 创建需要插入的User对象
        User user = new User();
        user.setUsername("Jimisun");
        user.setSex("1");
        user.setAddress("北京");
        System.out.println("====插入前的User的id=" + user.getId());
        // 会话调用插入的sql
        session.insert("com.johnfnash.learn.mybatis.mapper.UserMapper.insertUser", user);
        // 默认mybatis自动开启事务,需要手动提交事务
        session.commit();
        System.out.println("====插入后的User的id=" + user.getId());
    }

	@Test
    public void test4() throws IOException {
        // 会话执行sql操作
        session.delete("com.johnfnash.learn.mybatis.mapper.UserMapper.delUserById", 10);
        // 提交事务
        session.commit();
    }

	@Test
    public void test5() throws Exception {
        //创建User对象
        User user = new User ();
        user.setId(1);
        user.setUsername("王麻子222");
        session.update("com.johnfnash.learn.mybatis.mapper.UserMapper.updateUserById", user);
        //提交事务
        session.commit();
    }
	
}
