package com.noob.base.utils.converter.bak.ver2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.CRC32;

public class BinaryToFileConverterV2 {
    private static final byte[] MAGIC_NUMBER = {0x42, 0x49, 0x4E, 0x46}; // "BINF"

    public static void convertBinaryToFile(String inputFilePath, String outputDir) throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(inputFilePath)))) {

            // 验证魔术数字
            byte[] fileMagic = new byte[4];
            dis.readFully(fileMagic);
            if (!Arrays.equals(fileMagic, MAGIC_NUMBER)) {
                throw new IOException("无效的二进制文件格式");
            }

            // 读取版本号
            int version = dis.readByte();
            if (version != 1) {
                throw new IOException("不支持的版本: " + version);
            }

            // 读取文件名和校验信息
            String originalFileName = dis.readUTF();
            long expectedChecksum = dis.readLong();
            int contentLength = dis.readInt();

            // 读取文件内容
            byte[] fileContent = new byte[contentLength];
            dis.readFully(fileContent);

            // 验证结束标记
            byte endMarker = dis.readByte();
            if (endMarker != (byte) 0xFF) {
                throw new IOException("文件已损坏，结束标记不符");
            }

            // 校验内容完整性
            CRC32 crc = new CRC32();
            crc.update(fileContent);
            if (crc.getValue() != expectedChecksum) {
                throw new IOException("文件内容校验失败，可能已损坏");
            }

            // 写入输出文件
            Path outputPath = Paths.get(outputDir, originalFileName);
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, fileContent);

            System.out.println("成功还原文件: " + outputPath);
        }
    }

    /*
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("用法: java BinaryToFileConverterV2 <输入.bin文件> <输出目录>");
            return;
        }

        try {
            convertBinaryToFile(args[0], args[1]);
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            System.err.println("可能原因: 文件被文本编辑器修改或传输过程中损坏");
        }
    }
     */

    public static void main(String[] args) {

        // String inputFilePath = "D:\\Desktop\\test-other\\test.bin";
      String inputFilePath = "D:\\Desktop\\test-other\\hello.bin";
//        String inputFilePath = "D:\\Desktop\\test-other\\hello.json";
        String outputDir = "D:\\Desktop\\test-other";

        try {
            convertBinaryToFile(inputFilePath, outputDir);
        } catch (IOException e) {
            System.err.println("还原失败: " + e.getMessage());
            System.err.println("可能原因: 文件被文本编辑器修改或传输过程中损坏");
        }
    }

}