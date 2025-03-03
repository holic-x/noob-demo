package com.noob.base.order;

public class OrderServiceTest {

    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{orderService.createOrder();});
        Thread t2 = new Thread(()->{orderService.cancelOrder();});
        t1.start();
        t2.start();
    }
}
