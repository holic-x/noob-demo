package com.noob.mybatis.c_many_to_many;

import java.util.List;

import com.noob.mybatis.model.User;

public interface UserDetailMapper {
	
	// 根据ResultMap实现多对多查询
	public List<User> findUserDetailByResultMap();

}
