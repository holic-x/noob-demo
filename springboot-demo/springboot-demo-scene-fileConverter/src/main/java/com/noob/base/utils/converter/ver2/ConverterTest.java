package com.noob.base.utils.converter.ver2;

import java.io.IOException;

/**
 * 文件 <=> 二进制流转化 测试
 */
public class ConverterTest {

    // 初始化工具类
    static FileAndBinaryConverterV2 util = new FileAndBinaryConverterV2();

    public static void main(String[] args) {
        // 定义源文件&目标生成二进制文件路径
        String inputFilePath = "D:\\Desktop\\test-other\\fileConverter\\source\\test.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\fileConverter\\target\\V2\\test.bin";
        test_convertFileToBinary(inputFilePath, outputFilePath);

        // 定义源二进制文件路径&复原文件目录路径
        String inputBinFilePath = outputFilePath; // "D:\\Desktop\\test-other\\hello.bin"
        String outputDir = "D:\\Desktop\\test-other\\fileConverter\\output\\V2";
        test_convertBinaryToFile(inputBinFilePath, outputDir);
    }

    public static void test_convertFileToBinary(String inputFilePath, String outputFilePath) {
        try {
            util.convertFileToBinary(inputFilePath, outputFilePath);
            System.out.println("转换成功，生成的文件包含完整性校验信息");
        } catch (IOException e) {
            System.err.println("转换错误: " + e.getMessage());
        }
    }

    public static void test_convertBinaryToFile(String inputFilePath, String outputDir) {
        try {
            util.convertBinaryToFile(inputFilePath, outputDir);
            System.out.println("还原成功");
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            System.err.println("可能原因: 目标路径不存在 或 文件被文本编辑器修改或传输过程中损坏");
        }
    }

}
