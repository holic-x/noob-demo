package com.noob.utils.converter.ver3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * 文件转二进制转换器 V3
 * 功能：将任意文件转换为带校验的二进制格式文件
 */
public class FileToBinaryConverterV3 {
    // 文件头魔术数字（BINF标识）
    private static final byte[] MAGIC_NUMBER = {0x42, 0x49, 0x4E, 0x46};
    // 版本号
    private static final int VERSION = 3;
    // 文件结束标记
    private static final byte END_MARKER = (byte) 0xFF;

    public static void main(String[] args) {
        // String inputFilePath = "D:\\Desktop\\test-other\\投行目标应用架构.pdf";
        String inputFilePath = "D:\\Desktop\\test-other\\source\\hello.docx";
        String outputFilePath = "D:\\Desktop\\test-other\\hello.bin.txt";

        try {
            long startTime = System.currentTimeMillis();
            convert(inputFilePath, outputFilePath);
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

    /**
     * 执行文件转换
     *
     * @param inputPath  输入文件路径
     * @param outputPath 输出文件路径
     * @throws IOException 当IO错误发生时
     */
    public static void convert(String inputPath, String outputPath) throws IOException {
        // 读取原始文件内容
        byte[] fileData = Files.readAllBytes(Paths.get(inputPath));
        String fileName = new File(inputPath).getName();

        // 创建输出目录（如果需要）
        new File(outputPath).getParentFile().mkdirs();

        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputPath)))) {

            // 1. 写入文件头标识
            out.write(MAGIC_NUMBER);

            // 2. 写入版本号
            out.writeInt(VERSION);

            // 3. 写入原始文件名（UTF-8编码）
            out.writeUTF(fileName);

            // 4. 计算并写入CRC32校验值
            CRC32 crc = new CRC32();
            crc.update(fileData);
            out.writeLong(crc.getValue());

            // 5. 写入文件内容长度和数据
            out.writeInt(fileData.length);
            out.write(fileData);

            // 6. 写入结束标记
            out.writeByte(END_MARKER);
        }
    }
}