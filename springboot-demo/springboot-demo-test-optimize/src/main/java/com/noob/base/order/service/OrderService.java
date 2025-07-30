package com.noob.base.order.service;

import com.noob.base.order.dao.OrderRepository;
import com.noob.base.order.framework.exception.OrderNotFoundException;
import com.noob.base.order.framework.exception.OutOfStockException;
import com.noob.base.order.model.entity.Order;
import com.noob.base.order.model.entity.OrderItem;
import com.noob.base.order.model.entity.User;
import com.noob.base.order.model.enums.OrderStatus;
import com.noob.base.order.model.enums.UserLevel;
import com.noob.base.order.model.request.PaymentRequest;
import com.noob.base.order.model.vo.PaymentResult;

import java.util.List;

// OrderService.java
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final PaymentGateway paymentGateway;
    private final DiscountService discountService;

    public OrderService(OrderRepository orderRepository,
                        InventoryService inventoryService,
                        PaymentGateway paymentGateway,
                        DiscountService discountService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
        this.paymentGateway = paymentGateway;
        this.discountService = discountService;
    }

    public Order createOrder(String userId, List<OrderItem> items) {
        // 检查库存
        for (OrderItem item : items) {
            if (!inventoryService.checkStock(item.getProductId())) {
                throw new OutOfStockException("Product out of stock: " + item.getProductId());
            }
        }

        // 计算总金额
        double totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        // 计算折扣（简化版，实际应该有User对象）
        User user = new User(userId, userId.startsWith("vip") ? UserLevel.VIP : UserLevel.NORMAL);
        double discount = discountService.calculateDiscount(user, totalAmount);

        // 生成订单ID（简化）
        String orderId = "order-" + System.currentTimeMillis();

        // 创建并保存订单
        Order order = new Order(orderId, items, totalAmount, discount);
        return orderRepository.save(order);
    }

    public PaymentResult payOrder(String orderId, String paymentMethod) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Order cannot be paid");
        }

        PaymentResult result = paymentGateway.processPayment(
                new PaymentRequest(orderId, order.getFinalAmount(), paymentMethod));

        if (result.isSuccess()) {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }

        return result;
    }
}
