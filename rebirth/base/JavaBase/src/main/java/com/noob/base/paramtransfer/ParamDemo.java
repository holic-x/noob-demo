package com.noob.base.paramtransfer;

/**
 * 参数传递 demo
 */
public class ParamDemo {

    // 此处的方法定义中的参数列表为String name（name为形式参数）
    private static void show(String name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        String name = "noob"; // 此处的name为实际参数
        show(name);// 将实际参数传入方法
        System.out.println(name);
    }

}
