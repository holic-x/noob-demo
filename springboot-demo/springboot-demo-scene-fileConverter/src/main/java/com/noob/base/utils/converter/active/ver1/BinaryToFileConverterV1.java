package com.noob.base.utils.converter.active.ver1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 二进制流转文件工具：将二进制流转化为任意文件
 */
public class BinaryToFileConverterV1 {

    /**
     * 将二进制流文件还原为原始文件
     * @param inputFilePath 二进制文件路径
     * @param outputDirectory 输出目录路径
     * @return 还原后的文件路径
     * @throws IOException 如果发生I/O错误
     */
    public static String convertBinaryToFile(String inputFilePath, String outputDirectory) throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(inputFilePath)))) {

            // 读取原始文件名
            int fileNameLength = dis.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dis.readFully(fileNameBytes);
            String originalFileName = new String(fileNameBytes);

            // 读取文件内容
            int fileContentLength = dis.readInt();
            byte[] fileContent = new byte[fileContentLength];
            dis.readFully(fileContent);

            // 确保输出目录存在
            Files.createDirectories(Paths.get(outputDirectory));

            // 写入还原的文件
            Path outputPath = Paths.get(outputDirectory, originalFileName);
            Files.write(outputPath, fileContent);

            return outputPath.toString();
        }
    }

    public static void main(String[] args) {
        /*
        if (args.length != 2) {
            System.out.println("二进制流转文件工具");
            System.out.println("用法: java BinaryToFileConverterV1 <输入二进制文件路径> <输出目录路径>");
            System.out.println("示例: java BinaryToFileConverterV1 D:\\output.bin C:\\restored_files");
            return;
        }

        String inputFilePath = args[0];
        String outputDirectory = args[1];
         */

        String inputFilePath = "D:\\Desktop\\test-other\\fileConverter\\target\\V1\\test.bin";
        String outputDirectory = "D:\\Desktop\\test-other\\fileConverter\\output\\V1";

        try {
            long startTime = System.currentTimeMillis();
            String restoredFilePath = convertBinaryToFile(inputFilePath, outputDirectory);
            long endTime = System.currentTimeMillis();

            File inputFile = new File(inputFilePath);
            File restoredFile = new File(restoredFilePath);

            System.out.println("转换成功!");
            System.out.println("二进制文件: " + inputFile.getAbsolutePath() + " (" + inputFile.length() + " 字节)");
            System.out.println("还原文件: " + restoredFile.getAbsolutePath() + " (" + restoredFile.length() + " 字节)");
            System.out.println("耗时: " + (endTime - startTime) + " 毫秒");
        } catch (IOException e) {
            System.err.println("转换失败: " + e.getMessage());
        }
    }
}