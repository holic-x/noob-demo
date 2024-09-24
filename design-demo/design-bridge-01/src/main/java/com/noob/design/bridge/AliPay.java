package com.noob.design.bridge;

import java.math.BigDecimal;

/**
 * Ali 支付渠道
 */
public class AliPay extends Pay{

    public AliPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public void pay(String uid, String traceId, BigDecimal money) {
        this.payMode.security(uid);
        System.out.println("ali pay");
    }
    
}
