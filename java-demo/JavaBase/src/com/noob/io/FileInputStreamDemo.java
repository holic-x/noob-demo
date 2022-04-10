package com.noob.io;

import java.io.File;
import java.io.FileInputStream;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/4/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileInputStreamDemo {
    /**
     * FileInputStream:字节输入流
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception
    {
        //1.从指定路径加载文件
        File file = new File("e:/dev/test.txt");
        //2.利用FileInputStream创建输入流对象（需要处理异常或者是向上一级抛出异常）
        FileInputStream fis = new FileInputStream(file);
        //3.通过FileInputStream对象读取指定路径下的文件的内容
        /**
         * 操作步骤：
         * a.定义一个字节类型（byte）的数组（每次读取的字节数）
         * b.定义一个临时变量hasRead
         * c.如果有相应的内容（即返回值不为-1）则输出（获取）
         * d.根据需求输出或者是处理相关的内容
         */
        byte[] buffer = new byte[3];
        int hasRead = 0;// 查找的位置
        while((hasRead=fis.read(buffer))!=-1)
        {
            String str = new String(buffer,0,hasRead);
            System.out.print(str);
        }
        /**
         * 由结果分析：
         * 如果设置一次性读取的字节数过小，按照每次读取的字节进行读取，则针对中文字符有可能读出来的数据是乱码
         */
    }
}
