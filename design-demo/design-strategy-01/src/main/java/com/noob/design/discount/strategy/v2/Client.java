package com.noob.design.discount.strategy.v2;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式客户端测试demo
 */
public class Client {
    public static void main(String[] args) {

        // 满减
        Map<String,String> mjMap = new HashMap<>();
        mjMap.put("mj","300");
        mjMap.put("amount","50");
        Context<Map<String,String>> mjContext = new Context<>(new MJStrategy());
        System.out.println("满减后：" + mjContext.execStrategy(mjMap,500));

        // 直减
        Context<Double> zjContext = new Context<>(new ZJStrategy());
        System.out.println("直减后：" + zjContext.execStrategy(Double.valueOf(100),500));

        // 折扣 打三折
        Context<Double> zkContext = new Context<>(new ZKStrategy());
        System.out.println("折扣后：" + zkContext.execStrategy(Double.valueOf(0.3),500));

        // N 元购
        Context<Double> nygContext = new Context<>(new NYGStrategy());
        System.out.println("N元购后：" + nygContext.execStrategy(Double.valueOf(10),500));
    }
}
