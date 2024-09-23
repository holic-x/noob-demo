package com.noob.design.discount.strategy.v2;


/**
 * N元购策略
 */
public class NYGStrategy implements Strategy <Double>{
    @Override
    public double discountAmount(Double discountAmount, double skuPrice) {
        return discountAmount;
    }
}
