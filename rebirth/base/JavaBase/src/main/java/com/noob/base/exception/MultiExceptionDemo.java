package com.noob.base.exception;

/**
 * 多异常捕获 demo
 */
public class MultiExceptionDemo {

    public static void main(String[] args) {
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("当前执行的结果c是:" + c);
            /**
             * 一次性捕获多个异常需用 |隔开
             */
        } catch (IndexOutOfBoundsException|NumberFormatException|ArithmeticException e) {
            System.out.println("捕获到了数组越界，数字格式异常，算数异常 等之一的错误...");
            /**
             * 多异常捕获的变量是默认被final修饰的  所以下方的代码报错...
             * e=new ArithmeticException("aa");
             * 不能够对final修饰的变量进行二次修改赋值
             */
        } catch (Exception e) { //最大的异常始终是放在最后一个捕获
            System.out.println("捕获到了未知的异常...");
            e=new RuntimeException("aaa");
        }
    }

}
