package com.noob.io;

import java.io.*;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class BufferReaderDemo {
    /**
     * 缓冲流分为字节缓冲流和字符缓冲流，用法基本类似
     * 字节缓冲流：BufferedIuputStream、BufferedOutputStream
     * 字符缓冲流：BufferedReader、BufferedWriter
     */
    public static void main(String[] args) {
        test();
    }
    public static void test()
    {
        File file = new File("e:/dev/test.txt");
        //1.创建输入流和缓冲流的对象，并初始化为null
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            //2.每次直接读取一行数据
            String line = null;
            try {
                while((line=br.readLine())!=null)
                {
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                /**
                 * 此处只需要关闭缓冲流即可，无需重复关闭fr
                 * 但如果fr、br均要关闭则需要注意关闭的先后顺序:先关闭fr、再关闭br，否则提示出错
                 */
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
