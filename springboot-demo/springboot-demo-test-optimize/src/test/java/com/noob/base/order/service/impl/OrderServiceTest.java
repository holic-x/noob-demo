package com.noob.base.order.service.impl;

import com.noob.base.order.dao.OrderRepository;
import com.noob.base.order.framework.exception.OrderNotFoundException;
import com.noob.base.order.framework.exception.OutOfStockException;
import com.noob.base.order.model.entity.Order;
import com.noob.base.order.model.entity.OrderItem;
import com.noob.base.order.model.enums.OrderStatus;
import com.noob.base.order.model.request.PaymentRequest;
import com.noob.base.order.model.vo.PaymentResult;
import com.noob.base.order.service.DiscountService;
import com.noob.base.order.service.InventoryService;
import com.noob.base.order.service.OrderService;
import com.noob.base.order.service.PaymentGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 订单服务核心逻辑测试
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() {
        // MockitoAnnotations.openMocks(this); 等价于上述 @RunWith(MockitoJUnitRunner.class)
    }

    // 场景1：成功创建订单
    @Test
    public void test_createOrder_success() {
        // Arrange
        List<OrderItem> items = Arrays.asList(
                new OrderItem("product1", 2, 100.0),
                new OrderItem("product2", 1, 200.0)
        );

        when(inventoryService.checkStock(anyString())).thenReturn(true);
        when(discountService.calculateDiscount(any(), anyDouble())).thenReturn(50.0);
        when(orderRepository.save(any())).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            return order; // 简单返回传入的order对象
        });

        // Act
        Order result = orderService.createOrder("user123", items);

        // Assert
        assertNotNull(result.getOrderId());
        assertEquals(400.0, result.getTotalAmount()); // 2*100 + 1*200
        assertEquals(50.0, result.getDiscount());
        assertEquals(350.0, result.getFinalAmount());

        verify(inventoryService, times(2)).checkStock(anyString());
        verify(orderRepository).save(any());
    }

    // 场景2：成功支付订单
    @Test
    public void test_createOrder_failWhenOutOfStock() {
        // Arrange
        List<OrderItem> items = Arrays.asList(
                new OrderItem("product1", 1, 100.0)
        );

        when(inventoryService.checkStock("product1")).thenReturn(false);

        // Act & Assert
        assertThrows(OutOfStockException.class, () -> {
            orderService.createOrder("user123", items);
        });

        verify(orderRepository, never()).save(any());
    }

    // 场景3：库存不足时创建订单失败
    @Test
    public void test_payOrder_success() {
        // Arrange
        String orderId = "order123";
        Order order = new Order(orderId, "user123", OrderStatus.PENDING, 100.0);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(paymentGateway.processPayment(any())).thenReturn(new PaymentResult(true, "success"));
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        PaymentResult result = orderService.payOrder(orderId, "card_123");

        // Assert
        assertTrue(result.isSuccess());

        // 验证订单状态更新
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());
        assertEquals(OrderStatus.PAID, orderCaptor.getValue().getStatus());

        // 验证支付金额
        ArgumentCaptor<PaymentRequest> paymentCaptor = ArgumentCaptor.forClass(PaymentRequest.class);
        verify(paymentGateway).processPayment(paymentCaptor.capture());
        assertEquals(100.0, paymentCaptor.getValue().getAmount());
    }

    // 场景4：应用VIP用户折扣
    @Test
    public void test_applyVipDiscount() {
        // Arrange
        List<OrderItem> items = Arrays.asList(
                new OrderItem("product1", 1, 100.0)
        );

        when(inventoryService.checkStock(anyString())).thenReturn(true);
        when(discountService.calculateDiscount(any(), eq(100.0))).thenReturn(20.0);
        // mock 保存操作：思路① 自定义响应对象返回，如需处理更加细化的细节则需额外限定
        // when(orderRepository.save(any())).thenReturn(new Order());

        // mock 保存操作：思路② 此处stub的数据设定为传入的Order对象进行返回
        when(orderRepository.save(any())).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            return order; // 简单返回传入的order对象
        });

        // Act (VIP用户ID以"vip"开头)
        Order result = orderService.createOrder("vip123", items);

        // Assert
        assertEquals(80.0, result.getFinalAmount()); // 100 - 20
        // assertNotNull(result); // 此处如果是验证逻辑，可以简化断言细节，目的是为了验证整个流程是否走通
    }

    // 场景5：支付订单不存在
    @Test
    public void test_payOrder_failWhenOrderNotFound() {
        // Arrange
        String orderId = "nonexistent";
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrderNotFoundException.class, () -> {
            orderService.payOrder(orderId, "card_123");
        });
    }
}