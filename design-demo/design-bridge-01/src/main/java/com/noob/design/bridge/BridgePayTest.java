package com.noob.design.bridge;

import java.math.BigDecimal;

public class BridgePayTest {

    public static void main(String[] args) {
        // 微信 + 指纹
        Pay wechatPay = new WeChatPay(new FingerprintPayMode());
        wechatPay.pay("1001","t001",new BigDecimal("100"));

        // 支付宝 + 人脸识别
        Pay alipayPay = new AliPay(new FacePayMode());
        alipayPay.pay("1001","t002",new BigDecimal("300"));
    }
}
