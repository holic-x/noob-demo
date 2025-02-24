package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 11.有一批任务，需要实现按照批次执行，并且动态指定批次
 * - 例如[1,3,5,7]第1批执行、[11,16,19]第2批执行 .....最后没有指定的任务批次就最后一次性执行掉
 * - 批次之间需要按顺序执行，只有前面一批任务执行完成才能执行下一批
 */
public class Solution011_01 {

    // 自定义任务
    static class MyTask implements Runnable{

        private int taskId ;

        MyTask(int taskId){
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程执行任务" + taskId);
        }

    }

    @SneakyThrows
    static void batchHandle(List<List<Integer>> batches,List<MyTask> myTaskList){

        // 创建线程池按照批次处理任务
        ExecutorService executors = Executors.newFixedThreadPool(10);
        // 按照批次封装任务,记录当前批次的待执行任务idx
        Set<Integer> hasExec = new HashSet<>();
        for(List<Integer> batch : batches){
            List<Future> futures = new ArrayList<>();
            for(int idx:batch){
                Future future = executors.submit(myTaskList.get(idx));
                futures.add(future);
                hasExec.add(idx);
            }
            // 等待一批任务执行完成
            for(Future future : futures){
                future.get();// 等待当前批次每个任务执行完成
            }
            // 清理当前批次
            futures.clear();
            System.out.println("当前批次处理完成.....");
        }

        // 处理剩余批次内容
        List<Future> futures = new ArrayList<>();
        for(MyTask task : myTaskList){
            if(!hasExec.contains(task.taskId)){
                // 如果未执行，则加入批次处理
                Future future = executors.submit(task);
                futures.add(future);
            }
            // 等待一批任务执行完成
            for(Future future : futures){
                future.get();// 等待当前批次每个任务执行完成
            }
        }
        // 清理当前批次
        futures.clear();
        System.out.println("剩余批次处理完成.....");

        // 等待批次处理
        // executors.awaitTermination(1,TimeUnit.HOURS);

        // 处理完成，关闭线程池
        executors.shutdown();
    }

    public static void main(String[] args) {
        // 1.处理任务批次，构建任务并划分任务批次
        List<List<Integer>> batches = new ArrayList<>(); // 任务批次数据，根据taskId进行分批处理
        List<Integer> batch1 = Arrays.asList(1,3,5,7);
        batches.add(batch1);
        List<Integer> batch2 = Arrays.asList(11,16,19);
        batches.add(batch2);

        // 2.创建任务集合
        List<MyTask> myTaskList = new ArrayList<>();
        for(int i=0;i<=30;i++){
            MyTask myTask = new MyTask(i);
            myTaskList.add(myTask);
        }

        // 3.调用批量处理方法执行任务集
        batchHandle(batches,myTaskList);
    }

}
