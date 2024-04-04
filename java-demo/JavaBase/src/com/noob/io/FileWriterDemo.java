package com.noob.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileWriterDemo {
    /**
     * FileWriter:字符输出流
     */
    public static void main(String[] args) {
        test();
    }
    /**
     * 步骤分析：
     * 1.加载指定路径下的文件
     * 2.创建FileReader对象fw，初始化为null
     * 3.为fr分配空间，完成数据写入指定文件的操作
     * 4.关闭输出流
     */
    public static void test()
    {
        //1.加载指定路径下的文件
        File file = new File("e:/dev/write.txt");
        //2.创建FileReader对象fr，初始化为null
        FileWriter fw = null;
        //3.为fr分配空间，完成数据写入指定文件的操作
        try {
            //为指定的文件创建输出流对象，并实现在后方追加内容
            fw = new FileWriter(file,true);
            /**
             * 写入数据有多种方式：
             * a.按照指定的要求,一次写入一个字符串
             * b.亦可一次写入指定的字符数的数据
             */
            // 写入数据,可自行实现手动换行\r\n
            fw.write("hello java...\r\n");
            fw.write("冲冲冲...\r\n");
            //立即写入
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                //4.关闭打开的资源
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
