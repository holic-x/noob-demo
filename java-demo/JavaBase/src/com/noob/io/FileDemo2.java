package com.noob.io;

import java.io.File;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileDemo2 {
    public static void main(String[] args) throws Exception {
        test();
    }
    public static void test() throws Exception
    {
        /**
         * 创建文件、目录、遍历相关信息
         */
        //1.创建一个文件
        File file = new File("e:/ft.txt");
        file.createNewFile();
        /**
         * file.createNewFile();创建一个文件
         * 如果当前路径下并没有指定的目录，则无法直接创建文件,如果直接调用上述语句，会报出错误java.io.IOException: 系统找不到指定的路径
         * 因此，一定要注意指定文件的路径是否正确，如果没有指定的目录,则需要为其创建相应的一级或者是多级目录
         * 但若当前路径下存在指定的目录，则通过createNewFile方法
         * 直接在当前指定路径下创建指定的文件
         */
        //2.创建一个目录（单级目录）
        File catalog = new File("e:\\test");
        catalog.mkdir();
        /**
         * 通过mldir方法在指定的路径下创建了一个名为haha的单级目录
         */
        //3.创建多级目录
        File mulcatalog = new File("e:/dev/abc/ab/a");
        mulcatalog.mkdirs();
        //4.删除指定的文件或者目录
        System.out.println(file.delete());
        System.out.println(catalog.delete());
        System.out.println(mulcatalog.delete());
        /**
         * 删除指定文件输出结果：
         * true、true、true
         * 可以看到文件ft.txt被删除、单级目录test被删除、
         * 多级目录下的最里层的目录a被删除（多级目录的删除是删除指定的那一级目录）
         */
        //5.得到指定目录下的所有文件或者是目录（此处遍历某个路径下的文件、目录）
        File files = new File("e:\\dev");
        String[] list = files.list();
        for(String s : list)
        {
            System.out.println(s);
        }
        /**
         * 结果分析:
         * 上述遍历结果只是涉及到当前指定路径的所有文件、所有的目录的遍历,而不会进入到目录中深入遍历
         */
        //6.得到所有的磁盘信息
        File[] root = File.listRoots();
        for(File f : root)
        {
            System.out.println(f);
        }
    }
}
