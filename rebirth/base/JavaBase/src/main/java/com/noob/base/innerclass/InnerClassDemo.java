package com.noob.base.innerclass;


// 外部类定义
class OuterClass{

    // 内部类定义
    class InnerClass{
        private int value = 10;
        public int getValue(){
            return value;
        }
    }

    // 定义方法返回一个指向内部类的引用
    InnerClass getInnerClass(){
        return new InnerClass();
    }

    // 定义方法使用内部类
    public void doSth(){
        // 和使用普通类一样使用内部类
        InnerClass innerClass = getInnerClass();
        System.out.println(innerClass.getValue());

        // 从外部类的非静态方法之外的任意位置
        OuterClass.InnerClass m = new OuterClass.InnerClass();
        System.out.println(m.getValue());

    }
}

/**
 * 内部类 demo
 */
public class InnerClassDemo {
    public static void main(String[] args) {
        // 定义外部类对象，调用方法
        OuterClass outerClass = new OuterClass();
        outerClass.doSth();
    }
}
