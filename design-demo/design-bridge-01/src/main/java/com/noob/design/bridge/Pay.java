package com.noob.design.bridge;

import java.math.BigDecimal;

/**
 * 支付基类
 */
public abstract class Pay {

    // 定义桥（支付模式）
    protected IPayMode payMode;

    // 构造函数定义（搭建桥连接）
    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    // 支付方法
    public abstract void pay(String uid, String traceId, BigDecimal money);

}
