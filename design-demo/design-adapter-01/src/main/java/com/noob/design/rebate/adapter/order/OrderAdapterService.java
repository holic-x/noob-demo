package com.noob.design.rebate.adapter.order;

/**
 * 订单处理适配器(接口定义)
 */
public interface OrderAdapterService{
    // 判断是否为首单
    public boolean isFirstOrder(String uid);
}
