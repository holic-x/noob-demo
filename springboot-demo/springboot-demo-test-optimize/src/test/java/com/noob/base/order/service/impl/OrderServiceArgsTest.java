package com.noob.base.order.service.impl;

import com.noob.base.order.dao.OrderRepository;
import com.noob.base.order.model.entity.Order;
import com.noob.base.order.model.enums.OrderStatus;
import com.noob.base.order.model.request.PaymentRequest;
import com.noob.base.order.model.vo.PaymentResult;
import com.noob.base.order.service.OrderService;
import com.noob.base.order.service.PaymentGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 订单服务核心逻辑测试: 进阶功能验证测试
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceArgsTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() {
        // MockitoAnnotations.openMocks(this); 等价于上述 @RunWith(MockitoJUnitRunner.class)
    }

    // 验证Mockito版本（或者手动检查maven依赖）
    @Test
    public void test_mockitoVersion() {
        // 版本号如果返回null则可能没有正常指定版本号数据
        System.out.println("Mockito version: " + Mockito.class.getPackage().getImplementationVersion());
    }

    // 1.verify + ArgumentCaptor（验证方法调用参数）
    @Test
    public void payOrder_ShouldCallPaymentGatewayWithCorrectAmount() {
        // Arrange
        Order order = new Order("order123", "user123", OrderStatus.PENDING, 100.0);
        when(orderRepository.findById("order123")).thenReturn(Optional.of(order));
        when(paymentGateway.processPayment(any())).thenReturn(new PaymentResult(true, "success"));

        // Act
        orderService.payOrder("order123", "credit_card");

        // Assert: 手动创建ArgumentCaptor
        ArgumentCaptor<PaymentRequest> paymentCaptor = ArgumentCaptor.forClass(PaymentRequest.class);
        verify(paymentGateway).processPayment(paymentCaptor.capture());

        PaymentRequest request = paymentCaptor.getValue();
        assertEquals("order123", request.getOrderId());
        assertEquals(100.0, request.getAmount());
        assertEquals("credit_card", request.getPaymentMethod());
    }

    // 2.stub + doAnswer（在插桩时捕获参数）
    @Test
    public void payOrder_ShouldRecordPaymentLogWhenGatewayCalled() {
        // Arrange
        Order order = new Order("order123", "user123", OrderStatus.PENDING, 100.0);
        when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

        // 用doAnswer捕获参数
        doAnswer(invocation -> {
            PaymentRequest request = invocation.getArgument(0);
            System.out.println("[TEST] Payment attempted: " + request.getOrderId());
            return new PaymentResult(true, "success");
        }).when(paymentGateway).processPayment(any());

        // Act
        orderService.payOrder("order123", "credit_card");

        // 可选：验证调用次数
        verify(paymentGateway, times(1)).processPayment(any());
    }

    // 3.stub + thenAnswer（在插桩时动态生成返回值）
    @Test
    public void payOrder_ShouldRejectLargeAmounts() {
        // Arrange
        Order order = new Order("order123", "user123", OrderStatus.PENDING, 1500.0); // 大金额
        when(orderRepository.findById("order123")).thenReturn(Optional.of(order));

        // 用thenAnswer模拟支付失败
        when(paymentGateway.processPayment(any()))
                .thenAnswer(invocation -> {
                    PaymentRequest request = invocation.getArgument(0);
                    if (request.getAmount() > 1000.0) {
                        return new PaymentResult(false, "Amount exceeded limit");
                    }
                    return new PaymentResult(true, "success");
                });

        // Act & Assert
        PaymentResult result = orderService.payOrder("order123", "credit_card");
        assertFalse(result.isSuccess()); // 验证支付失败
        assertEquals("Amount exceeded limit", result.getMessage());
    }
}