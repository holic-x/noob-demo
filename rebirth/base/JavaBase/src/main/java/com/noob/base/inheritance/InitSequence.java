package com.noob.base.inheritance;


class Parent{
    public Parent() {
        System.out.println(++b1+"父类构造方法");
    }
    static int a1=0;
    static {
        System.out.println(++a1+"父类static");
    }
    int b1=a1;
    {
        System.out.println(++b1+"父类代码块");
    }
}
class Sub extends Parent{
    public Sub() {
        System.out.println(++b2+"子类构造方法");
    }
    static {
        System.out.println(++a1+"子类static");
    }
    int b2=b1;
    {
        System.out.println(++b2 + "子类代码块");
    }
}



/**
 * 初始化顺序（父类、子类）
 */
public class InitSequence {
    public static void main(String[] args) {
        // 静态>非静态；子类>父类；成员变量、代码块>构造方法
        Sub sub=new Sub();
    }
}
