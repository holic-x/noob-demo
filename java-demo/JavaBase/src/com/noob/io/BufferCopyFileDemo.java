package com.noob.io;

import java.io.*;

/**
 * @description: 通过缓冲流实现文件的拷贝
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class BufferCopyFileDemo {
    public static void main(String[] args) {
        File from = new File("E:/dev/test.txt");
        File to = new File("e:/target/outtest.txt");
        copy(from,to);
    }
    public static void copy(File from,File to)
    {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(from);
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream(to);
            bos = new BufferedOutputStream(fos);
            //定义字节数组
            byte[] buffer = new byte[1024*1024];
            int hasRead = 0;
            try {
                while((hasRead=bis.read(buffer))!=-1)
                {
                    bos.write(buffer, 0, hasRead);
                    bos.flush();//立即写入
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭打开的资源
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
