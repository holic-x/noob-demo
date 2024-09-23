package com.noob.design.discount.strategy.v1;

/**
 * 具体策略：折扣策略
 */
public class ZKDiscountStrategy implements IDiscountStrategy{
    @Override
    public double discountAmount(double strategyContent, double skuPrice) {
        // 折扣策略：此处strategyContent为折扣系数
        return skuPrice*strategyContent;
    }
}
