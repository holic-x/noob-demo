package com.noob.base.utils.converter.active.ver3;


import java.io.File;
import java.io.IOException;

/**
 * 文件 <=> 二进制流转化 测试
 */
public class ConverterTest {

    // 初始化工具类
    static FileAndBinaryConverterV3 util = new FileAndBinaryConverterV3();

    public static void main(String[] args) {
        // 定义源文件&目标生成二进制文件路径
        String inputFilePath = "D:\\Desktop\\test-other\\fileConverter\\source\\test.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\fileConverter\\target\\V3\\test.bin";
        test_convertFileToBinary(inputFilePath, outputFilePath);

        System.out.println("=========================================================================================================");

        // 定义源二进制文件路径&复原文件目录路径
        String inputBinFilePath = outputFilePath; // "D:\\Desktop\\test-other\\hello.bin"
        String outputDir = "D:\\Desktop\\test-other\\fileConverter\\output\\V3";
        test_convertBinaryToFile(inputBinFilePath, outputDir);
    }

    public static void test_convertFileToBinary(String inputFilePath, String outputFilePath) {
        try {
            long startTime = System.currentTimeMillis();
            util.convertFileToBinary(inputFilePath, outputFilePath);
            long duration = System.currentTimeMillis() - startTime;

            File input = new File(inputFilePath);
            File output = new File(outputFilePath);

            System.out.println("转换成功");
            System.out.println("输入文件: " + input.getAbsolutePath() + " (" + input.length() + " 字节)");
            System.out.println("输出文件: " + output.getAbsolutePath() + " (" + output.length() + " 字节)");
            System.out.println("耗时: " + duration + " 毫秒");
        } catch (IOException e) {
            System.err.println("转换失败: " + e.getMessage());
            if (e.getMessage().contains("CRC")) {
                System.err.println("提示: 源文件可能在转换过程中被修改");
            }
        }
    }

    public static void test_convertBinaryToFile(String inputFilePath, String outputDir) {
        try {
            long startTime = System.currentTimeMillis();
            String restoredFile = util.convertBinaryToFile(inputFilePath, outputDir);
            long duration = System.currentTimeMillis() - startTime;

            File input = new File(inputFilePath);
            File output = new File(restoredFile);

            System.out.println("还原成功");
            System.out.println("输入文件: " + input.getAbsolutePath() + " (" + input.length() + " 字节)");
            System.out.println("输出文件: " + output.getAbsolutePath() + " (" + output.length() + " 字节)");
            System.out.println("耗时: " + duration + " 毫秒");
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            util.printTroubleshootingTips(e);
        }
    }

}
