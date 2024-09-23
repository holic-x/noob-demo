package com.noob.design.discount.strategy.v2;


/**
 * 折扣策略
 */
public class ZKStrategy implements Strategy <Double>{
    @Override
    public double discountAmount(Double discountFactor, double skuPrice) {
        return skuPrice * discountFactor;
    }
}
