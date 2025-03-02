package com.noob.algorithm.daily.day13.design.decorate;

/**
 * 装饰者模式：实现对方法的增强
 */
public class SimpleDecorateDemo {

    // ① 定义装饰目标（Target）：被装饰对象
    static class Target {
        public void doSth() {
            System.out.println("do sth......");
        }
    }

    // ② 定义装饰者（Decorate）
    static class Decorate {
        // 定义装饰目标
        private Target target;

        // 构造器（接收并初始化装饰目标）
        Decorate(Target target) {
            this.target = target;
        }

        // 定义装饰方法
        public void doMoreSth() {
            System.out.println("do more sth.....");
            target.doSth();
        }
    }

    public static void main(String[] args) {
        Target target = new Target(); // 定义装饰目标
        Decorate decorate = new Decorate(target); // 定义装饰者
        decorate.doMoreSth(); // 调用装饰方法完成方法增强
    }

}
