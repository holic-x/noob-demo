package com.noob.base.io;

import java.io.*;

/**
 * 字节流转字符流（输入）
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        File f = new File("testRoot/io/temp.log");
        Reader reader = new InputStreamReader(new FileInputStream(f));
        char[] c = new char[1024];
        int len = reader.read(c);
        reader.close();
        System.out.println(new String(c, 0, len));
    }
}
