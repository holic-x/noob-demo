package com.noob.utils.converter.ver4;

import java.io.*;
import java.nio.file.*;
import java.util.Base64;
import java.util.Scanner;
import java.util.zip.CRC32;

/**
 * 二进制转文件转换器 V4（兼容文本编辑器处理版）
 * 功能：可还原经过文本编辑器处理的Base64编码文件
 */
public class BinaryToFileConverterV4 {
    private static final String TEXT_HEADER = "-----BEGIN BINARY DATA V4-----";
    private static final String TEXT_FOOTER = "-----END BINARY DATA V4-----";
    
    public static void main(String[] args) {
        String inputFilePath = "D:\\Desktop\\test-other\\test.bin.txt";
        String outputDir = "D:\\Desktop\\test-other";

        try {
            long startTime = System.currentTimeMillis();
            String restoredFile = restoreFromTextSafeFormat(inputFilePath, outputDir);
            long duration = System.currentTimeMillis() - startTime;

            File input = new File(inputFilePath);
            File output = new File(restoredFile);

            System.out.println("还原成功（从文本安全格式）");
            System.out.println("输入文件: " + input.getAbsolutePath() + " (" + input.length() + " 字符)");
            System.out.println("输出文件: " + output.getAbsolutePath() + " (" + output.length() + " 字节)");
            System.out.println("耗时: " + duration + " 毫秒");
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            printTroubleshootingTips(e);
        }
    }

    /**
     * 从文本安全格式还原文件
     */
    public static String restoreFromTextSafeFormat(String inputPath, String outputDir) throws IOException {
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            throw new IOException("输入文件不存在");
        }
        
        try (Scanner scanner = new Scanner(new FileReader(inputPath))) {
            // 1. 验证文件头
            if (!scanner.hasNextLine() || !scanner.nextLine().equals(TEXT_HEADER)) {
                throw new IOException("无效的文件头（可能被文本编辑器修改）");
            }
            
            // 2. 读取元数据
            String fileName = null;
            long expectedChecksum = -1;
            int expectedLength = -1;
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) break; // 遇到空行停止读取头
                
                if (line.startsWith("Filename: ")) {
                    fileName = line.substring("Filename: ".length());
                } else if (line.startsWith("Checksum: ")) {
                    expectedChecksum = Long.parseLong(line.substring("Checksum: ".length()));
                } else if (line.startsWith("Length: ")) {
                    expectedLength = Integer.parseInt(line.substring("Length: ".length()));
                }
            }
            
            if (fileName == null) {
                throw new IOException("缺少文件名信息");
            }
            
            // 3. 读取Base64数据
            StringBuilder base64Data = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(TEXT_FOOTER)) break;
                base64Data.append(line);
            }
            
            // 4. 解码数据
            byte[] fileData = Base64.getDecoder().decode(base64Data.toString());
            
            // 5. 验证数据完整性
            if (fileData.length != expectedLength) {
                throw new IOException(String.format(
                    "数据长度不匹配（预期: %d, 实际: %d）", 
                    expectedLength, fileData.length));
            }
            
            CRC32 crc = new CRC32();
            crc.update(fileData);
            if (expectedChecksum != -1 && crc.getValue() != expectedChecksum) {
                throw new IOException(String.format(
                    "数据校验失败（预期: %d, 实际: %d）", 
                    expectedChecksum, crc.getValue()));
            }
            
            // 6. 写入输出文件
            Path outputPath = Paths.get(outputDir, fileName);
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, fileData);
            
            return outputPath.toString();
        }
    }

    private static void printTroubleshootingTips(IOException e) {
        String msg = e.getMessage();
        System.err.println("\n问题排查建议:");
        
        if (msg.contains("无效的文件头")) {
            System.err.println("1. 请勿修改文件头标记行: " + TEXT_HEADER);
            System.err.println("2. 确保文件是通过FileToBinaryConverterV4生成的");
        } 
        else if (msg.contains("数据长度不匹配")) {
            System.err.println("1. 可能删除了Base64数据中的某些字符");
            System.err.println("2. 请确保复制时包含全部数据");
        }
        else if (msg.contains("校验失败")) {
            System.err.println("1. Base64数据可能被意外修改");
            System.err.println("2. 请检查是否有空格/换行符被添加或删除");
        }
        else {
            System.err.println("请确保：");
            System.err.println("1. 使用原始文件的完整内容");
            System.err.println("2. 不修改文件结构（头/元数据/Base64内容/尾）");
        }
    }
}