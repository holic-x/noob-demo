package com.noob.mybatis.a_original_dao;

import com.noob.mybatis.model.User;

public interface UserDAO {
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User findUserById(int id);
}
