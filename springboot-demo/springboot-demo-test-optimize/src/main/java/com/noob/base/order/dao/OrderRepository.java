package com.noob.base.order.dao;

import com.noob.base.order.model.entity.Order;

import java.util.Optional;

/**
 * DAO 层操作：接口服务定义
 */
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(String orderId);
}