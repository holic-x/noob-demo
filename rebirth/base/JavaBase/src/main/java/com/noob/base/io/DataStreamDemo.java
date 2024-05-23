package com.noob.base.io;

import java.io.*;

/**
 * 数据操作流（格式化读入和输出数据）
 */
public class DataStreamDemo {

    // 定义操作文件
    private final static String FILEPATH = "testRoot/io/dataStream.log";

    // 定义写方法
    private static void write(String filePath) throws IOException {
        // 1.绑定一个文件
        File file = new File(filePath);

        // 2.将文件绑定到流对象
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));

        // 3.写入操作
        String[] names = {"衬衣", "手套", "围巾"};
        float[] prices = {98.3f, 30.3f, 50.5f};
        int[] nums = {3, 2, 1};
        for (int i = 0; i < names.length; i++) {
            dos.writeChars(names[i]);
            dos.writeChar('\t');
            dos.writeFloat(prices[i]);
            dos.writeChar('\t');
            dos.writeInt(nums[i]);
            dos.writeChar('\n');
        }

        // 4.写入完成，关闭流
        dos.close();
    }


    // 定义读方法
    public static void read(String filePath) throws IOException {
        // 1.绑定文件
        File file = new File(filePath);

        // 2.将文件绑定到流对象
        DataInputStream dis = new DataInputStream(new FileInputStream(file));

        // 3.从文件中读取内容
        String name = null; // 接收名称
        float price = 0.0f; // 接收价格
        int num = 0; // 接收数量
        char[] temp = null; // 接收商品名称
        int len = 0; // 保存读取数据的个数
        char c = 0; // '\u0000'
        try{
            while (true) {
                temp = new char[200]; // 开辟空间
                len = 0;
                while ((c = dis.readChar()) != '\t') { // 接收内容
                    temp[len] = c;
                    len++; // 读取长度加1
                }
                name = new String(temp, 0, len); // 将字符数组变为String
                price = dis.readFloat(); // 读取价格
                dis.readChar(); // 读取\t
                num = dis.readInt(); // 读取int
                dis.readChar(); // 读取\n
                System.out.printf("名称：%s；价格：%5.2f；数量：%d\n", name, price, num);
            }
        }catch (EOFException e){
            System.out.println("结束");
        }
        // 4.关闭流
        dis.close();
    }

    public static void main(String[] args) throws IOException {
        // 测试数据流
        write(FILEPATH);
        read(FILEPATH);
    }
}
