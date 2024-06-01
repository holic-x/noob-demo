package com.noob.base.annoation;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 15:12
 */
public class DeprecatedDemo {

    public static void main(String[] args) {
        A a = new A();
        a.test();
    }
}

// 使用@Deprecated标识这个类已经过期
@Deprecated
class A{
    public void test(){
        System.out.println("testing");
    }
}