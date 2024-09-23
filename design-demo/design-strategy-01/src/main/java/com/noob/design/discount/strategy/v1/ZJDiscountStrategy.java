package com.noob.design.discount.strategy.v1;

/**
 * 具体策略：直减策略
 */
public class ZJDiscountStrategy implements IDiscountStrategy{
    @Override
    public double discountAmount(double strategyContent, double skuPrice) {
        // 直减策略：此处strategyContent为直减金额
        return skuPrice-strategyContent;
    }
}
