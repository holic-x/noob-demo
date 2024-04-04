package com.noob.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileCopyDemo {
    /**
     * 练习：
     * 利用输入流和输出流实现文件拷贝操作
     * 此处用FileInputStream和FileOutputStream实现
     * 技巧：
     * 如果操作的是纯文本文件,需要使用字符流用以防止出现乱码问题
     * 而如果操作的是二进制文件则使用字节流进行处理
     */
    /**
     * 步骤分析：
     * 1.加载指定路径的相关文件
     * 2.分别为指定的文件创建输入流和输出流对象
     * 3.此处使用字节（byte）类型的数组实现数据的读取和写入
     * 4.完成相关操作之后关闭打开的资源
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //加载指定路径的文件来源和目的文件
        File from = new File("e:/dev/create.sql");
        File to = new File("e:/dev/newCreate.sql");
        /**
         * 如果目的路径的文件不存在，则通过createNewFile创建指定的文件
         */
        if(!to.exists())
        {
            to.createNewFile();
        }
        copy(from,to);
    }
    // 为了方便叙述，此处将涉及到的异常全部抛给上一级进行处理
    public static void copy(File from, File to) throws Exception
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //为指定的文件分别创建输入流和输出流对象
        fis = new FileInputStream(from);
        fos = new FileOutputStream(to,true);
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        while((hasRead=fis.read(buffer))!=-1)
        {
            fos.write(buffer, 0, hasRead);
            //立即写入
            fos.flush();
        }
    }
}