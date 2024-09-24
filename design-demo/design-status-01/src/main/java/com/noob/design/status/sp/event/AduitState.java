package com.noob.design.status.sp.event;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.model.Result;

/**
 * 提审状态：
 * 可由提审状态->撤审、通过、拒绝、关闭
 */
public class AduitState extends State{
    @Override
    public Result aduit(String activityId, Enum<ActivityStatus> currentStatus) {
        return Result.ILLEGAL;
    }

    @Override
    public Result checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        System.out.println( activityId + "活动状态变更 from " + currentStatus + " to " + ActivityStatus.Pass );
        return Result.SUCCESS;
    }

    @Override
    public Result checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        System.out.println( activityId + "活动状态变更 from " + currentStatus + " to " + ActivityStatus.Refuse );
        return Result.SUCCESS;
    }

    @Override
    public Result checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return null;
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
