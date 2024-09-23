package com.noob.design.pay;

import java.math.BigDecimal;

public class PayTest {

    public static void main(String[] args) {
        PayController payController = new PayController();
        // 模拟微信支付
        payController.pay("1001","t001",new BigDecimal("100"),1,2);
        // 模拟支付宝支付
        payController.pay("2001","t0012",new BigDecimal("2000"),2,2);
    }

}
