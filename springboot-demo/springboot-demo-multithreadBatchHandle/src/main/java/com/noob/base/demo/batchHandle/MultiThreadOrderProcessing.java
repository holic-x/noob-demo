package com.noob.base.demo.batchHandle;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ③ 订单处理
 */
public class MultiThreadOrderProcessing {

    @SneakyThrows
    public static void main(String[] args) {

        // 模拟10w个订单
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            Order order = new Order(i, "onum_" + i, new Random().nextInt(1000) + i);
            orderList.add(order);
        }

        // 定义线程池（10个线程）
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 收集批量处理结果
        List<Future<Integer>> futures = new ArrayList<>();

        // 将订单进行分批处理
        int batchSize = 1000; // 设定1000个订单为1批进行处理
        for (int i = 0; i < orderList.size(); i += batchSize) {
            // 定义当前处理批次范围[start,end]
            int start = i;
            int end = Math.min(i + batchSize, orderList.size());
            // 获取当前批次
            List<Order> subBatch = orderList.subList(start, end);
            // 将当前批次提交到任务池
            Future<Integer> future = executor.submit(() -> handleBatch(subBatch));
            futures.add(future);
        }

        // 等待所有批次处理完成
        int total = 0;
        for (Future<Integer> future : futures) {
            total += future.get();
        }
        System.out.println("订单处理总金额" + total);

        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 批量处理任务
     *
     * @param subBatch
     * @return
     */
    private static int handleBatch(List<Order> subBatch) {
        // 模拟批量处理订单信息
        int count = 0;
        for (Order order : subBatch) {
            // 例如此处模拟调用外部API验证订单是否处理成功
            /*
            if(valid(order)){
                ....
            }
             */
            count += order.price;
        }
        return count;
    }

}

// 订单表
class Order {
    int id;
    String orderNum;
    int price;

    public Order(int id, String orderNum, int price) {
        this.id = id;
        this.orderNum = orderNum;
        this.price = price;
    }
}