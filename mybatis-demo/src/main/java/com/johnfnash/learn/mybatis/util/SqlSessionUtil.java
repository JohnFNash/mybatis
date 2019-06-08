package com.johnfnash.learn.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	private final static String RESOURCE = "SqlMapConfig.xml";
	
	public static SqlSession getSession() throws IOException {
		// 通过流将核心配置文件加载进来
        InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
        // 通过配置文件创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过会话工厂获取会话
        SqlSession session = factory.openSession();
        return session;
	}
	
}
