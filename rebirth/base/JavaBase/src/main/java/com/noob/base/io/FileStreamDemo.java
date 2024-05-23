package com.noob.base.io;

import java.io.*;

/**
 * 字节流：文件字节流
 */
public class FileStreamDemo {

    // 定义文件存储路径（用于校验文件内容输出输出）
    private final static String FILEPATH = "testRoot/io/fileStream.log";

    // 写入内容到文件
    public static void writeFile(String filePath, String content) throws Exception {
        // 1.使用File绑定一个文件
        File file = new File(filePath);

        // 2.将File对象绑定到流对象(实例化默认为覆盖原文件内容方式，如果添加true参数则变为对源文件追加内容的方式)
        OutputStream outputStream = new FileOutputStream(file);// 追加方式：new FileOutputStream(file,true);

        // 3.进行读/写操作
        byte[] bytes = content.getBytes();
        outputStream.write(bytes);

        // 4.关闭资源（关闭流）
        outputStream.close();
    }

    // 从文件中读取内容
    public static String readFile(String filePath) throws Exception {
        // 1.使用File绑定一个文件
        File file = new File(filePath);

        // 2.将File对象绑定到流对象(子类对象实例化父类对象)
        InputStream inputStream = new FileInputStream(file);

        // 3.进行读/写操作
        byte[] bytes = new byte[(int) file.length()];
        int length = inputStream.read(bytes);
        System.out.println("读入的数据长度：" + length);

        // 4.关闭资源（关闭流）
        inputStream.close();

        // 返回读取的数据内容
        return new String(bytes);
    }

    public static void main(String[] args) throws Exception {
        // 写入文件内容
        writeFile(FILEPATH,"hello fileStream");

        // 读取文件内容
        String content = readFile(FILEPATH);
        System.out.println(content);
    }

}
