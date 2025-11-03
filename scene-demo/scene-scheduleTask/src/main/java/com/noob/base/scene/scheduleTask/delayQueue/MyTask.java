package com.noob.base.scene.scheduleTask.delayQueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
class MyTask implements Delayed {
    private int taskId; // 任务ID
    private String taskContent; // 任务内容
    private long executeTime; // 任务执行时间戳(ms)

    public MyTask(int taskId, String taskContent, long delay, TimeUnit timeUnit) {
        this.taskId = taskId;
        this.taskContent = taskContent;
        this.executeTime = System.currentTimeMillis() + timeUnit.toMillis(delay); // 任务执行时间戳 = 当前系统时间 + 指定单位延迟时间
    }

    // 获取剩余延迟时间
    @Override
    public long getDelay(TimeUnit unit) {
        // 计算剩余时间 = 执行时间 - 当前时间
        long remaining = executeTime - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    // 比较两个任务的执行时间（用于队列排序）
    @Override
    public int compareTo(Delayed o) {
        if (this == o) {
            return 0;
        }
        MyTask other = (MyTask) o;
        long diff = this.executeTime - other.executeTime;
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}