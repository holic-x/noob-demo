package com.noob.base.demo.batchHandle;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ② 数据处理
 */
public class MultiThreadDataProcessing {

    // 处理批次任务
    public static int handleBatch(List<Integer> list) {
        // 模拟数据处理：例如累加数据
        int sum = 0;
        for (int data : list) {
            sum += data; // 模拟数据处理
        }
        // System.out.println(Thread.currentThread().getName() + "处理批次，得到结果：" + sum);
        return sum;
    }

    @SneakyThrows
    public static void main(String[] args) {
        // 初始化数据（大数据集）
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            dataList.add(i);
        }

        // 定义线程池
        ExecutorService executor = Executors.newFixedThreadPool(8);

        List<Future<Integer>> futures = new ArrayList<>();

        // 设定将数据分为多个批次
        int batchSize = 1000;
        for (int i = 0; i < dataList.size(); i += batchSize) {
            // 批次的起止点：[i,min{i+batchSize,maxLen}]
            int start = i;
            int end = Math.min(i + batchSize, dataList.size());
            List<Integer> subBatch = dataList.subList(start, end);
            // 提交批量任务到线程池
            Future<Integer> future = executor.submit(() -> handleBatch(subBatch));
            futures.add(future);
        }

        // 等待所有任务完成并汇总结果
        int total = 0;
        for (Future<Integer> future : futures) {
            total += future.get(); // 汇总处理结果
        }
        System.out.println("最终处理结果：" + total);

        // 关闭线程池
        executor.shutdown();
    }

}
