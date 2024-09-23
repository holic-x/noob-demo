package com.noob.design.adapter.service;

/**
 * 第三方订单处理服务
 */
public class ThirdOrderService {
    // 查询用户订单是否为首单
    public boolean isFirstOrder(String uid){
        System.out.println("POP商家（第三方订单），查询用户订单是否为首单：" + uid);
        return true; // 模拟返回
    }
}
