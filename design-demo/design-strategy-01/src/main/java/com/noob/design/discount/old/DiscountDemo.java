package com.noob.design.discount.old;

/**
 * 优惠券折算活动计算
 */
public class DiscountDemo {

    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();
        // 满300减300
        System.out.println("满减后：" + discountService.discountAmount(1,300,500));
        // 直减100
        System.out.println("直减后：" + discountService.discountAmount(2,100,500));
        // 打三折
        System.out.println("折扣后：" + discountService.discountAmount(3,0.3,500));
        // 0元购
        System.out.println("n元购后：" + discountService.discountAmount(4,0,500));
    }

}
