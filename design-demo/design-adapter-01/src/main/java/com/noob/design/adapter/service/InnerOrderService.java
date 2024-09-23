package com.noob.design.adapter.service;

/**
 * 内部订单处理服务接口
 */
public class InnerOrderService {
    // 查询用户订单是否为首单
    public long queryUserOrderCount(String uid){
        System.out.println("自营商家（内部订单），查询用户订单是否为首单：" + uid);
        return 10L; // 模拟返回
    }
}
