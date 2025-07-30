package com.noob.base.order.model.entity;

import com.noob.base.order.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单表
 */
@Getter
@Setter
public class Order {
    private String orderId;
    private String userId;
    private OrderStatus status;
    private List<OrderItem> items;
    private double totalAmount;
    private double discount;

    public Order() {
    }

    public Order(String orderId, double totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
    }

    public Order(String orderId, String userId, OrderStatus status, double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
    }

    public Order(String orderId, List<OrderItem> items, double totalAmount, double discount) {
        this.orderId = orderId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.discount = discount;
    }


    public double getFinalAmount() {
        return totalAmount - discount;
    }
}