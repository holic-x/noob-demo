package com.noob.base.io;


import java.io.*;

/**
 * 合并流
 */
public class SequenceStreamDemo {

    // 定义合并对象文件路径
    private final static  String FILEPATH1 = "testRoot/io/seq1.log";
    private final static  String FILEPATH2 = "testRoot/io/seq2.log";

    private final static  String OUTPUT_FILEPATH = "testRoot/io/merge.log";

    // 合并多个文件内容并输出
    public static void merge() throws Exception {
        // 1.定义文件输入流读取要合并的文件信息
        InputStream is1 = new FileInputStream(FILEPATH1);
        InputStream is2 = new FileInputStream(FILEPATH2);
        // 2.定义合并流(将多个文件合并)
        SequenceInputStream sis = new SequenceInputStream(is1, is2);
        // 3.通过字节流将内容写入到一个新的文件(文件字节流的写入处理)
        OutputStream os = new FileOutputStream(OUTPUT_FILEPATH);
        int temp = 0; // 接收内容
        while((temp = sis.read()) != -1) { // 循环输出
            os.write(temp); // 保存内容
        }

        // 4.依次关闭输出流
        sis.close(); // 关闭合并流
        is1.close(); // 关闭输入流1
        is2.close(); // 关闭输入流2
        os.close(); // 关闭输出流
    }

    public static void main(String[] args) throws Exception {
        // 测试合并流
        merge();
    }
}
