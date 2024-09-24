package com.noob.design.status.sp.handler;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.model.Result;
import com.noob.design.status.sp.event.AduitState;
import com.noob.design.status.sp.event.EditingState;
import com.noob.design.status.sp.event.State;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态处理服务（提供对状态服务的统一控制中心）
 */
public class StateHandler {

    // 定义状态列表和对应的event映射
    private Map<Enum<ActivityStatus>, State> stateMap = new HashMap<Enum<ActivityStatus>, State>();

    // 初始化状态列表
    public StateHandler(){
        stateMap.put(ActivityStatus.Editing,new EditingState());
        stateMap.put(ActivityStatus.Aduit,new AduitState());
        // ...... 其他状态定义扩展 ......
    }

    // ---------- 提供统一的处理方法 ----------

    // 活动提审
    public Result aduit(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).aduit(activityId,currentStatus);
    }

    // 审核通过
    public Result checkPass(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).checkPass(activityId,currentStatus);
    }

    // 审核拒绝
    public Result checkRefuse(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).checkRefuse(activityId,currentStatus);
    }

    // 撤审
    public Result checkRevoke(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).checkRevoke(activityId,currentStatus);
    }

    // 活动关闭
    public Result close(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).close(activityId,currentStatus);
    }

    // 活动开启
    public Result open(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).open(activityId,currentStatus);
    }

    // 活动执行
    public Result doing(String activityId, Enum<ActivityStatus> currentStatus){
        return stateMap.get(currentStatus).doing(activityId,currentStatus);
    }

}
