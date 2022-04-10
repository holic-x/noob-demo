package com.noob.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class InputStreamReaderDemo {
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
         * System.in返回的是一个字节流对象，通过
         * InputStreamReader将字节流转化为字符流
         */
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(new FileWriter("e:/dev/test.txt"));
        String line = null;
        while((line=br.readLine())!=null)
        {
            if("exit".equals(line)){
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
