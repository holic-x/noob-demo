package com.noob.design.discount.strategy.v2;

/**
 * 策略定义：接收泛型参数
 * @param <T>
 */
public interface Strategy<T> {
    // 定义公共方法：计算折算金额
    public double discountAmount(T t, double skuPrice);
}
