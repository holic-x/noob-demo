package com.noob.design.status.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 活动信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInfo {
    private String activityId; // 活动ID
    private String activityName; // 活动名称
    private Enum<ActivityStatus> status; // 活动状态
    private Date beginTime; // 活动开始时间
    private Date endTime; // 活动结束时间
}
