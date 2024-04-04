package com.noob.mybatis.c_mapper_test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.c_mapper.UserCustomMapper;
import com.noob.mybatis.model.User;
import com.noob.mybatis.model.UserCustom;
import com.noob.mybatis.model.UserQueryVO;

public class UserCustomMapperTest {

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
	public void testFindUserByUnion() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		// 定义查找条件
		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("小明");
		userQueryVO.setUserCustom(userCustom);
		// 查找内容
		List<UserCustom> list = userCustomMapper.findUserByUnion(userQueryVO);
		System.out.println(list);
		// 关闭连接
		sqlSession.close();
	}
	
	@Test
	public void testFindUserCountByUnion() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		// 定义查找条件
		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("小明");
		userQueryVO.setUserCustom(userCustom);
		// 查找内容
		int count = userCustomMapper.getUserCountByUnion(userQueryVO);
		System.out.println("查找到的用户个数："+count);
		// 关闭连接
		sqlSession.close();
	}
	
	@Test
	public void testFindUserById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		User findUser = userCustomMapper.findUserById(25);
		System.out.println(findUser);
		// 关闭连接
		sqlSession.close();
	}
	
	@Test
	public void testFindUserByName() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		// 定义查找条件
		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("小明");
		userQueryVO.setUserCustom(userCustom);
		// 查找内容
		List<User> list = userCustomMapper.findUserByName(userQueryVO);
		System.out.println(list);
		// 关闭连接
		sqlSession.close();
	}
	
	@Test
	public void testFindUserListByResultMap() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		// 定义查找条件
		UserQueryVO userQueryVO = new UserQueryVO();
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername("小明");
		userQueryVO.setUserCustom(userCustom);
		// 查找内容
		List<User> list = userCustomMapper.findUserListByResultMap(userQueryVO);
		System.out.println(list);
		// 关闭连接
		sqlSession.close();
	}
	
	
	@Test
	public void testFindUserListByIds() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过SqlSession对象获取UserMapper对象
		UserCustomMapper userCustomMapper = sqlSession.getMapper(UserCustomMapper.class);
		// 定义查找条件
		UserQueryVO userQueryVO = new UserQueryVO();
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		userQueryVO.setIds(ids);
		// 查找内容
		List<UserCustom> list = userCustomMapper.findUserListByIds(userQueryVO);
		System.out.println(list);
		// 关闭连接
		sqlSession.close();
	}
	
}
