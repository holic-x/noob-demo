package com.noob.base.order.model.entity;

import lombok.Getter;

/**
 * 订单明细
 */
@Getter
public class OrderItem {
    private String productId;
    private int quantity;
    private double price;
    
    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}