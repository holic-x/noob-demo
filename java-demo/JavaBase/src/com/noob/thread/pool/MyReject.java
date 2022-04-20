package com.noob.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 自定义拒绝策略(饱和策略)
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class MyReject implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义饱和/拒绝策略处理...");
        System.out.println("当前任务被拒绝执行...拒绝执行的任务id是:"+r.toString());
    }
}