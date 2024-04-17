package com.noob.base.lambda;

/**
 * @ClassName MathOperation
 * @Description 函数式接口定义：实现两个数字之和
 * @Author Huh-x
 */
@FunctionalInterface
public interface MathOperation {
    // 定义抽象方法
    public abstract int add(int x, int y);
}
