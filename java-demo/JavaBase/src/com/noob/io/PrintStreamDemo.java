package com.noob.io;

import java.io.PrintStream;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws Exception {
        // PrintStream ps = new PrintStream("e:/dev/test.txt");
        //指定输出数据到控制台
        PrintStream ps = new PrintStream(System.out);
        /**
         * 1.wirter方法：PrintStream的write方法是将一个
         *   int类型的数据的最低字节输出（最低八位）
         */
        ps.write(97);   //a
        ps.write(353);  //a
        /**
         * 2.println方法：PrintStream的println方法是保持
         * 	 数值的形式不变直接输出
         */
        ps.println(97);  //97
        ps.println(353); //353
        //关闭打印流
        ps.close();
    }
}
