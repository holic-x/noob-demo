package com.noob.io;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileOutputStreamDemo {
    /**
     * FileOutputStream:字节输出流
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
        /**
         * 步骤分析：
         * 1.加载指定路径下的文件
         * 2.创建FileOutputStream对象fos，初始化为null
         * 3.为fos分配空间，完成数据写入指定文件的操作
         * 4.关闭输出流
         */
        //1.加载指定路径下的文件
        File file = new File("e:/dev/write.txt");
        //2.创建FileOutputStream对象fos,初始化为null
        FileOutputStream fos = null;
        //3.为fos分配空间,完成数据写入指定文件的操作
        fos = new FileOutputStream(file,true);
        //向指定文件中写入数据，并实现手动换行
        fos.write("haha,fos测试ing\r\n".getBytes());
        fos.write("xixi,输出流测试完成\r\n".getBytes());
        //立即写入
        fos.flush();
        //4.关闭输出流,由于此处是将异常抛给上一级进行处理,则无法使用finally语句
    }
}
