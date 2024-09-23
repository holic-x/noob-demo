package com.noob.design.rebate.adapter.order;

/**
 * 订单服务接口适配 demo 测试
 */
public class OrderAdapterServiceDemo {
    public static void main(String[] args) {
        OrderAdapterService innerOrderService = new InnerOrderServiceImpl();
        System.out.println("innerOrderService 适配器:" + innerOrderService.isFirstOrder("1001"));
        OrderAdapterService thirdOrderService = new ThirdOrderServiceImpl();
        System.out.println("thirdOrderService 适配器:" + thirdOrderService.isFirstOrder("2001"));
    }
}
