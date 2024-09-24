package com.noob.design.discount.strategy.v1;

/**
 * 抽象策略：优惠策略接口定义
 */
public interface IDiscountStrategy {
    public double discountAmount(double strategyContent,double skuPrice);
}
