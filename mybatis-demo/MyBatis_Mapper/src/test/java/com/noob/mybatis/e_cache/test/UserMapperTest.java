package com.noob.mybatis.e_cache.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.e_cache.UserMapper;
import com.noob.mybatis.model.User;

public class UserMapperTest {
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
	public void testFindOrderUserLazyLoading() {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = userMapper1.findUserById(10);
		System.out.println("第一次查找的数据：" + user1.getUsername());
		// 第一次查找完数据之后关闭sqlSession1（一级缓存被清空）
		sqlSession1.close();

		// 第二次查找相同的数据，开启新的sqlSession进行操作
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(10);
		System.out.println("第二次查找的数据：" + user2.getUsername());
		// 查找完数据之后关闭sqlSession1（一级缓存被清空）
		sqlSession2.close();

		// 第三次查找相同的数据，开启新的sqlSession进行操作
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user3 = userMapper3.findUserById(10);
		System.out.println("第三次查找的数据：" + user3.getUsername());
		// 查找完数据之后关闭sqlSession1（一级缓存被清空）
		sqlSession3.close();
	}
}
