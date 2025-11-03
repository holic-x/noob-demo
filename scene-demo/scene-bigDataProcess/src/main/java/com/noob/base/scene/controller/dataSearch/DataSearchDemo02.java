package com.noob.base.scene.controller.dataSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 数据检索
 */
public class DataSearchDemo02 {

    /**
     * ② 优化：异步并行调度
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();

        // 构造线程池处理
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 提交任务并获取Futures
        List<Future<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future future = executor.submit(() -> {
                DataSearchAPI.doSearch(String.format("业务编号-%d", finalI));
            });
            futureList.add(future);
        }

        // 阻塞并等待任务处理完成,收集任务处理结果
        for (int i = 0; i < futureList.size(); i++) {
            futureList.get(i).get();
        }

        // 处理完成，关闭临时线程池
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("本次处理总耗时{%s}ms", (endTime - startTime)));

    }

}
