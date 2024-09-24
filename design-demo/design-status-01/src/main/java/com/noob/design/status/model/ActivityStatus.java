package com.noob.design.status.model;

/**
 * 活动状态枚举
 */
public enum ActivityStatus {
    // 活动状态设定（1创建编辑、2提审（待审核）、3撤审、4审核通过、5审核拒绝、6活动关闭、7活动开启、8活动中）
    Editing,Aduit,CancelAduit,Pass,Refuse,Close,Open,Doing
}
