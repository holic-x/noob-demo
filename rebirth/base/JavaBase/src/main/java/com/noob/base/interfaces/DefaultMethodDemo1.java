package com.noob.base.interfaces;


// 接口定义
interface Ia{
    default void doSth(){
        System.out.println("IA doSth");
    }
}
interface Ib{
    default void doSth(){
        System.out.println("IB doSth");
    }
}

// 存在方法签名相同导致的冲突，编译不允许通过 class Sub1 implements Ia,Ib{ }
class Sub2 implements Ia,Ib{

    // 通过覆写冲突的方法，借助super关键字来明确要调用的方法
    @Override
    public void doSth() {
        // 调用Ia接口的doSth方法
        Ia.super.doSth();
        // 调用Ib接口的doSth方法
        Ib.super.doSth();
        // 自行额外扩展的操作定义
        System.out.println("sub2 doSth");
    }
}

public class DefaultMethodDemo1 {
    public static void main(String[] args) {
        Sub2 sub2 = new Sub2();
        sub2.doSth();
    }
}
