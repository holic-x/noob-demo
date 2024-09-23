package com.noob.design.strategy.computer;

/**
 * 抽象策略（Abstract Strategy）：可以是抽象类或接口定义，定义策略的公共接口方法
 */
public interface OperationStrategy {
    public int operate(int a, int b);
}
