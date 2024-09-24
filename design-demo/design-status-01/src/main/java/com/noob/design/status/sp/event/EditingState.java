package com.noob.design.status.sp.event;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.model.Result;

/**
 * 编辑状态：
 * 可由编辑状态->提审、关闭，其他状态都是非法的
 */
public class EditingState extends State {
    @Override
    public Result aduit(String activityId, Enum<ActivityStatus> currentStatus) {
        System.out.println( activityId + "活动状态变更 from " + currentStatus + " to " + ActivityStatus.Aduit );
        return Result.SUCCESS;
    }

    @Override
    public Result checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }

    @Override
    public Result checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }

    @Override
    public Result checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }

    @Override
    public Result close(String activityId, Enum<ActivityStatus> currentStatus) {
        System.out.println( activityId + "活动状态变更 from " + currentStatus + " to " + ActivityStatus.Close );
        return Result.SUCCESS;
    }

    @Override
    public Result open(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }

    @Override
    public Result doing(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }
}
