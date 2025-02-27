package com.noob.base.demo.idempotence.unique;

import com.noob.base.demo.model.dto.OrderRequest;
import com.noob.base.demo.model.entity.Order;

import java.util.HashSet;
import java.util.Set;

/**
 * ① 基于【唯一标识】实现幂等性
 */
public class OrderService {
    // 缓存订单ID
    private Set<String> orderIdCache = new HashSet<>();

    public Order createOrder(String orderId, OrderRequest request) {
        if (orderIdCache.contains(orderId)) {
            return getOrderById(orderId); // 返回已创建的订单
        }
        Order order = createOrderInDB(request); // 创建订单
        orderIdCache.add(orderId); // 记录订单ID
        return order;
    }

    // 模拟创建订单
    private Order createOrderInDB(OrderRequest request) {
        // .... 创建订单 ....
        return new Order();
    }

    // 模拟从结果集中获取订单信息
    private Order getOrderById(String orderId) {
        // 根据ID查找订单信息
        return new Order();
    }
}

