package com.noob.utils.converter.ver2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class FileToBinaryConverterV2 {
    // 文件头魔术数字，用于标识这是我们的二进制格式
    private static final byte[] MAGIC_NUMBER = {0x42, 0x49, 0x4E, 0x46}; // "BINF"

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

    /*
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("用法: java FileToBinaryConverterV2 <输入文件> <输出文件.bin>");
            return;
        }

        try {
            convertFileToBinary(args[0], args[1]);
            System.out.println("转换成功，生成的文件包含完整性校验信息");
        } catch (IOException e) {
            System.err.println("错误: " + e.getMessage());
        }
    }
     */

    public static void main(String[] args) {
        // String inputFilePath = "D:\\Desktop\\test-other\\投行目标应用架构.pdf";
        String inputFilePath = "D:\\Desktop\\test-other\\hello.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\hello.bin";

        try {
            convertFileToBinary(inputFilePath, outputFilePath);
        } catch (IOException e) {
            System.err.println("错误: " + e.getMessage());
        }
    }
}