package com.noob.base.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LambdaTester02
 * @Description TODO
 * @Author Huh-x
 */
public class LambdaTester02 {

    public static void main(String[] args) {
        test01();

        test02();

        test03();
        test04();


    }

    private static void test04() {
        // Lambda应用4：方法引用
        List<String> list = Arrays.asList("baidu", "tianmao", "taobao");
        list.forEach(System.out::println);
    }

    private static void test03() {
        // Lambda应用3:集合遍历
        List<String> list = Arrays.asList("baidu", "tianmao", "taobao");
        list.forEach((obj)->{
            System.out.println(obj);
        });
    }

    private static void test02() {
        // Lambda应用2：实现MathOperation接口方法，实现两数之和输出
        MathOperation mathOperation = (x, y) -> {
            return x + y;
        };
        // 调用Lambda表达式
        int result = mathOperation.add(1, 2);
        System.out.println(result);
    }

    private static void test01() {
        // Lambda应用1：Runnable创建线程(Runnable是一个函数式接口，其只包含一个抽象方法，则可通过Lambda实现该将接口)
        Runnable r = () -> {
            System.out.println("Runnable是一个函数式接口");
        };
        r.run();
    }

}
