package com.noob.thread.question11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 按照指定批次执行任务，可指定任务索引进行批量执行
 * 1.定义任务MyTask
 * 2.构建一个创建批量任务的方法
 * 3.构建一个批量执行任务的方法（入参：要执行的任务列表，要执行的任务批次，根据批次依次进行执行）
 *  - 根据批次依次执行，记录已执行的任务索引
 *  - 等待批次任务执行完成（通过Future提供的get方法进行确认），最后执行剩余的任务
 */
public class Solution1 {

    // 批量创建任务
    public List<MyTask> createTaskList(int num) {
        List<MyTask> list = new ArrayList<MyTask>();
        for (int i = 0; i < num; i++) {
            MyTask task = new MyTask(i);
            list.add(task);
        }
        return list;
    }

    // 批量执行任务
    public void executeTask(List<MyTask> list,List<List<Integer>> batchList) throws ExecutionException, InterruptedException {
        // 创建固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 定义已执行的索引序列
        List<Integer> hasExecuteIndex = new ArrayList<>();

        // 根据指定的批次信息执行任务
        for (int i = 0; i < batchList.size(); i++) {
            // 将要执行的批次准备好，批量执行指定索引的任务
            List<Future<Void>> futures = new ArrayList<>();
            for(int idx : batchList.get(i)) {
                Future<Void> future = (Future<Void>) executor.submit(list.get(idx));
                futures.add(future);
                hasExecuteIndex.add(idx);
            }
            // 等待一批任务完成
            for (Future<Void> future : futures) {
                future.get(); // 等待每个任务执行完成
            }
            // 清理futures，进入下一批次的执行
            futures.clear();
            System.out.println("当前批次" + i + "执行完成");
        }

        // 执行剩下任务
        List<Future<Void>> lastFuture = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // 执行未执行的任务
            if(!hasExecuteIndex.contains(i)) {
                lastFuture.add((Future<Void>) executor.submit(list.get(i)));
            }
        }

        // 关闭线程池并等待所有任务完成
        executor.shutdown();
        while(!executor.isTerminated()) {
            // 纯等待
        }

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Solution1 s = new Solution1();
        // 批量创建任务
        List<MyTask> list = s.createTaskList(100);
        // 定义执行批次
        List<List<Integer>> batchList = new ArrayList<>();
        List<Integer> batch1 = Arrays.asList(1,3,5,7);
        List<Integer> batch2 = Arrays.asList(11,13,15,17);
        batchList.add(batch1);
        batchList.add(batch2);

        // 调用方法执行批次
        s.executeTask(list,batchList);
    }


}

class MyTask implements Runnable{

    private int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程执行" + taskId + "任务");
    }
}
