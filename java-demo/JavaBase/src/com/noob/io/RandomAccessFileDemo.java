package com.noob.io;

import java.io.RandomAccessFile;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class RandomAccessFileDemo {
    /**
     * RandomAccessFile的特点：
     * 1.既可以读取数据,也可以写入数据
     * 2.只是针对文件进行操作
     * 3.内部维护一个大型的Byte数组,将字节流输入流和字节输出流进行封装
     * 4.通过索引的方式对数组中的元素进行操作(获取和设置索引的方法 getFilePointer、seek)
     * 5.随机访问的原理就是通过操作索引的方法对指针进行自定义的指定位置进行读写
     */
    public static void main(String[] args) throws Exception {
        //1.随机写入数据
        write();
        //2.随机读取指定位置的数据
        read();
    }
    public static void write() throws Exception
    {
        /**
         * 1.随机创建读写文件的对象,如果文件不存在则会自行创建,如果文件存在则不会创建新的文件
         *   "r":以只读方式打开文件
         *   "rw":以读写方式打开文件
         *   "rws":以读写方式打开文件
         *   "rwd":以读写方式打开文件
         */
        RandomAccessFile raf = new RandomAccessFile("e:/dev/raf.txt","rw");
        //2.将数据写入到相应的文件中,并相应地获取当前指针的位置
        raf.write("张小三".getBytes());
        raf.writeInt(18);
        System.out.println("当前的指针位置为："+raf.getFilePointer());
        raf.write("李小四".getBytes());
        raf.writeInt(28);
        System.out.println("当前的指针位置为："+raf.getFilePointer());
        raf.write("王小五".getBytes());
        raf.writeInt(38);
        System.out.println("当前的指针位置为："+raf.getFilePointer());
        //3.数据保存完毕,关闭流
        raf.close();
    }
    public static void read() throws Exception
    {
        /**
         * 1.创建随机读写文件的对象
         */
        RandomAccessFile raf = new RandomAccessFile("e:/dev/raf.txt","r");
        /**
         * 2.跳过指定的字节数不读,读取指定位置的数据
         *   一个中文字符占3个字节
         *   一个int类型的整型数据占4个字节
         */
        // 跳过指定字节不读，等价于raf.seek(10*2);
        raf.skipBytes(13*2);
        //读取姓名
        byte[] buffer = new byte[9];
        raf.read(buffer);
        String name = new String(buffer);
        //读取年龄
        int age = raf.readInt();
        System.out.println("姓名："+name);
        System.out.println("年龄："+age);
        //3.关闭流
        raf.close();
        /**
         * 结果：（跳过了指定的内容）
         * 姓名：王小五
         * 年龄：38
         */
    }
}
