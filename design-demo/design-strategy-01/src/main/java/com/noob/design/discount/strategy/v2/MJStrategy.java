package com.noob.design.discount.strategy.v2;

import java.util.Map;

/**
 * 满减策略
 */
public class MJStrategy implements Strategy <Map<String,String>>{
    @Override
    public double discountAmount(Map<String, String> map, double skuPrice) {
        // map 中定义了相应的满减策略（满减金额阈值、满减金额）
        double mj = Double.parseDouble(map.get("mj"));
        double amount = Double.parseDouble(map.get("amount"));
        return skuPrice > mj ? (skuPrice - amount) : skuPrice;
    }
}
