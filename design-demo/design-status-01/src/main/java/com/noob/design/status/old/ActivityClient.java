package com.noob.design.status.old;

import com.noob.design.status.model.ActivityStatus;

/**
 * 活动客户端测试demo
 */
public class ActivityClient {



    public static void main(String[] args) {
        // 模拟活动状态流转场景
        ActivityService activityService = new ActivityService();
        activityService.execStatus("1", ActivityStatus.Editing, ActivityStatus.Check);

    }

}
