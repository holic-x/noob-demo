package com.noob.test;

import lombok.Data;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 批量执行任务DEMO
 * 1.创建线程池
 * 2.创建批次任务并执行
 * 3.任务执行完成关闭线程池
 */
public class BatchExecutorDemo {

    /**
     * 调用方法执行指定批次的任务
     *
     * @param taskList 待执行任务
     * @param batchs   批次定义
     */
    @SneakyThrows
    public static void batchExecute(List<NewTask> taskList, List<List<Integer>> batchs) {

        // 创建固定大小的线程池
        ExecutorService executors = Executors.newFixedThreadPool(10);

        // 记录已执行任务索引
        List<Integer> hasExecuteIndex = new ArrayList<>();

        // 1.按照批次执行任务
        List<Future<Void>> executeTasks = new ArrayList<>();
        batchs.forEach(batch -> {
            // 装配当前批次任务
            for (int idx : batch) {
                Future<Void> future = (Future<Void>) executors.submit(taskList.get(idx));
                executeTasks.add(future); // 加入指定任务到当前执行批次
                hasExecuteIndex.add(idx); // 将已完成任务索引记录下来
            }
            // 等待当前批次任务完成
            for (Future<Void> future : executeTasks) {
                try {
                    future.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            // 清理future，进入下一批次任务执行
            executeTasks.clear();
            System.out.println("当前批次任务执行完成");
        });

        // 2.执行剩余批次的任务
        List<Future<Void>> remindTask = new ArrayList<>();
        // 根据已执行索引进行排除
        for (NewTask task : taskList) {
            // 如果未执行则加入待执行
            if (!hasExecuteIndex.contains(task.getId())) {
                Future<Void> future = (Future<Void>) executors.submit(task);
                remindTask.add(future);
                // hasExecuteIndex.add(task.getId()); 已经是最后一批，此处无需再记录已完成的任务索引
            }
        }
        // 等待最后一个批次的任务完成
        remindTask.forEach((task) -> {
            try {
                task.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // 3.关闭资源（关闭线程池并等待所有任务完成）
        executors.shutdown();
        while(!executors.isTerminated()){
            // 持续等待任务完成
        }

    }


    // 要求：按照一定批次顺序执行任务，然后再执行剩余的任务
    public static void main(String[] args) {

        List<NewTask> taskList = new ArrayList<>();
        // 创建100个任务
        for (int i = 0; i < 100; i++) {
            taskList.add(new NewTask(i));
        }

        // 指定任务执行的批次(根据任务编号)
        List<List<Integer>> batchs = new ArrayList<>();
        batchs.add(Arrays.asList(1, 3, 5, 7));
        batchs.add(Arrays.asList(15, 18, 20, 59));

        // 调用方法执行指定批次的任务
        BatchExecutorDemo.batchExecute(taskList,batchs);

    }


}

// 自定义任务
@Data
class NewTask implements Runnable {

    private int id;

    NewTask(int id) {
        this.id = id;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        System.out.println("编号" + id + "任务执行ING");
    }

}
