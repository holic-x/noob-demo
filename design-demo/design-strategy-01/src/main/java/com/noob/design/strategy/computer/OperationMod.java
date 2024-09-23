package com.noob.design.strategy.computer;

/**
 * 具体策略：取模操作
 */
public class OperationMod implements OperationStrategy{
    @Override
    public int operate(int a, int b) {
        return a%b;
    }
}
