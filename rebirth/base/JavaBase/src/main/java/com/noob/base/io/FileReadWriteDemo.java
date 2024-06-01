package com.noob.base.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件字符流操作（读写）
 */
public class FileReadWriteDemo {

    // 定义文件
    private final static String FILEPATH = "testRoot/io/fileReadWriteDemo.log";

    // 写文件
    public static void write(String filePath,String content) throws IOException {
        // 1.绑定文件
        File file = new File(filePath);

        // 2.将文件绑定到字符流
        FileWriter fw = new FileWriter(file);

        // 3.写入操作
        fw.write(content);

        // 4.关闭流
        fw.flush(); // 字符流操作时使用了缓冲区，并在关闭字符流时会强制将缓冲区内容输出,如果不关闭流，则缓冲区的内容是无法输出的; 如果想在不关闭流时，将缓冲区内容输出，可以使用 flush 强制清空缓冲区
        fw.close();
    }

    // 读文件
    public static String read(String filePath) throws IOException {
        // 1.绑定文件
        File file = new File(filePath);

        // 2.将文件绑定到字符流
        FileReader fr = new FileReader(file);

        // 3.读取操作
        int temp = 0; // 接收每一个内容
        int len = 0; // 读取内容
        char[] buf = new char[1024];
        while ((temp = fr.read()) != -1) {
            // 如果不是-1说明还有内容可以继续读取
            buf[len] = (char) temp;
            len++;
        }
        System.out.println("文件字符数为：" + len);
        // 4.关闭流
        fr.close();
        // 返回读取内容
        return new String(buf,0,len);
    }

    public static void main(String[] args) throws IOException {
        // 测试字符流读写操作
        write(FILEPATH,"hello fileWrite \r \n");
        String content = read(FILEPATH);
        System.out.println("读取到的内容：" + content);
    }
}
