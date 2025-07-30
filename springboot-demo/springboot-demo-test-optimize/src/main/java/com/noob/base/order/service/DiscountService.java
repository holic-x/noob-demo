package com.noob.base.order.service;

import com.noob.base.order.model.entity.User;

// DiscountService.java
public interface DiscountService {
    double calculateDiscount(User user, double amount);
}