package com.noob.design.state;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态模式
 * 通过状态来实现节点状态的切换
 */
public class StateDemo {
    public static void main(String[] args) {
        // 普通流转逻辑实现
        InitState initState = new InitState();
        initState.commit(0, 1);// 合法
        initState.commit(0, 3);// 非法
        initState.pass(0, 3);// 非法

        // 通过控制器进行流转
        StateHandle stateHandle = new StateHandle();
        stateHandle.commit(0, 1);// 合法
        stateHandle.commit(0, 3);// 非法
        stateHandle.pass(0, 2);// 非法
    }
}

/**
 * 确认流转规则：初始化 init 0 -> 拟稿 write 1 -> 审核 approve 2 -> 审核成功 approveSuccess 3 -> 审核失败 approveFail 4 ->结束 5
 * 状态流转规则：根据每个状态进行分析当前状态可以通过什么操作变更下一个状态
 * （只考虑每个节点的出（即其会变为什么状态），而不考虑入（因为它的入是由其他节点的出来决定的，因此只需要考虑一头，避免状态流转概念混淆））
 * 初始化 -> 拟稿 （通过[提交]操作）
 * 拟稿 -> 审核 （通过[提交]操作）
 * 审核 -> 审核成功（通过[通过]操作）
 * 审核 -> 审核失败（通过[拒绝]操作）
 * 审核成功 -> 结束 (通过[办结]操作)
 * 审核失败 -> 拟稿 (通过[回退]操作)
 * 审核失败 -> 结束（通过[办结]操作）
 * <p>
 * 基于上述分析，状态可以分为6个状态，而每个"操作"就是每个状态执行的操作，但并不是每个状态都可以执行这些操作（需要进行排除）
 */


// 定义状态及可能的流转方法
interface State {
    // 流转方法定义(提交、通过、拒绝、办结、回退)
    public void commit(int prevState, int nextState);

    public void pass(int prevState, int nextState);

    public void reject(int prevState, int nextState);

    public void finish(int prevState, int nextState);

    public void back(int prevState, int nextState);
}

// 每个状态的控制定义（初始化 init 0 -> 拟稿 write 1 -> 审核 approve 2 -> 审核成功 approveSuccess 3 -> 审核失败 approveFail 4 ->结束 end 5）
class InitState implements State {
    // 以初始化状态为例：它只有一种状态转化的情况（其他的操作对它来说都是非法的）
    @Override
    public void commit(int prevState, int nextState) {
        // 校验流转条件处理不同的流转逻辑（例如此处是只有0-1的情况）
        if (prevState == 0 && nextState == 1) {
            System.out.println("状态从" + prevState + "切换到" + nextState);
        } else {
            System.out.println("非法操作哦哦哦哦哦哦");
        }
    }

    @Override
    public void pass(int prevState, int nextState) {
        System.out.println("操作非法，拒绝执行");
    }

    @Override
    public void reject(int prevState, int nextState) {
        System.out.println("操作非法，拒绝执行");
    }

    @Override
    public void finish(int prevState, int nextState) {
        System.out.println("操作非法，拒绝执行");
    }

    @Override
    public void back(int prevState, int nextState) {
        System.out.println("操作非法，拒绝执行");
    }
}

// 状态服务控制类
class StateHandle {
    // 定义所有的状态集（状态和对应的状态实现类映射）
    private static Map<Integer, State> stateMap = new HashMap<Integer, State>();

    // 初始化状态集
    static {
        stateMap.put(0, new InitState());
//        stateMap.put(1,new WriteState());
//        stateMap.put(2,new ApproveState());
//        stateMap.put(3,new ApproveSuccessState());
//        stateMap.put(4,new ApproveFailState());
//        stateMap.put(5,new EndState());
    }

    // 提供方法进行状态流转
    public void commit(int prevState, int nextState) {
        stateMap.get(prevState).commit(prevState, nextState);
    }

    public void pass(int prevState, int nextState) {
        stateMap.get(prevState).pass(prevState, nextState);
    }

    public void reject(int prevState, int nextState) {
        stateMap.get(prevState).reject(prevState, nextState);
    }

    public void finish(int prevState, int nextState) {
        stateMap.get(prevState).finish(prevState, nextState);
    }

    public void back(int prevState, int nextState) {
        stateMap.get(prevState).back(prevState, nextState);
    }

}
