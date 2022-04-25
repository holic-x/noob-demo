package com.noob.mybatis.b_mapper.test;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.b_mapper.UserMapper;
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
	public void testAddUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 创建要添加的用户信息（通过指定的用户id修改数据）
		User user = new User();
		user.setUsername("小傻子");
		user.setAddress("王者峡谷");
		user.setBirthday(new Date());
		user.setSex("2");
		// 通过UserMapper对象执行操作
		userMapper.addUser(user);
		// 提交事务、释放连接
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testFindUserById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 根据id查找用户信息
		User findUser = userMapper.findUserById(25);
		System.out.println("查找到的用户："+findUser);
		// 关闭连接
		sqlSession.close();
	}
}
