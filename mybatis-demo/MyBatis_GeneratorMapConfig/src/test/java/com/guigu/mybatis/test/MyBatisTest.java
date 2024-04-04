package com.guigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.guigu.mybatis.dao.mapper.UserMapper;
import com.guigu.mybatis.model.User;
import com.guigu.mybatis.model.UserExample;
import com.guigu.mybatis.model.UserExample.Criteria;

/**
 * 利用逆向工程生成的数据简单实现增删改查测试
 */

public class MyBatisTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void before() throws IOException {
		// 定义mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 根据流 创建 会话工厂 传入mybatis配置信息到会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testUserSelectByPrimaryKey() {
		// 根据id查找用户信息
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 查找id为1的用户信息
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);
		sqlSession.close();
	}

	@Test
	public void testSelectByExample() {
		// 根据条件查询用户信息
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		/**
		 *  设置查询条件：
		 *  a.创建相应的xxxExample对象
		 *  b.通过相应的Criteria对象构建查询条件（通过createCriteria()方法获取）
		 *  c.通过相应的方法设置查询条件，再将查询条件封装到xxxExample中
		 */
		UserExample userExample = new UserExample();
		// 通过Criteria对象构建查询条件
		Criteria criteria = userExample.createCriteria();
		// 设置相应的查询条件:名字包含小明、性别为2
		criteria.andUsernameEqualTo("张小明");
		criteria.andSexEqualTo("1");
		// 把查询条件封装到UserExample中即可
		userExample.or(criteria);
		// 执行查询
		List<User> list = userMapper.selectByExample(userExample);
		// 关闭连接
		sqlSession.close();
	}

	@Test
	public void testInsert() {
		// 增加用户信息
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 创建用户
		User user = new User();
		user.setUsername("花木兰");
		user.setSex("2");
		user.setAddress("王者峡谷");
		user.setBirthday(new Date());
		userMapper.insert(user);
		// 提交事务、关闭连接
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testUpdateByPrimaryKey() {
		// 根据主键id修改用户信息
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 修改id为28的用户信息
		User user = new User();
		user.setId(28);
		user.setSex("1");
		user.setAddress("绝地求生");
		user.setUsername("aomoll");
		userMapper.updateByPrimaryKey(user);
		// 提交事务，关闭连接
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testDeleteByPrimaryKey() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.deleteByPrimaryKey(28);
		sqlSession.commit();
		sqlSession.close();
	}

}
