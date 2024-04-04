package com.noob.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @description: 切割合并流：将一个文件切割至多份进行操作，随后再将文件进行合并
 * @author：holic-x
 * @date: 2022/4/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws Exception {
        File srcFile = new File("e:/dev/PhpStorm-2021.2.3.exe");
        // 切割文件
        splitFile(srcFile,"e:/dev/splitFile/",".part");
        // 合并文件
        mergeFile("e:/dev/splitFile/",".part","e:/dev/mergeFile/");
    }

    /**
     * 切割文件
     * @param srcFile 文件源
     * @param targetFilePath 目标文件夹路径
     * @param suffix 切割文件后缀（切割文件按一定规律存储，例如数字递增）
     * @throws Exception
     */
    public static void splitFile(File srcFile,String targetFilePath,String suffix) throws Exception
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //定义临时变量用以记录分割后文件的个数
        int count = 1;
        fis = new FileInputStream(srcFile);
        byte[] buffer = new byte[1024*1024];
        int hasRead = 0;
        while((hasRead=fis.read(buffer))!=-1)
        {
            // 创建一个文件，指定文件的位置(例如e:/dev/xxx.part)
            fos = new FileOutputStream(new File(targetFilePath,(count++)+suffix));
            fos.write(buffer,0,hasRead);
            fos.flush();
            fos.close();
        }
        // 在统计目录中定义一个文件用以记录分割后的文件信息（切分后的文件个数、源文件的名称）
        Properties prop = new Properties();
        prop.setProperty("partCount", (count-1)+"");
        prop.setProperty("fileName", srcFile.getName());
        //将相关的属性保存至指定的文件
        fos = new FileOutputStream(new File(targetFilePath,"info.properties"));
        prop.store(fos, "save partFile info");
        //释放打开的资源
        fos.close();
        fis.close();
    }

    /**
     * @param sourceFilePath 要合并的文件目录源
     * @param suffix 合并的源文件后缀
     * @param outFilePath 合并输出的文件路径
     * @throws IOException
     */
    public static void mergeFile(String sourceFilePath,String suffix,String outFilePath) throws IOException {
        /**
         *  SequenceInputStream 特点
         *    a)将多个源合并为一个流
         *    b)接受的是一个枚举接口对象
         */
        // 1.创建ArrayList列表，将所有需要合并的文件输入流加载进列表中
        ArrayList<FileInputStream> al =new ArrayList<>();
        // 读取配置文件获取切割文件信息(或者通过遍历文件跟踪切割文件信息)
        Properties prop = new Properties();
        FileReader fr = new FileReader(sourceFilePath+File.separator+"info.properties");
        prop.load(fr);
        int partCount = Integer.valueOf(prop.getProperty("partCount"));
        String fileName = prop.getProperty("fileName");
        fr.close();
        // 遍历切割的子文件列表
        for(int i=1;i<=partCount;i++) {
            al.add(new FileInputStream(sourceFilePath+i+suffix));
        }
        // 2.SequenceInputStream需要一个枚举类型
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        SequenceInputStream sis =new SequenceInputStream(en);
        // 3.创建目的地
        FileOutputStream fos =new FileOutputStream(outFilePath+fileName);
        byte [] buffer =new byte[1024];
        int hasRead=0;
        while((hasRead=sis.read(buffer))!=-1) {
            fos.write(buffer,0,hasRead);
        }
        fos.close();
        sis.close();
    }
}
