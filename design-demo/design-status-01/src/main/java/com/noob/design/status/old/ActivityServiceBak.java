package com.noob.design.status.old;

import com.noob.design.status.model.ActivityInfo;
import com.noob.design.status.model.ActivityStatus;

/**
 * 活动服务定义
 */
public class ActivityServiceBak {

    /**
     * 根据活动ID查询活动信息
     * @param activityId
     * @return
     */
    public static ActivityInfo queryActivityInfo(String activityId) {
        // 模拟查询活动信息
        ActivityInfo activityInfo = new ActivityInfo();
        return activityInfo;
    }

    /**
     * 根据活动ID查询活动状态
     * @param activityId
     * @return
     */
    public static Enum<ActivityStatus> queryActivityStatus(String activityId) {
        // 模拟查询活动状态
        return ActivityStatus.Aduit;
    }


    /**
     * 校验状态有效性
     * @param currentStatus
     * @param afterStatus
     * @return
     */
    private static boolean validOper(ActivityStatus currentStatus, ActivityStatus afterStatus){
        return false;
    }



    /**
     * 执行状态变更
     * @param activityId
     * @param currentStatus
     * @param afterStatus
     */
    public static synchronized void execStatus(String activityId, ActivityStatus currentStatus, ActivityStatus afterStatus) {
        // 也可以模拟根据活动ID查找对应的状态 ActivityStatus currentStatus = ActivityStatus.Check;
        // todo 业务场景中需判断当前状态，校验状态变更是否符合约定 （例如A->B的状态变更是否合理），此处作为扩展项
        System.out.println("模拟校验状态变更有效性：" + activityId + "活动状态变更-" + currentStatus + "=>" + afterStatus);
        boolean validOperFlag = validOper(currentStatus, afterStatus);
        if(validOperFlag){
            // 进行状态流转
            System.out.println("状态变更成功：" + afterStatus);
        }else{
            System.out.println("状态表更操作非法");
        }
    }

}
