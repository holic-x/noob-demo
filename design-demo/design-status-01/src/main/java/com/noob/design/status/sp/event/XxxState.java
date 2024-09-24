package com.noob.design.status.sp.event;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.model.Result;

/**
 * xxxState 状态 继承抽象父类
 * 重写本状态需要处理的方法（处理入和出）
 */
public class XxxState extends State {

    @Override
    public Result aduit(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result close(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result open(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }

    @Override
    public Result doing(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
    }
}
