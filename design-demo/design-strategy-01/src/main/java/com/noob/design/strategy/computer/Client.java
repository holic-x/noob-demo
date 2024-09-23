package com.noob.design.strategy.computer;

/**
 * 客户端调用
 */
public class Client {
    public static void main(String[] args) {
        // 测试新增操作
        OperationContext context = new OperationContext(new OperationAdd());
        System.out.println(context.execute(1,2));

        // 相减操作
        context = new OperationContext(new OperationSubtract());
        System.out.println(context.execute(1,2));

        // 相乘操作
        context = new OperationContext(new OperationMultiply());
        System.out.println(context.execute(1,2));

        // 取模操作
        context = new OperationContext(new OperationMod());
        System.out.println(context.execute(10,2));
    }
}
