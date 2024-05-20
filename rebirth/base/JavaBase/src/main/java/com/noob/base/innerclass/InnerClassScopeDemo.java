package com.noob.base.innerclass;

// 局部内部类
class LocalOuterClass{
    public void doSth(){
        // 局部内部类定义：在方法的作用域中定义内部类，而不是其他类的作用域
        final class Inner{
            private String val = "hello inner class";
            public String getVal() {
                return val;
            }
        }
        // 调用内部类方法
        Inner inner = new Inner();
        System.out.println(inner.getVal());
    }

    public static void main(String[] args) {
        LocalOuterClass localOuterClass = new LocalOuterClass();
        localOuterClass.doSth();
    }
}

// 任意作用域嵌入内部类
class RandomScopeOuterClass{

    public void doSth(boolean b){
        // 满足条件才执行
        if(b){
            // 在任意作用域中嵌入内部类
            class Inner{
                private String val = "hello inner class";
                public String getVal() {
                    return val;
                }
            }
            // 调用内部类方法
            Inner inner = new Inner();
            System.out.println(inner.getVal());
        }
        // Inner inner = new Inner(); 脱离内部类的作用域，不可在此处引用
    }

    public static void main(String[] args) {
        RandomScopeOuterClass randomScopeOuterClass = new RandomScopeOuterClass();
        randomScopeOuterClass.doSth(true);
    }

}







/**
 * 内部类作用域 demo
 */
public class InnerClassScopeDemo {
}
