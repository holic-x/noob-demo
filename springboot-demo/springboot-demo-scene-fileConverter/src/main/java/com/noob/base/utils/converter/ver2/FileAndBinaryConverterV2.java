package com.noob.base.utils.converter.ver2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * 文件与二进制流互转工具
 */
public class FileAndBinaryConverterV2 {

    // 魔术数字
    private static final byte[] MAGIC_NUMBER = {0x42, 0x49, 0x4E, 0x46}; // "BINF"

    // 将源文件转化为目标二进制流
    public static void convertFileToBinary(String inputFilePath, String outputFilePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(inputFilePath));
        File inputFile = new File(inputFilePath);
        String originalFileName = inputFile.getName();

        // 计算CRC校验值
        CRC32 crc = new CRC32();
        crc.update(fileContent);
        long checksum = crc.getValue();

        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFilePath)))) {

            // 写入魔术数字
            dos.write(MAGIC_NUMBER);

            // 写入版本号
            dos.writeByte(1);

            // 写入文件名和内容
            dos.writeUTF(originalFileName); // 使用UTF写入更安全
            dos.writeLong(checksum);
            dos.writeInt(fileContent.length);
            dos.write(fileContent);

            // 写入结束标记
            dos.writeByte(0xFF);
        }
    }

    // 将二进制流转化为目标文件
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

}