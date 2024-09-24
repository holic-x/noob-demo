package com.noob.design.discount.strategy.v2;


/**
 * 直减策略
 */
public class ZJStrategy implements Strategy <Double>{
    @Override
    public double discountAmount(Double amount, double skuPrice) {
        return skuPrice - amount;
    }
}
