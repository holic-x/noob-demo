package com.noob.io;

import java.io.File;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileIteratorDemo {
    /**
     *	练习：
     *  给定一个目录，迭代当前目录下所有的内容，
     *  如果是文件夹则进入到文件夹继续迭代（体现分级性，列出文件列表）
     * 迭代指定的文件夹：如果文件夹内有文件夹则继续进入迭代，直到是文件为止
     */
    public static void main(String[] args) {
        File file = new File("E:/dev");
        show(file,1);
    }
    public static void show(File file,int deepth)
    {
        //1.获取当前目录下的每个“文件”，判断其时普通文件还是文件夹
        File[] list = file.listFiles();
        for(File f : list)
        {
            if(f.isFile())
            {
                print(deepth,f);
            }
            else if(f.isDirectory())
            {
                /**
                 * 根据不同的级数输出相应的信息
                 * 首先要先打印当前的目录的名称,随后通过show迭代进入到其中进行遍历,终将所有的结果输出
                 * 如果判断出是文件夹，则用一个参数记录当前的级数，采用局部变量避免影响到当前目录下的其它文件的记录
                 */
                int current = deepth;
                print(current,f);
                show(f,++current);
            }
        }
    }
    public static void print(int count,File f)
    {
        for(int i=1;i<=count;i++){
            System.out.print("  ");
        }
        System.out.println("|--"+f.getName());
    }
}
