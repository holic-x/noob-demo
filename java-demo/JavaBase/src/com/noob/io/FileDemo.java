package com.noob.io;

import java.io.File;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileDemo {
    public static void main(String[] args) {
        /**
         * File类代表一个文件或者是一个目录，可以用指定的路径和文件名称创建一个File对象
         * 表示加载对应路径下的文件（如果文件不存在则加载失败）
         * 需要注意的是此处并不是创建文件，而是指向指定路径的某个文件，
         * 如果文件不存在，则不能对其进行部分读写操作，需要通过createNewFile
         * 方法在当前目录下创建该文件（但必须确保当前目录是存在的、有效的）
         */
        File file = new File("e:/dev/test.txt");
        show(file);
    }
    /**
     * 测试File类常用的方法
     */
    public static void show(File file)
    {
        //1.获取文件的名字
        System.out.println(file.getName());
        //2.得到当前文件的父目录
        System.out.println(file.getParent());
        //3.获取当前文件的上一级目录
        System.out.println(file.getAbsoluteFile().getParent());
        /**
         * 获取文件的相关属性
         */
        //4.文件是否存在
        System.out.println(file.exists());
        //5.文件是否可执行
        System.out.println(file.canExecute());
        //6.文件是否可读
        System.out.println(file.canRead());
        //7.文件是否可写
        System.out.println(file.canWrite());
        //8.得到文件的长度
        System.out.println(file.length());
        //9.得到指定内容的路径信息
        System.out.println(file.getPath());
        //10.判断指定内容是一个文件还是一个文件夹
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        /**
         * 结果分析：
         * 假设当前目录下并没有该文件
         * test.txt
         * e:\dev
         * e:\dev
         * 可通过对文件的相关属相进行设置从而得到不同的结果
         * false:文件不存在
         * false:文件不可执行
         * false:文件不可读
         * false:文件不可写
         * 0:文件长度为0
         * e:\filetest\test.txt
         * false
         * false
         * -----------------------------
         * 如果当前目录下存在该文件，则输出以下结果：
         * test.txt
         * e:\dev
         * e:\dev
         * true:文件存在
         * true:文件可执行
         * true:文件可读
         * true:文件可写
         * 0
         * e:\dev\test.txt
         * true:是一个文件
         * false:不是一个文件夹
         */
    }
}
