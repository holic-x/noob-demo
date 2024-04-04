package com.noob.mybatis.a_original_dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.noob.mybatis.model.User;

public class UserDAOImpl implements UserDAO{
	
	private SqlSessionFactory sqlSessionFactory;
	
	public UserDAOImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public void addUser(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void updateUser(User user) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("test.updateUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void deleteUser(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("test.deleteUser", id);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public User findUserById(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User findUser = sqlSession.selectOne("test.findUserById", id);
		sqlSession.close();
		return findUser;
	}
}
