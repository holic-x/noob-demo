package com.noob.mybatis.d_lazyloading.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.d_lazyloading.OrderUserMapper;
import com.noob.mybatis.model.Orders;
import com.noob.mybatis.model.User;

public class OrderUserTest {

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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		OrderUserMapper orderUserMapper = sqlSession.getMapper(OrderUserMapper.class);
		// 此处加载订单信息，设置断点进行测试
		List<Orders> list =orderUserMapper.findOrderUserLazyLoading();
		System.out.println(list);
		// 此处用户信息按需加载，执行查看结果
		for(Orders order:list) {
			// 获取用户信息进行分析
			User user = order.getUser();
			System.out.println(user);
		}
		// 关闭连接
		sqlSession.close();
	}

}
