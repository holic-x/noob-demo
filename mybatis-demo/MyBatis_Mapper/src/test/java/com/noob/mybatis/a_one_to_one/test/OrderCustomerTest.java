package com.noob.mybatis.a_one_to_one.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.a_one_to_one.OrderCustomMapper;
import com.noob.mybatis.model.OrderCustom;
import com.noob.mybatis.model.Orders;

public class OrderCustomerTest {

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
	public void testFindOrderCustomByResultType() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		OrderCustomMapper orderCustomMapper = sqlSession.getMapper(OrderCustomMapper.class);
		List<OrderCustom> list = orderCustomMapper.findOrderCustomByResultType();
		list.forEach(System.out::println);
		// 关闭连接
		sqlSession.close();
	}
	
	@Test
	public void testFindOrderCustomByResultMap() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		OrderCustomMapper orderCustomMapper = sqlSession.getMapper(OrderCustomMapper.class);
		List<Orders> list = orderCustomMapper.findOrderCustomByResultMap();
		list.forEach(System.out::println);
		// 关闭连接
		sqlSession.close();
	}

}
