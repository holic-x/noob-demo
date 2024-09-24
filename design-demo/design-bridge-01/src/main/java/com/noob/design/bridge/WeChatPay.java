package com.noob.design.bridge;

import java.math.BigDecimal;

/**
 * wechat 支付渠道
 */
public class WeChatPay extends Pay{

    public WeChatPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public void pay(String uid, String traceId, BigDecimal money) {
        this.payMode.security(uid);
        System.out.println("wechat pay");
    }

}
