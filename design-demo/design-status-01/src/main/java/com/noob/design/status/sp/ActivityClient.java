package com.noob.design.status.sp;

import com.noob.design.status.model.ActivityStatus;
import com.noob.design.status.sp.handler.StateHandler;

/**
 * 客户端测试
 */
public class ActivityClient {
    public static void main(String[] args) {
        StateHandler stateHandler = new StateHandler();
        System.out.println(stateHandler.checkRefuse("1", ActivityStatus.Aduit));// 成功
        System.out.println(stateHandler.checkRefuse("1", ActivityStatus.Editing));// 非法操作
    }
}
