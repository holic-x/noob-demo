package com.noob.design.discount.strategy.v2;

/**
 * 环境定义
 */
public class Context<T> {

    // 维护策略对象引用
    private Strategy<T> strategy;

    // 初始化策略对象
    public Context(Strategy<T> strategy) {
        this.strategy = strategy;
    }

    // 执行策略
    public double execStrategy(T t,double skuPrice) {
        return this.strategy.discountAmount(t,skuPrice);
    }
}
