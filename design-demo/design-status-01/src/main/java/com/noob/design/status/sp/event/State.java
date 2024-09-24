package com.noob.design.status.sp.event;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.model.Result;

import java.awt.*;

/**
 * 状态抽象类：定义状态流转涉及的相关方法
 */
public abstract class State {

    // 活动提审
    public abstract Result aduit(String activityId, Enum<ActivityStatus> currentStatus);

    // 审核通过
    public abstract Result checkPass(String activityId, Enum<ActivityStatus> currentStatus);

    // 审核拒绝
    public abstract Result checkRefuse(String activityId, Enum<ActivityStatus> currentStatus);

    // 撤审
    public abstract Result checkRevoke(String activityId, Enum<ActivityStatus> currentStatus);

    // 活动关闭
    public abstract Result close(String activityId, Enum<ActivityStatus> currentStatus);

    // 活动开启
    public abstract Result open(String activityId, Enum<ActivityStatus> currentStatus);

    // 活动执行
    public abstract Result doing(String activityId, Enum<ActivityStatus> currentStatus);

}
