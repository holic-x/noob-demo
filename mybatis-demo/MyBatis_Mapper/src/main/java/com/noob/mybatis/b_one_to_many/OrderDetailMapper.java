package com.noob.mybatis.b_one_to_many;

import java.util.List;

import com.noob.mybatis.model.Orders;

public interface OrderDetailMapper {
	
	// 根据ResultMap实现一对多查询
	public List<Orders> findOrderDetailByResultMap();

}
