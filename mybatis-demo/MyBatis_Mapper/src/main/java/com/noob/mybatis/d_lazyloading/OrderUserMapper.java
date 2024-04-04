package com.noob.mybatis.d_lazyloading;

import java.util.List;

import com.noob.mybatis.model.Orders;

public interface OrderUserMapper {
	// 使用association实现延迟加载
	public List<Orders> findOrderUserLazyLoading();
}
