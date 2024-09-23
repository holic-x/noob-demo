package com.noob.design.strategy.computer;

/**
 * 具体策略：相乘操作
 */
public class OperationMultiply implements OperationStrategy{
    @Override
    public int operate(int a, int b) {
        return a*b;
    }
}
