package com.noob.base.scene.export;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 批量数据导出场景
 */
public class ExcelExportDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 原始数据记录（从数据库获取或者其他方式获取，此处仅用于模拟，实际场景中避免一次性载入过多数据）
        List<UserData> dataList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            dataList.add(new UserData("name" + i, "noob-" + i + "@16.com", "13800138000"));
        }

        // 1.任务分片执行
        ExecutorService executor = Executors.newFixedThreadPool(2); // 模拟测试此处使用固定数量线程池
        int batchSize = 100000; // 假设10w条数据一个批次
        List<Future> futures = new ArrayList<>();
        int batch = dataList.size() / batchSize;
        for (int i = 0; i < batch; i++) {
            // 处理指定范围的数据
            List<UserData> batchList = dataList.subList(i * batchSize, Math.min((i + 1) * batchSize, dataList.size()));
            // 创建分片任务
            Future future = executor.submit(new ExportTask("task" + i, batchList, "/output_" + i + ".xlsx"), executor);
            futures.add(future);
        }

        // 阻塞等待，收集处理结果
        int cnt = 0;
        for (Future future : futures) {
            future.get();
            cnt++;
        }

        // 2.合并结果
        System.out.println("模拟结果合并");

        // 关闭线程池
        executor.shutdown();
    }

}
