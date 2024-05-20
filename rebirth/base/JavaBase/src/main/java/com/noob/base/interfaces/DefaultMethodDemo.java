package com.noob.base.interfaces;



// 多个接口定义：定义default method扩展基类功能
interface One{
    default void first(){
        System.out.println("first");
    }

    // 定义接口（默认包含public static final属性）
    void doSth();
}

interface Two{
    default void second(){
        System.out.println("second");
    }
}

interface Three{
    default void third(){
        System.out.println("third");
    }
}

// 子类实现多个接口，拥有default method扩展功能
class Sub implements One,Two,Three{

    // 实现One接口的doSth方法
    @Override
    public void doSth() {
        System.out.println("do sth");
    }
}

/**
 * java default method demo
 */
public class DefaultMethodDemo {
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.doSth();
        // 拥有接口的default method方法扩展
        sub.first();
        sub.second();
        sub.third();
    }
}
