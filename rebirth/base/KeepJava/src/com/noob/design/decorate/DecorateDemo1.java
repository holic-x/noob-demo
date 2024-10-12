package com.noob.design.decorate;

/**
 * 装饰器模式
 */
public class DecorateDemo1 {
    public static void main(String[] args) {
        // 定义装饰目标
        Target target = new Target();
        // 通过装饰者修饰目标方法(也可以自定义逻辑返回被装饰后的对象)，此处设定目标方法增强（对标代理模式应用）
        Decorater decorater = new Decorater(target);
        decorater.doSth();
    }
}

// 装饰目标（被装饰对象）
class Target{
    public void doSth(){
        System.out.println("i am target");
    }
}

// 装饰者（用于装饰）
class Decorater{
    // 定义被装饰对象（通过提供构造方法初始化被装饰对象）
    private Target target;

    Decorater(Target target){
        this.target = target;
    }

    // 定义方法增强
    public void doSth(){
        System.out.println("i am decorate start");
        target.doSth();
        System.out.println("i am decorate end");
    }
}