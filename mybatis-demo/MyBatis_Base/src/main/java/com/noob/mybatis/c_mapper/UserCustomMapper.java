package com.noob.mybatis.c_mapper;

import java.util.List;

import com.noob.mybatis.model.User;
import com.noob.mybatis.model.UserCustom;
import com.noob.mybatis.model.UserQueryVO;

public interface UserCustomMapper {
	// 定义查询方法
	public List<UserCustom> findUserByUnion(UserQueryVO userQueryVO);
	
	// 查询符合指定条件的用户个数
	public int getUserCountByUnion(UserQueryVO userQueryVO);
	
	// 根据用户id查找用户信息 
 	public User findUserById(int id);
 	
 	// 根据用户姓名关键字查找用户信息（返回的可能是一个列表）
 	public List<User> findUserByName(UserQueryVO userQueryVO);

 	//通过ResultMap映射根据用户名称关键字查找用户信息
 	public List<User> findUserListByResultMap(UserQueryVO userQueryVO);
 	
 	// 根据提供的用户id列表查找用户信息 
  	public List<UserCustom> findUserListByIds(UserQueryVO userQueryVO);
	
}
