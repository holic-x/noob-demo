package com.noob.algorithm.daily.design._2strategy;

// 抽象策略
interface Strategy {
    public void doSth();
}

// 具体策略
class AStrategy implements Strategy {

    @Override
    public void doSth() {
        System.out.println("do A");
    }
}

class BStrategy implements Strategy {

    @Override
    public void doSth() {
        System.out.println("do B");
    }
}

// 环境装配和策略实施（维护一个策略，用于将请求转发到不同的策略实施）
class Context {
    // 此处实际上不局限于策略的维护放在哪里，可以作为类成员变量（然后构造器初始化），也可以作为方法参数传入，目的是通过策略调用执行算法
    public void test(Strategy strategy) {
        // 根据不同的策略执行不同的内容
        strategy.doSth();
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        Context context = new Context();
        context.test(new AStrategy());
        context.test(new BStrategy());
    }
}
