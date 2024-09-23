package com.noob.design.discount.strategy;

/**
 * 策略模式客户端测试
 */
public class DiscountClient {
    public static void main(String[] args) {

        // 初始化环境并执行策略
        DiscountContext discountContext = null;

        // 满300减300
        discountContext = new DiscountContext(new MJDiscountStrategy());
        System.out.println("满减后：" + discountContext.execStrategy(300,500));

        // 直减100
        discountContext = new DiscountContext(new ZJDiscountStrategy());
        System.out.println("直减后：" + discountContext.execStrategy(100,500));

        // 打三折
        discountContext = new DiscountContext(new ZKDiscountStrategy());
        System.out.println("折扣后：" + discountContext.execStrategy(0.3,500));

        // 10元购
        discountContext = new DiscountContext(new NBDiscountStrategy());
        System.out.println("n元购后：" + discountContext.execStrategy(10,500));
    }
}
