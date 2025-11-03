package com.noob.base.scene.export;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 独立导出任务：数据处理、导出
 */
@Slf4j
public class ExportTask implements Runnable {

    // 任务名称
    private String taskName;

    // 任务关联数据
    private List<UserData> dataList;

    // 导出目标路径
    private String targetPath;

    // 构造器
    public ExportTask(String taskName, List<UserData> dataList, String targetPath) {
        this.taskName = taskName;
        this.dataList = dataList;
        this.targetPath = targetPath;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        // 执行数据导出操作
        modHandle(1000);
        long endTime = System.currentTimeMillis();
        log.info("模拟数据导出操作，本次导出{}条记录，导出目标路径为{}，执行耗时{}ms", dataList.size(), targetPath, (endTime - startTime));
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
