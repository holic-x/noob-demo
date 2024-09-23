package com.noob.design.discount.old;

/**
 * 根据不同优惠券类型计算折扣金额：
 * 1-满减、2-直减、3-折扣、4-n元购
 */
public class DiscountService {

    public double discountAmount(int discountType,double discountTypeContent, double skuPrice) {
        double discountAmount = 0;
        if(discountType==1){ // 此时discountTypeContent为满减金额阈值（可以设定满减金额参数，此处设定为满多少就减多少）
            // 达到满减阈值则进行扣减，未达到则维持原价
            discountAmount = skuPrice<discountTypeContent? skuPrice:(skuPrice - discountTypeContent);
        }else if(discountType==2){// 此时discountTypeContent为直减金额
            discountAmount = skuPrice-discountTypeContent;
        }else if(discountType==3){// 此时discountTypeContent为折扣系数（0-1取值）
            discountAmount = skuPrice*discountTypeContent;
        }else if(discountType==4){// 此时discountTypeContent为n元购参数设定
            discountAmount = discountTypeContent;
        }
        // 返回对应折算策略计算后的金额
        return discountAmount;
    }
}
