package com.noob.io;

import java.io.File;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileNameFilterDemo {
    /**
     * 通过文件过滤器实现得到指定目录下的制定类型的文件
     * 文件过滤器：
     * 通过Lambda表达式进行相关操作,在列出指定的文件时,根据提供的Lambda表达式进行相应的条件过滤
     */
    public static void main(String[] args) {
        /**
         * 假定在e盘下有许多文件，得到指定目录test下的所有文件
         * 过滤出后缀名为‘.java’的文件
         */
        File file = new File("e:/dev");
        System.out.println(file.getPath()+"下的所有文件展示如下：");
        String[] all = file.list();
        for(String s : all)
        {
            System.out.println(s);
        }
        System.out.println("--------------------------------");
        System.out.println("经过过滤之后,所有的'.java'文件显示如下：");
        String[] filter = file.list((dir,name)->name.endsWith(".java"));
        for(String s : filter)
        {
            System.out.println(s);
        }
    }
}
