package com.noob.base.inheritance;


/**
 * 计算器
 */
class Calculator{
    // add方法
    public int add(int a, int b){
        return a+b;
    }

    // add方法：可以看作是上面add方法的重载（同一个类中方法名相同参数列表不同）
    public int add(int a, int b, int c){
        return a+b+c;
    }
}

/**
 * 方法重载
 */
public class OverloadDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(1,2));
        System.out.println(calculator.add(1,2,3));
    }
}
