package com.noob.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class BufferWriterDemo {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void test() throws Exception
    {
        File file = new File("e:/dev/test.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        for(int i=0;i<10;i++)
        {
            bw.write("加油努力、积极向上、ok的");
            bw.newLine();//换行
            bw.flush();//立即写入
        }
    }
}
