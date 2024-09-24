package com.noob.design.computer.strategy;

/**
 * 具体策略：相加操作
 */
public class OperationAdd implements OperationStrategy{
    @Override
    public int operate(int a, int b) {
        return a+b;
    }
}
