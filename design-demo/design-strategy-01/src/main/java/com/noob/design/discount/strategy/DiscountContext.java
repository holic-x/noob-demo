package com.noob.design.discount.strategy;

/**
 * 环境：维护一个策略引用，用于将客户端请求委派到具体策略对象进行执行
 */
public class DiscountContext {

    // 引用策略对象
    private IDiscountStrategy discountStrategy;

    // 初始化环境（初始化策略）
    public DiscountContext(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    // 执行策略
    public double execStrategy(double strategyContent,double skuPrice){
        return this.discountStrategy.discountAmount(strategyContent,skuPrice);
    }
}
