package com.noob.design.discount.strategy;

/**
 * 具体策略：满减策略
 */
public class MJDiscountStrategy implements IDiscountStrategy{
    @Override
    public double discountAmount(double strategyContent, double skuPrice) {
        // 满减策略：此处strategyContent为满多少减多少
        return skuPrice<strategyContent?skuPrice:skuPrice-strategyContent;
    }
}
