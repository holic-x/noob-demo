package com.noob.mybatis.a_one_to_one;

import java.util.List;

import com.noob.mybatis.model.OrderCustom;
import com.noob.mybatis.model.Orders;

public interface OrderCustomMapper {
	
	// 根据ResultType实现一对一查询
	public List<OrderCustom> findOrderCustomByResultType();
	
	// 根据ResultMap实现一对一查询
	public List<Orders> findOrderCustomByResultMap();

}
