package com.noob.base.utils.converter.active.ver1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件转二进制流工具：将任意文件转化为二进制流
 */
public class FileToBinaryConverterV1 {

    /**
     * 将文件转换为二进制流文件
     * @param inputFilePath 原始文件路径
     * @param outputFilePath 输出的二进制文件路径
     * @throws IOException 如果发生I/O错误
     */
    public static void convertFileToBinary(String inputFilePath, String outputFilePath) throws IOException {
        // 读取原始文件
        byte[] fileContent = Files.readAllBytes(Paths.get(inputFilePath));

        // 获取原始文件名和扩展名
        File inputFile = new File(inputFilePath);
        String originalFileName = inputFile.getName();

        // 创建输出流
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFilePath)))) {

            // 写入原始文件名长度和文件名
            dos.writeInt(originalFileName.length());
            dos.write(originalFileName.getBytes());

            // 写入文件内容长度和内容
            dos.writeInt(fileContent.length);
            dos.write(fileContent);
        }
    }

    public static void main(String[] args) {
        /*
        if (args.length != 2) {
            System.out.println("文件转二进制流工具");
            System.out.println("用法: java FileToBinaryConverterV1 <输入文件路径> <输出二进制文件路径>");
            System.out.println("示例: java FileToBinaryConverterV1 C:\\test.jpg D:\\output.bin");
            return;
        }
         */

        // String inputFilePath = args[0];
        // String outputFilePath = args[1];

        // 转换失败则进一步确认source、target目标路径是否存在
        String inputFilePath = "D:\\Desktop\\test-other\\fileConverter\\source\\test.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\fileConverter\\target\\V1\\test.bin";

        try {
            long startTime = System.currentTimeMillis();
            convertFileToBinary(inputFilePath, outputFilePath);
            long endTime = System.currentTimeMillis();

            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);

            System.out.println("转换成功!");
            System.out.println("原始文件: " + inputFile.getAbsolutePath() + " (" + inputFile.length() + " 字节)");
            System.out.println("输出文件: " + outputFile.getAbsolutePath() + " (" + outputFile.length() + " 字节)");
            System.out.println("耗时: " + (endTime - startTime) + " 毫秒");
        } catch (IOException e) {
            System.err.println("转换失败: " + e.getMessage());
        }
    }
}