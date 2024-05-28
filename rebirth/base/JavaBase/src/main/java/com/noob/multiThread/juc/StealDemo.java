package com.noob.multiThread.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// 定义计数任务
class CountTask extends RecursiveTask<Integer> {

    // 设置阈值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;
    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就直接计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            // 如果任务大于阈值，则分裂成两个子任务进行计算
            int middle = (end + start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并获取到相应的结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务：负责计算1+2+3+4
        CountTask countTask = new CountTask(1, 4);
        // 执行一个任务
        Future<Integer> res = forkJoinPool.submit(countTask);
        System.out.println(res.get());
    }

}

// 窃取概念：
public class StealDemo {
}
