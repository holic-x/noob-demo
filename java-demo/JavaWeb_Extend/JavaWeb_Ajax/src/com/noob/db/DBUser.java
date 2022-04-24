package com.noob.db;

import java.util.ArrayList;
import java.util.List;

import com.noob.model.User;

/**       
 * <p>project_name:JavaWeb_Ajax</p>
 * <p>package_name:com.noob.db.DBUser</p>
 * <p>description：</p>
 * <p> date:2018年8月1日下午6:04:23 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 892944741@qq.com All Rights Reserved. </p>    
 */

public class DBUser {
	
	// 模拟数据库保存User数据
	private static List<User> list = new ArrayList<>();
	
	static {
		list.add(new User("haha","haha"));
		list.add(new User("xixi","xixi"));
		list.add(new User("bibi","bibi"));
	}
	
	// 验证用户是否存在
	public static boolean checkUserName(String name) {
		boolean flag = false;
		for(int i=0;i<list.size();i++) {
			User user = list.get(i);
			if(user.getUsername().equals(name)) {
				flag=true;
			}
		}
		return flag;
	}
}


