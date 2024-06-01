package com.noob.base.interfaces;

// 模板方法设计模式版本
interface Operations{
    // 执行方法定义
    void execute();

    // 静态方法定义(可变参数列表)，runOps是一个模板方法，使用可变参数列表可以传入任意的Operations参数并按顺序运行
    static void runOps(Operations... ops){
        for (Operations op : ops) {
            op.execute();
        }
    }

    // 静态方法定义
    static void show(String msg){
        System.out.println(msg);
    }
}

// 定义多个类实现Operations
class Bing implements Operations{
    @Override
    public void execute() {
        System.out.println("Bing");
    }
}
class Crack implements Operations{
    @Override
    public void execute() {
        System.out.println("Crack");
    }
}
class Twist implements Operations{
    @Override
    public void execute() {
        System.out.println("Twist");
    }
}

/**
 * 静态方法 demo（java8 运行接口定义静态方法）
 */
public class StaticDemo {
    public static void main(String[] args) {
        // Operations工具类模式操作接口
        Operations.runOps(new Bing(),new Crack(),new Twist());
    }
}
