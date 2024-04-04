package com.noob.thread.pool;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class MyTask implements Runnable {

    private int taskId;

    private String taskName;

    public MyTask(int taskId, String taskName) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.println("run taskId ="+this.taskId);
            Thread.sleep(5*1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return Integer.toString(this.taskId);
    }

}