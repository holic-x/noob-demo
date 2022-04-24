package com.noob.web.session;

import java.util.ArrayList;
import java.util.List;

/**   
 * 模拟数据库：存储数据信息、查找数据    
 */

public class DBUser {
	
	private static List<User> list = new ArrayList<>();
	
	static {
		list.add(new User("1","haha","haha"));
		list.add(new User("2","xixi","xixi"));
		list.add(new User("3","bibi","bibi"));
	}
	
	// 根据指定的用户名、密码查找是否存在该用户
	public static User findUser(String username,String password) {
		for(User user : list) {
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
				// 查找成功，返回查找的数据
				return user;
			}
		}
		return null;
	}
}


