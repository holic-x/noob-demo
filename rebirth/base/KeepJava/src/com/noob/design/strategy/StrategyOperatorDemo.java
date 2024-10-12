package com.noob.design.strategy;

/**
 * 策略模式：将算法实现和算法调用进行解耦，根据不同的环境切换相应的算法调用
 */
public class StrategyOperatorDemo {

    public static void main(String[] args) {
        // 初始化环境
        OperatorContext context = new OperatorContext(new OperatorAdd());
        System.out.println(context.doOperator(1,2));;
    }

}

// 抽象算法（抽象策略）:例如此处的计算器操作定义
interface Operator{
    int operate(int a,int b);
}

// 具体算法（具体策略）：例如此处具体的操作策略（加减乘除）
class OperatorAdd implements Operator{
    @Override
    public int operate(int a, int b) {
        return a+b;
    }
}
class OperatorSub implements Operator{
    @Override
    public int operate(int a, int b) {
        return a-b;
    }
}
class OperatorMul implements Operator{
    @Override
    public int operate(int a, int b) {
        return a*b;
    }
}
class OperatorDiv implements Operator{
    @Override
    public int operate(int a, int b) {
        if(b==0){
            throw new ArithmeticException();
        }
        return a/b;
    }
}

// 环境（根据不同的环境调用不同的操作）
class OperatorContext{
    // 定义相应的策略
    private Operator operate;
    
    // 初始化环境
    public OperatorContext(Operator operate){
        this.operate = operate;
    }
    
    // 实际执行逻辑（调用策略执行）
    public int doOperator(int a,int b){
        return operate.operate(a,b);
    }
}
