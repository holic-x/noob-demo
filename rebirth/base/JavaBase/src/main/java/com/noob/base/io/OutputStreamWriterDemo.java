package com.noob.base.io;


import java.io.*;

/**
 * 字节流转字符流（输出）
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        File f = new File("testRoot/io/temp.log");
        Writer out = new OutputStreamWriter(new FileOutputStream(f));
        out.write("hello world!!");
        out.close();
    }
}
