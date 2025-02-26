package com.noob.base.demo.batchHandle;


import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ⑤ 推荐系统
 */
public class MultiThreadRecommendation {
    @SneakyThrows
    private static List<Recommendation> batchHandle(List<User> batch) {
        // 模拟处理用户的推荐信息
        List<Recommendation> recommendationList = new ArrayList<>();
        for (User user : batch) {
            recommendationList.add(new Recommendation(user.id, "Recommended item for user " + user.name));
        }
        return recommendationList;
    }

    @SneakyThrows
    public static void main(String[] args) {
        // ① 模拟1000w个用户（大批量用户）
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            userList.add(new User(i, "user_" + i));
        }

        // ② 定义线程池（10个线程），并将任务提交到线程池(按批提交，一个线程执行一个批次)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 定义Future列表收集批次执行结果
        List<Future<List<Recommendation>>> futures = new ArrayList<>();

        // 定义批次大小
        int batchSize = 1000; // 设定1000个任务为1个批次(1批处理1000个用户的推荐信息)

        // 提交任务到线程池
        for (int i = 0; i < userList.size(); i += batchSize) {
            // 定义当前批次的起止点[start,end]
            int start = i;
            int end = i + batchSize;
            // 获取当前批次
            List<User> subBatch = userList.subList(start, end);
            // 将当前批次任务加入任务池
            Future future = executor.submit(() -> batchHandle(subBatch));
            futures.add(future);
        }

        // 等待所有批次执行完成，收集结果
        List<Recommendation> total = new ArrayList<>();
        for (Future<List<Recommendation>> future : futures) {
            total.addAll(future.get());
        }
        System.out.println("执行结果" + total.size());

        // 任务执行完成关闭线程池
        executor.shutdown();
    }
}

// 用户信息
class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// 推荐列表
class Recommendation {
    int userId;
    String item; // 推荐项目

    public Recommendation(int userId, String item) {
        this.userId = userId;
        this.item = item;
    }
}
