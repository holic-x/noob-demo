package com.noob.mybatis.c_many_to_many.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.c_many_to_many.UserDetailMapper;
import com.noob.mybatis.model.User;

public class UserDetailTest {

	// 优化测试，将公共代码提取出来
	SqlSessionFactory sqlSessionFactory;

	@Before // 在所有测试代码执行之前执行该方法
	public void before() throws Exception {
		// a.定义mybatis配置文件,并得到配置文件的流
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserDetailByResultMap() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserDetailMapper userDetailMapper = sqlSession.getMapper(UserDetailMapper.class);
		List<User> list = userDetailMapper.findUserDetailByResultMap();
		list.forEach(System.out::println);
		// 关闭连接
		sqlSession.close();
	}

}
