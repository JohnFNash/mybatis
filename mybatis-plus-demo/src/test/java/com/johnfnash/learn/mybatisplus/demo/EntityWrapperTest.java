package com.johnfnash.learn.mybatisplus.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.johnfnash.learn.mybatisplus.demo.entity.Employee;
import com.johnfnash.learn.mybatisplus.demo.mapper.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class EntityWrapperTest {
	
	@Autowired
    private EmployeeMapper employeeMapper;
	
	@Test
	public void test() {
		List<Employee> employees = 
				employeeMapper.selectPage(new Page<Employee>(1,3), 
						new EntityWrapper<Employee>()
						.between("age", 18, 50)
						.eq("gender", 2)
						.like("last_name", "老师")
						.orNew()
						.like("email", "a")
						.orderBy("age"));
		System.out.println(employees);
	}
	
}
