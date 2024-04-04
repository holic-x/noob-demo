package com.noob.mybatis.a_original_dao.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.a_original_dao.UserDAO;
import com.noob.mybatis.a_original_dao.UserDAOImpl;
import com.noob.mybatis.model.User;

public class UserDAOTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before // 在所有测试代码执行之前执行该方法
	public void before() throws Exception {
		// a.定义mybatis配置文件,并得到配置文件的流
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test() {
		// 创建UserDAO实现类对象进行测试
		UserDAO userDAO = new UserDAOImpl(sqlSessionFactory);
		User findUser = userDAO.findUserById(25);
		System.out.println(findUser);
	}

}
