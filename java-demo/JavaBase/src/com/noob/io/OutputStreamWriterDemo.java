package com.noob.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;

/**
 * @description:
 * @author：holic-x
 * @date: 2022/4/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OutputStreamWriterDemo {
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
         * 通过OutputStreamWriter实现将字符流转化为字节流
         * 输出（System.out）
         */
        BufferedReader br = new BufferedReader(new FileReader("e:/dev/test.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedWriter bw = new BufferedWriter(osw);
        String line = null;
        while((line=br.readLine())!=null)
        {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
