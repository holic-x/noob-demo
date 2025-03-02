package com.noob.algorithm.daily.day13.design.state;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态模式：状态流转
 */
public class StateDemo2 {

    public static void main(String[] args) {
        StateHandler handler = new StateHandler();
        handler.commit(2, 3);
        handler.commit(1, 2);
        handler.pass(1, 2);
        handler.pass(2, 3);
    }

}

/**
 * 状态梳理：
 * 1 拟稿
 * 2 审核
 * 3 审核成功
 * 4 审核失败
 * 5 完结（归档）
 * 基于上述分析将状态流转的过程划分为几个状态之间的流转，每个操作即每个状态执行的操作
 * 但并不是每个状态都可以执行所有的操作，需对前置状态进行校验(例如只能从审核态=>审核成功或审核失败，而不能由其他状态转过来)
 * 所谓操作，即触发状态流转的方法定义，可以有如下设计
 * - 提交（1->2）
 * - 审核通过（2->3）
 * - 审核拒绝（2->4）
 * - 归档（3->5、4->5）
 */

// ① 定义状态（抽象状态）
abstract class State {
    // 流转方法定义（默认设置一个操作处理结果状态，由具体子类来进行优化）

    // 提交
    public void commit(int prevState, int nextState) {
        System.out.println("非法操作");
    }

    // 审核通过
    public void pass(int prevState, int nextState) {
        System.out.println("非法操作");
    }

    // 审核拒绝
    public void reject(int prevState, int nextState) {
        System.out.println("非法操作");
    }

    // 归档
    public void finish(int prevState, int nextState) {
        System.out.println("非法操作");
    }
}

// ② 具体状态子类
class DraftState extends State {

    @Override
    public void commit(int prevState, int nextState) {
        System.out.println("状态由" + prevState + "变更为" + nextState);
    }
}

class AduitState extends State {
    @Override
    public void pass(int prevState, int nextState) {
        System.out.println("审核通过");
    }

    @Override
    public void reject(int prevState, int nextState) {
        System.out.println("审核拒绝");
    }
}

// ③ 定义状态控制类
class StateHandler {
    // 构建状态映射
    static Map<Integer, State> map = new HashMap<>();

    static {
        map.put(1, new DraftState());
        map.put(2, new AduitState());
//        map.put(1,new AduitState()); ......
    }

    // 定义方法执行操作
    public void commit(int prevState, int nextState) {
        map.get(prevState).commit(prevState, nextState);
    }

    public void pass(int prevState, int nextState) {
        map.get(prevState).pass(prevState, nextState);
    }

}
