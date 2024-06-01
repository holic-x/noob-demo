package com.noob.base.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 异常 demo
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        /**
         * 使用try...catch语句捕获异常
         */
        try
        {
			System.out.println(9/0);
//			Class.forName("java.util.ArrayListhahah");
            FileInputStream fis = new FileInputStream("c:/test.txt");
        }catch (ArithmeticException e) {
            System.out.println("捕获到了数学运算异常的错误...");
        } catch (FileNotFoundException e) {
            System.out.println("捕获到了文件不存在的异常...");
        } catch (Exception e) {
            System.out.println("捕获到了未知的异常...");
        }
    }
}
