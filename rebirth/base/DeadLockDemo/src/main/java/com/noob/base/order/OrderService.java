package com.noob.base.order;

public class OrderService {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private InventoryService inventoryService = new InventoryService();
    private OrderDao orderDao = new OrderDao();

    public void createOrder() {
        synchronized (lock1) {
            // 锁定库存
            inventoryService.lockStock();
            synchronized (lock2) {
                // 创建订单
                orderDao.createOrder();
            }
        }
    }

    public void cancelOrder() {
        synchronized (lock2) {
            // 删除订单
            orderDao.deleteOrder();
            synchronized (lock1) {
                // 释放库存
                inventoryService.releaseStock();
            }
        }
    }
}

// 模拟订单、库存的操作
class OrderDao{
    public void createOrder(){
        System.out.println("createOrder");
    }
    public void deleteOrder(){
        System.out.println("deleteOrder");
    }
}

class InventoryService{
    public void lockStock(){
        System.out.println("lockStock");
    }
    public void releaseStock(){
        System.out.println("releaseStock");
    }
}