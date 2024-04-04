package com.noob.mybatis.e_cache;

import com.noob.mybatis.model.User;

public interface UserMapper {
	// 此处简单根据id查找用户信息进行测试
	public User findUserById(int id);
	public void updateUser(User user);
}
