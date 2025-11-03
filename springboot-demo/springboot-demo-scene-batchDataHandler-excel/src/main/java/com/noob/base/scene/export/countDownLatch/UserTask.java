package com.noob.base.scene.export.countDownLatch;

import com.noob.base.scene.export.UserData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 独立导出任务：数据处理、导出
 */
@Slf4j
public class UserTask implements Runnable {

    // 任务名称
    private String taskName;

    // 任务关联数据
    private List<UserData> dataList;

    // 导出目标路径
    private String targetPath;

    // CountDownLatch
    private CountDownLatch countDownLatch;


    // 构造器
    public UserTask(String taskName, List<UserData> dataList, String targetPath, CountDownLatch countDownLatch) {
        this.taskName = taskName;
        this.dataList = dataList;
        this.targetPath = targetPath;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        // 执行数据导出操作
        modHandle(1000);
        long endTime = System.currentTimeMillis();
        log.info("模拟数据导出操作，本次导出{}条记录，导出目标路径为{}，执行耗时{}ms", dataList.size(), targetPath, (endTime - startTime));
        // 模拟执行成功
        countDownLatch.countDown(); // 计数器-1
    }

    // 模拟任务执行耗时
    private void modHandle(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            log.error("处理异常", e);
        }
    }
}
