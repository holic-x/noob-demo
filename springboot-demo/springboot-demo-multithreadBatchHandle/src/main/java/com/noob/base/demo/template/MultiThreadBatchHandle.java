package com.noob.base.demo.template;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ② 多线程批量处理任务（大数据量分批处理，每个线程执行一个批次）
 */
public class MultiThreadBatchHandle {

    @SneakyThrows
    private static int batchHandle(List<Integer> batch) {
        System.out.println("模拟处理批次任务.....");
        return 0; // 模拟批次处理返回值
    }

    @SneakyThrows
    public static void main(String[] args) {
        // ① 模拟100w个任务（大批量任务）
        List<Integer> taskList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            taskList.add(i);
        }

        // ② 定义线程池（10个线程），并将任务提交到线程池(按批提交，一个线程执行一个批次)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 定义Future列表收集批次执行结果
        List<Future<Integer>> futures = new ArrayList<>();

        // 定义批次大小
        int batchSize = 1000; // 设定1000个任务为1个批次

        // 提交任务到线程池
        for (int i = 0; i < taskList.size(); i += batchSize) {
            // 定义当前批次的起止点[start,end]
            int start = i;
            int end = i + batchSize;
            // 获取当前批次
            List<Integer> subBatch = taskList.subList(start, end);
            // 将当前批次任务加入任务池
            Future future = executor.submit(() -> batchHandle(subBatch));
            futures.add(future);
        }

        // 等待所有批次执行完成，收集结果
        int total = 0;
        for (Future<Integer> future : futures) {
            total += future.get();
        }
        System.out.println("执行结果" + total);

        // 任务执行完成关闭线程池
        executor.shutdown();
    }
}
