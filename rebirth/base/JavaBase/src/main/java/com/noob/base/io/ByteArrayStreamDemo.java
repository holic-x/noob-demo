package com.noob.base.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 内存字节流（一般用于操作一些临时信息）
 */
public class ByteArrayStreamDemo {

    public static void main(String[] args) throws IOException {
        // 定义字符串（全部大写）
        String str = "HELLO WORLD";

        // 定义内存字节流对象（输入输出），用子类实例化父类对象
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // 从bis（字符输入流）中读取内容
        int temp = 0;
        while ((temp=bis.read())!=-1){
            // 对读取的内容进行处理（例如转为小写），并将内容存入字节流
            char ch = (char)temp;
            bos.write(Character.toLowerCase(ch));
        }

        // 去除bos中的内容
        System.out.println(bos.toString());

        // 关闭流
        bis.close();
        bos.close();
    }

}
