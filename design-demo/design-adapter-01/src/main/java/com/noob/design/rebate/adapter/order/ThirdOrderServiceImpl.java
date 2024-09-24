package com.noob.design.rebate.adapter.order;

import com.noob.design.rebate.old.handler.ThirdOrderMqService;

/**
 * 第三方订单服务处理
 */
public class ThirdOrderServiceImpl implements OrderAdapterService{

    ThirdOrderMqService thirdOrderMqService = new ThirdOrderMqService();

    @Override
    public boolean isFirstOrder(String uid) {
        System.out.println("third order");
        // 模拟业务逻辑处理
        return thirdOrderMqService.isFirstOrder(uid);
    }
}
