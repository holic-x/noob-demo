package com.noob.io;

import java.io.*;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class PrintWriteDemo {
    public static void main(String[] args) throws Exception {
        // 通过InputStreamReader将标准字节流（System.in）转化为字符流,再用缓冲流进行封装
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 定义输出的缓冲流，将缓冲流再次封装为打印流
        BufferedWriter bw = new BufferedWriter(new FileWriter("e:/dev/gogo.txt"));
        PrintWriter pw = new PrintWriter(bw);
        String line = null;
        while((line=br.readLine())!=null)
        {
            // 如果输入exit标识退出
            if(line.equals("exit")){
                break;
            }
            pw.write(line);// 写入数据
            pw.println();  // 换行
            pw.flush();    // 立即写入
        }
        pw.close();
        br.close();
    }
}
