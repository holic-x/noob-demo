package com.noob.base.demo.batchHandle;


import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ④ 日志分析
 */
public class MultiThreadLogAnalysis {

    @SneakyThrows
    private static Map<String, Integer> batchHandle(List<String> batch) {
        // 模拟收集日志数据，统计关键字出现次数
        Map<String, Integer> map = new HashMap<>();
        for (String log : batch) {
            if (log.contains("User")) {
                map.put("User", map.getOrDefault("User", 0) + 1);
            }
            if (log.contains("logged")) {
                map.put("logged", map.getOrDefault("logged", 0) + 1);
            }
        }
        // 返回关键字统计结果
        return map;
    }

    @SneakyThrows
    public static void main(String[] args) {
        // ① 模拟100w条日志记录
        List<String> logList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            logList.add("Log:" + i + " - User logged in");
        }

        // ② 定义线程池（10个线程），并将任务提交到线程池(按批提交，一个线程执行一个批次)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 定义Future列表收集批次执行结果
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        // 定义批次大小
        int batchSize = 1000; // 设定1000个任务为1个批次

        // 提交任务到线程池
        for (int i = 0; i < logList.size(); i += batchSize) {
            // 定义当前批次的起止点[start,end]
            int start = i;
            int end = i + batchSize;
            // 获取当前批次
            List<String> subBatch = logList.subList(start, end);
            // 将当前批次任务加入任务池
            Future future = executor.submit(() -> batchHandle(subBatch));
            futures.add(future);
        }

        // 等待所有批次执行完成，收集结果
        Map<String, Integer> total = new HashMap<>();
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> batchMap = future.get();
            batchMap.forEach((key, value) -> total.merge(key, value, Integer::sum));
        }
        System.out.println("执行结果" + total);

        // 任务执行完成关闭线程池
        executor.shutdown();
    }
}
