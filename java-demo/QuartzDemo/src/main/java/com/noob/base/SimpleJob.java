package com.noob.base;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.xml.crypto.Data;
import java.util.Date;

public class SimpleJob implements Job {

    // 显式地定义一个无参的公共构造函数
    public SimpleJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("SimpleJob任务被执行: " + jobExecutionContext.getJobDetail().getKey() + "  at " + new Date());
    }
}
