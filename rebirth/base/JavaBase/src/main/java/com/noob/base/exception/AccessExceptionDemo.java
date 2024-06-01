package com.noob.base.exception;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 访问异常信息 demo
 */
public class AccessExceptionDemo {
    /**
     * 通过相关的方法了解具体捕获到的异常信息
     */
    public static void main(String[] args) {
        try
        {
            FileInputStream fis = new FileInputStream("c://a.txt");
        }catch(IOException e)
        {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            //了解具体的异常信息
            e.printStackTrace();
        }
    }
}
