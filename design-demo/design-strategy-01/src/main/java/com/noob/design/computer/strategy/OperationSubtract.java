package com.noob.design.computer.strategy;

/**
 * 具体策略：相减操作
 */
public class OperationSubtract implements OperationStrategy{
    @Override
    public int operate(int a, int b) {
        return a-b;
    }
}
