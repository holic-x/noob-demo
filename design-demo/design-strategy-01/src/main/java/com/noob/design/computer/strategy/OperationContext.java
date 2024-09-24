package com.noob.design.computer.strategy;

/**
 * 环境（context）：用于维护一个策略对象引用，将Client请求委派到对应的策略对象进行调度
 */
public class OperationContext {

    // 维护相应的策略对象
    private OperationStrategy operationStrategy;

    // 初始化环境
    public OperationContext(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    // 执行具体策略
    public int execute(int a, int b) {
        return this.operationStrategy.operate(a,b);
    }
}
