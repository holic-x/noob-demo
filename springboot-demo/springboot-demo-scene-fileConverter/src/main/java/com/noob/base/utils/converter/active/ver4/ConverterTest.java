package com.noob.base.utils.converter.active.ver4;


import java.io.File;
import java.io.IOException;

/**
 * 文件 <=> 二进制流转化 测试(Base64)
 */
public class ConverterTest {

    // 初始化工具类
    static FileAndBinaryConverterV4 util = new FileAndBinaryConverterV4();

    public static void main(String[] args) {
        // 定义源文件&目标生成二进制文件路径
        String inputFilePath = "D:\\Desktop\\test-other\\fileConverter\\source\\test.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\fileConverter\\target\\V4\\test.bin";
        test_convertToTextSafeFormat(inputFilePath, outputFilePath);

        System.out.println("=========================================================================================================");

        // 定义源二进制文件路径&复原文件目录路径
        String inputBinFilePath = outputFilePath; // "D:\\Desktop\\test-other\\hello.bin"
        String outputDir = "D:\\Desktop\\test-other\\fileConverter\\output\\V4";
        test_restoreFromTextSafeFormat(inputBinFilePath, outputDir);
    }

    public static void test_convertToTextSafeFormat(String inputFilePath, String outputFilePath) {
        try {
            long startTime = System.currentTimeMillis();
            util.convertToTextSafeFormat(inputFilePath, outputFilePath);
            long duration = System.currentTimeMillis() - startTime;

            File input = new File(inputFilePath);
            File output = new File(outputFilePath);

            System.out.println("转换成功（文本安全格式）");
            System.out.println("输入文件: " + input.getAbsolutePath() + " (" + input.length() + " 字节)");
            System.out.println("输出文件: " + output.getAbsolutePath() + " (" + output.length() + " 字符)");
            System.out.println("Base64膨胀率: " + String.format("%.2f", output.length()/(double)input.length()) + "倍");
            System.out.println("耗时: " + duration + " 毫秒");

            System.out.println("\n提示：输出文件可直接用文本编辑器处理，不会损坏数据");

        } catch (IOException e) {
            System.err.println("转换失败: " + e.getMessage());
        }
    }

    public static void test_restoreFromTextSafeFormat(String inputFilePath, String outputDir) {
        try {
            long startTime = System.currentTimeMillis();
            String restoredFile = util.restoreFromTextSafeFormat(inputFilePath, outputDir);
            long duration = System.currentTimeMillis() - startTime;

            File input = new File(inputFilePath);
            File output = new File(restoredFile);

            System.out.println("还原成功（从文本安全格式）");
            System.out.println("输入文件: " + input.getAbsolutePath() + " (" + input.length() + " 字符)");
            System.out.println("输出文件: " + output.getAbsolutePath() + " (" + output.length() + " 字节)");
            System.out.println("耗时: " + duration + " 毫秒");
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            util.printTroubleshootingTips(e);
        }
    }

}
