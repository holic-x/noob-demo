package com.noob.redis.aopLock.service;


import com.noob.redis.aopLock.model.Resp;

public interface IOrderService {
    Resp updateOrder(String orderCode, Integer userId, Integer status);
}
