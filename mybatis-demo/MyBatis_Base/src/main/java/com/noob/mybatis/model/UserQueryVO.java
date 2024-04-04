package com.noob.mybatis.model;

import java.util.List;

public class UserQueryVO {
	
	// 此处定义所需要查询的条件
	private UserCustom userCustom;
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	// 还可封装其他的查询条件：例如订单信息、商品信息等内容

}
