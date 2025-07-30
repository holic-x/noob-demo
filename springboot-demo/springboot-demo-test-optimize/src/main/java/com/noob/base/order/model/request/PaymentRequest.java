package com.noob.base.order.model.request;

import lombok.Getter;

/**
 * 支付请求实体类
 */
@Getter
public class PaymentRequest {
    private String orderId;
    private double amount;
    private String paymentMethod;
    
    public PaymentRequest(String orderId, double amount, String paymentMethod) {
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}