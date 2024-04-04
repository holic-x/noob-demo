package com.noob.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:
 * @author：holic-x
 * @date: 2017/12/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class FileReaderDemo {
    /**
     * FileReader:字符输入流
     */
    public static void main(String[] args) {
        // 方式1:单个字符读取
        // test("1");
        // 方式2:指定字符读取
        test("2");
    }
    public static void test(String oper)
    {
        //1.加载指定路径下的文件
        File file = new File("e:/dev/test.txt");
        //2.创建FileReader对象，初始化为null
        FileReader fr = null;
        //3.为fr分配空间，通过fr读取指定文件中的数据信息
        try {
            fr = new FileReader(file);
            // 读取文本数据
            switch (oper){
                // a.读取单个字符(每读取一个字符，指针自行往下移动)
                case "1":{
                    char ch1 = (char) fr.read();
                    char ch2 = (char) fr.read();
                    char ch3 = (char) fr.read();
                    System.out.println("ch1:"+ch1+"--ch2:"+ch2+"--ch3:"+ch3);
                    //依次读取数据
                    int ch = 0;
                    while((ch=fr.read())!=-1)
                    {
                        System.out.print((char)ch);
                    }
                    break;
                }
                // b.按照指定的字符数读取数据
                case "2":{
                    /**
                     * 操作步骤：
                     * 1.创建一个char类型的字符数组buffer
                     * 2.定义int类型的临时变量hasRead,初始化为0
                     * 3.将读取到的内容拼接为字符串，进行相应的处理
                     */
                    char[] buffer = new char[3];
                    int hasRead = 0;
                    while((hasRead=fr.read(buffer))!=-1)
                    {
                        String str = new String(buffer,0,hasRead);
                        System.out.print(str);
                    }
                    /**
                     * 无论buffer定义的大小为多少，FileReader对象每次读取都是按字符进行读取，而不会产生中文乱码问题
                     */
                    break;
                }
                default:{
                    System.out.println("参数指定错误");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                //4.释放打开的资源
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
