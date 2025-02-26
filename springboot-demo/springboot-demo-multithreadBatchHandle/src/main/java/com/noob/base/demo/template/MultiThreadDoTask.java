package com.noob.base.demo.template;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ① 多线程批量处理任务
 */
public class MultiThreadDoTask {

    @SneakyThrows
    private static void doTask(int i) {
        System.out.println("模拟处理任务.....");
    }

    public static void main(String[] args) {
        // ① 模拟100个任务
        List<Integer> taskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            taskList.add(i);
        }

        // ② 定义线程池（10个线程），并将任务提交到线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交任务到线程池
        for (int taskId : taskList) {
            executor.submit(() -> {
                doTask(taskId);
            });
        }

        // 任务执行完成关闭线程池
        executor.shutdown();
    }
}
