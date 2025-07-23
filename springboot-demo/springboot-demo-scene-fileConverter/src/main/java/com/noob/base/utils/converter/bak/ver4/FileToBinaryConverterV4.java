package com.noob.base.utils.converter.bak.ver4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.CRC32;

/**
 * 文件转二进制转换器 V4（文本编辑器安全版）
 * 功能：将任意文件转换为Base64编码的文本格式，确保可通过文本编辑器安全处理
 */
public class FileToBinaryConverterV4 {
    // 文本标识头（非二进制魔术数字）
    private static final String TEXT_HEADER = "-----BEGIN BINARY DATA V4-----";
    private static final String TEXT_FOOTER = "-----END BINARY DATA V4-----";
    
    public static void main(String[] args) {
        String inputFilePath = "D:\\Desktop\\test-other\\source\\hello.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\hello.bin.txt";

        try {
            long startTime = System.currentTimeMillis();
            convertToTextSafeFormat(inputFilePath, outputFilePath);
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

    /**
     * 转换为文本编辑器安全的格式
     */
    public static void convertToTextSafeFormat(String inputPath, String outputPath) throws IOException {
        // 读取原始文件并计算校验值
        byte[] fileData = Files.readAllBytes(Paths.get(inputPath));
        CRC32 crc = new CRC32();
        crc.update(fileData);
        long checksum = crc.getValue();
        
        // 转换为Base64
        String base64Data = Base64.getEncoder().encodeToString(fileData);
        String fileName = new File(inputPath).getName();
        
        // 创建输出目录（如果需要）
        new File(outputPath).getParentFile().mkdirs();
        
        // 写入文本格式文件
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            writer.println(TEXT_HEADER);
            writer.println("Filename: " + fileName);
            writer.println("Checksum: " + checksum);
            writer.println("Length: " + fileData.length);
            writer.println(); // 空行分隔头和数据
            writer.println(base64Data);
            writer.println(TEXT_FOOTER);
        }
    }
}