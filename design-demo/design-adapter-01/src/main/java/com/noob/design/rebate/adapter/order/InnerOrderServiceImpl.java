package com.noob.design.rebate.adapter.order;

import com.noob.design.rebate.old.handler.InnerOrderMqService;

/**
 * 内部订单服务处理
 */
public class InnerOrderServiceImpl implements OrderAdapterService{

    InnerOrderMqService innerOrderMqService = new InnerOrderMqService();

    @Override
    public boolean isFirstOrder(String uid) {
        System.out.println("inner order");
        // 模拟业务逻辑处理
        long count = innerOrderMqService.queryUserOrderCount(uid);
        return count==0;
    }
}
