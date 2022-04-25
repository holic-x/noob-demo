package com.noob.mybatis.base.a_quickstart.test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.noob.mybatis.model.User;

/**
 * MyBatis入门程序：用户的增删改查测试
 */

public class UserTest {

	// 优化测试，将公共代码提取出来
	SqlSessionFactory sqlSessionFactory;
	@Before  // 在所有测试代码执行之前执行该方法
	public void before() throws Exception {
		// a.定义mybatis配置文件,并得到配置文件的流
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
				
		// b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testUpdateUser() {
		/**
		 * 需要注意的是此处测试是直接创建一个新的User对象，通过User对象
		 * id进行修改操作，一般情况下是先根据id查找相关用户再进行修改
		 */
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建要修改的用户信息（通过指定的用户id修改数据）
		User user = new User();
		user.setId(32);
		user.setUsername("小傻子");
		user.setAddress("王者峡谷");
		// 通过sqlSession修改用户信息
		sqlSession.update("test.updateUser",user);
		// 提交事务、关闭连接
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testDeleteUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过sqlSession执行删除数据
		sqlSession.update("test.deleteUser", 30);
		// 提交事务、释放连接
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testInsertUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建要添加的用户信息
		User user = new User();
		user.setSex("2");
		user.setUsername("狄仁杰");
		user.setBirthday(new Date());
		user.setAddress("敌方水晶");
		// 通过sqlSession添加用户信息
		sqlSession.insert("test.insertUser",user);
		// 查阅当前插入用户的id
		System.out.println("当前插入用户id："+user.getId());
		// 提交事务、关闭连接（数据库事务默认提交方式是关闭的因此需要手动提交数据）
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testFindUserByUsername() {
		
		// 通过会话工厂获取SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> list = sqlSession.selectList("test.findUserByName","小明");
		System.out.println(list);
		sqlSession.close();
		
	}

	@Test
	public void testFindUserById() throws Exception {
		// a.定义mybatis配置文件,并得到配置文件的流
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		// b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// c.通过会话工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		/**
		 * d.通过sqlSession操作数据库
		 * 参数1:映射文件中的statement的id,即 namespace+"."+statment的id
		 * 参数2:指定和映射文件中parameterType所匹配的类型
		 * selectOne 就是查询一个对象或者是一个数据
		 */
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		// e.释放资源
		sqlSession.close();
	}
}
