package com.noob.base.objects;

// 字符串拼接
class StringSplice{
    public static void main(String[] args) {
        // 1.字符串常量拼接(编译器将其优化为一个常量字符串)
        String str = "hello" + "world";
        System.out.println(str);

         // 2.字符串变量拼接(编译器将其优化为StringBuilder的方式)
        String s = "";
        for (int i = 0; i < 10; i++) {
            // 等价于 s = new StringBuilder(s).append(i).toString();
            s = s + i;
        }
    }
}


/**
 * String类 demo
 */
public class StringDemo {
}
