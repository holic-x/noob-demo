package com.noob.base.order.model.vo;

import lombok.Getter;

/**
 * 支付结果实体
 */
@Getter
public class PaymentResult {
    private boolean success;
    private String message;
    
    public PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
}