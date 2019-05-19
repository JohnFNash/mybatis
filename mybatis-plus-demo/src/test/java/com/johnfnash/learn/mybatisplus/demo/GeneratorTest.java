package com.johnfnash.learn.mybatisplus.demo;

import org.junit.Test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorTest {

	@Test
    public void testGenerator(){
		 //1、全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//开启AR模式
              .setAuthor("zhu")//设置作者
              //生成路径(一般都是生成在此项目的src/main/java下面)
              .setOutputDir("E:\\workspace\\java\\mybatis\\mybatis-plus-demo\\src\\main\\java")
              .setFileOverride(true)//第二次生成会把第一次生成的覆盖掉
              .setIdType(IdType.AUTO)//主键策略
              .setServiceName("%sService")//生成的service接口名字首字母是否为I，这样设置就没有I
              .setBaseResultMap(true)//生成resultMap
              .setBaseColumnList(true);//在xml中生成基础列
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//数据库类型
                        .setDriverName("com.mysql.jdbc.Driver")
                        .setUrl("jdbc:mysql://localhost:3306/数据库名")
                        .setUsername("数据库用户名")
                        .setPassword("数据库密码");
        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//开启全局大写命名
                      .setDbColumnUnderline(true)//表名字段名使用下划线
                      .setNaming(NamingStrategy.underline_to_camel)//下划线到驼峰的命名方式
                      .setTablePrefix("tb_")//表名前缀
                      .setEntityLombokModel(true)//使用lombok
                      .setInclude("表1","表2");//逆向工程使用的表
        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.zhu.mpg")//设置包名的parent
                     .setMapper("mapper")
                     .setService("service")
                     .setController("controller")
                     .setEntity("entity")
                     .setXml("mapper");//设置xml文件的目录
        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                     .setDataSource(dataSourceConfig)
                     .setStrategy(strategyConfig)
                     .setPackageInfo(packageConfig);
        //6、执行
        autoGenerator.execute();
	}
	
}
