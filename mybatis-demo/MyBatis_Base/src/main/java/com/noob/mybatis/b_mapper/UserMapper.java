package com.noob.mybatis.b_mapper;

import com.noob.mybatis.model.User;

public interface UserMapper {
	// 此处简单定义两个方法进行测试
	public void addUser(User user);
	public User findUserById(int id);
}
