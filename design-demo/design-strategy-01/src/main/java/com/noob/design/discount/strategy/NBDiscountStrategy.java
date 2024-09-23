package com.noob.design.discount.strategy;

/**
 * 具体策略：n元购策略
 */
public class NBDiscountStrategy implements IDiscountStrategy{
    @Override
    public double discountAmount(double strategyContent, double skuPrice) {
        // n元购策略：此处strategyContent为n元购配置
        return strategyContent;
    }
}
