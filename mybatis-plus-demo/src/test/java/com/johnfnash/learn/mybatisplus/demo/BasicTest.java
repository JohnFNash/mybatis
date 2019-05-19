package com.johnfnash.learn.mybatisplus.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.johnfnash.learn.mybatisplus.demo.entity.Employee;
import com.johnfnash.learn.mybatisplus.demo.mapper.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class BasicTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private EmployeeMapper employeeMapper;
	
	@Test
	public void testDataSource() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testInsert() {
		Employee employee = new Employee();
		employee.setLastName("东方不败");
		employee.setEmail("dfbb@163.com");
        employee.setGender(1);
        employee.setAge(20);
        employeeMapper.insert(employee);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(employee.getId());
	}
	
	@Test
	public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("更新测试");
        employeeMapper.updateById(employee);//根据id进行更新，没有传值的属性就不会更新
        //employeeMapper.updateAllColumnById(employee);//根据id进行更新，没传值的属性就更新为null
	}
	
	@Test
	public void testSelectById() {
		Employee employee = employeeMapper.selectById(1);
		System.out.println(employee);
	}
	
	@Test
	public void testSelectByOne() {
		Employee employeeCondition = new Employee();
		employeeCondition.setId(1);
		employeeCondition.setLastName("更新测试");
		//若是数据库中符合传入的条件的记录有多条，那就不能用这个方法，会报错
		Employee employee = employeeMapper.selectOne(employeeCondition);
		System.out.println(employee);
	}
	
	@Test
	public void testSelectByMap() {
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("last_name","更新测试");//写表中的列名
		columnMap.put("gender","1");
		List<Employee> employees = employeeMapper.selectByMap(columnMap);
		System.out.println(employees.size());
	}
	
	@Test
	public void testSelectBatchByIds() {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(2);
		idList.add(3);
		
		List<Employee> employees = employeeMapper.selectBatchIds(idList);
		System.out.println(employees);
	}
	
	@Test
	public void testSelectByPage() {
		Page<Employee> page = new Page<>(1, 2);
		List<Employee> employees = employeeMapper.selectPage(page,null);
		System.out.println(employees);
		
        System.out.println("================= 相关的分页信息 ==================");
        System.out.println("总条数:" + page.getTotal());
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("总页数:" + page.getPages());
        System.out.println("每页显示条数:" + page.getSize());
        System.out.println("是否有上一页:" + page.hasPrevious());
        System.out.println("是否有下一页:" + page.hasNext());
        //还可以将查询到的结果set进page对象中
        page.setRecords(employees);
	}
	
	@Test
	public void testDeleteById() {
		employeeMapper.deleteById(3);
	}
	
	@Test
	public void testDeleteByMap() {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("gender",2);
		columnMap.put("age",25);
		employeeMapper.deleteByMap(columnMap);
	}
	
	@Test
	public void testDeleteBatchIds() {
		 List<Integer> idList = new ArrayList<>();
		 idList.add(4);
		 idList.add(5);
		 employeeMapper.deleteBatchIds(idList);
	}
	
}
