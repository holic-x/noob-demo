package com.noob.design.status.old;

import com.noob.design.status.model.ActivityStatus;

/**
 * 活动服务定义
 */
public class ActivityService {

    /**
     * 校验状态有效性
     *
     * @param currentStatus
     * @param afterStatus
     * @return
     */
    private static boolean validOper(ActivityStatus currentStatus, ActivityStatus afterStatus) {

        /**
         * 活动状态变更约定：
         * 1.编辑      ->提审、关闭
         * 2.提审      ->撤审、通过、拒绝、关闭
         * 3.撤审      ->编辑
         * 4.通过      ->活动中
         * 5.拒绝      ->撤审
         * 6.关闭      ->编辑、开启
         * 7.开启      ->关闭、活动中
         * 8.活动中    ->关闭
         * 如果不满足约定则视为非法操作
         */
        if (ActivityStatus.Editing == currentStatus) {
            // 编辑      ->提审、关闭
            return ActivityStatus.Aduit == afterStatus || ActivityStatus.Close == afterStatus;
        } else if (ActivityStatus.Aduit == currentStatus) {
            // 提审      ->撤审、通过、拒绝、关闭
            return ActivityStatus.CancelAduit == afterStatus || ActivityStatus.Pass == afterStatus || ActivityStatus.Refuse == afterStatus || ActivityStatus.Close == afterStatus;
        } else if (ActivityStatus.CancelAduit == currentStatus) {
            // 撤审      ->编辑
            return ActivityStatus.Editing == afterStatus;
        } else if (ActivityStatus.Pass == currentStatus) {
            // 通过      ->活动中
            return ActivityStatus.Doing == afterStatus;
        } else if (ActivityStatus.Refuse == currentStatus) {
            // 拒绝      ->撤审
            return ActivityStatus.CancelAduit == afterStatus;
        } else if (ActivityStatus.Close == currentStatus) {
            // 关闭      ->编辑、开启
            return ActivityStatus.Editing == afterStatus || ActivityStatus.Open == afterStatus;
        } else if (ActivityStatus.Open == currentStatus) {
            // 开启      ->关闭、活动中
            return ActivityStatus.Close == afterStatus || ActivityStatus.Doing == afterStatus;
        } else if (ActivityStatus.Doing == currentStatus) {
            // 开启      ->关闭、活动中
            return ActivityStatus.Close == afterStatus;
        } else {
            System.out.println(currentStatus + "状态非法");
            return false;
        }
    }

    /**
     * 执行状态变更
     *
     * @param activityId
     * @param currentStatus
     * @param afterStatus
     */
    public static synchronized void execStatus(String activityId, ActivityStatus currentStatus, ActivityStatus afterStatus) {
        // 也可以模拟根据活动ID查找对应的状态 ActivityStatus currentStatus = ActivityStatus.Check;
        // todo 业务场景中需判断当前状态，校验状态变更是否符合约定 （例如A->B的状态变更是否合理），此处作为扩展项
        System.out.println("模拟校验状态变更有效性：" + activityId + "活动状态变更-" + currentStatus + "=>" + afterStatus);
        boolean validOperFlag = validOper(currentStatus, afterStatus);
        if (validOperFlag) {
            // 进行状态流转
            System.out.println("状态变更成功：" + afterStatus);
        } else {
            System.out.println("状态表更操作非法,拒绝操作！");
        }
    }

}
