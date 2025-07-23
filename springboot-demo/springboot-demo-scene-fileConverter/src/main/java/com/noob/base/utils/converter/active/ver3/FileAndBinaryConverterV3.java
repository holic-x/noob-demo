package com.noob.base.utils.converter.active.ver3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * 文件与二进制流互转工具
 */
public class FileAndBinaryConverterV3 {

    // 文件头魔术数字（BINF标识）
    private static final byte[] MAGIC_NUMBER = {0x42, 0x49, 0x4E, 0x46};

    // 版本号
    private static final int VERSION = 3;

    // 文件结束标记
    private static final byte END_MARKER = (byte) 0xFF;

    // 支持的版本号
    private static final int SUPPORTED_VERSION = 3; // 此处版本号需注意保持一致用于验证是同一套代码版本转化

    // 预期的结束标记
    private static final byte EXPECTED_END_MARKER = (byte) 0xFF;

    /**
     * 执行文件转换：将源文件转化为目标二进制流
     *
     * @param inputPath  输入文件路径
     * @param outputPath 输出文件路径
     * @throws IOException 当IO错误发生时
     */
    public static void convertFileToBinary(String inputPath, String outputPath) throws IOException {
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


    /**
     * 执行文件还原：restore
     *
     * @param inputPath 输入文件路径
     * @param outputDir 输出目录
     * @return 还原后的文件路径
     * @throws IOException 当IO错误或数据校验失败时
     */
    public static String convertBinaryToFile(String inputPath, String outputDir) throws IOException {
        File inputFile = new File(inputPath);
        // 基础检查
        if (!inputFile.exists()) {
            throw new IOException("输入文件不存在");
        }
        if (inputFile.length() < 20) { // 最小合理文件大小
            throw new IOException("输入文件过小，可能已损坏");
        }

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(inputPath)))) {

            // 1. 验证魔术数字
            byte[] magic = new byte[4];
            in.readFully(magic);
            if (!Arrays.equals(magic, MAGIC_NUMBER)) {
                throw new IOException("无效的文件格式（魔术数字不匹配）");
            }

            // 2. 验证版本号
            int version = in.readInt();
            if (version != SUPPORTED_VERSION) {
                throw new IOException("不支持的版本号: " + version);
            }

            // 3. 读取原始文件名
            String fileName = in.readUTF();

            // 4. 读取校验值
            long expectedChecksum = in.readLong();

            // 5. 读取文件数据
            int dataLength = in.readInt();
            if (dataLength < 0 || dataLength > in.available()) {
                throw new IOException("无效的数据长度: " + dataLength);
            }

            byte[] fileData = new byte[dataLength];
            in.readFully(fileData);

            // 6. 验证结束标记
            if (in.available() > 0) {
                byte endMarker = in.readByte();
                if (endMarker != EXPECTED_END_MARKER) {
                    throw new IOException("文件结束标记不符（应为0xFF）");
                }
            }

            // 7. 校验数据完整性
            CRC32 crc = new CRC32();
            crc.update(fileData);
            if (crc.getValue() != expectedChecksum) {
                throw new IOException(String.format(
                        "数据校验失败（预期: %d, 实际: %d）",
                        expectedChecksum, crc.getValue()));
            }

            // 8. 写入输出文件
            Path outputPath = Paths.get(outputDir, fileName);
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, fileData);

            return outputPath.toString();
        }
    }

    /**
     * 根据异常类型提供排查建议
     */
    public static void printTroubleshootingTips(IOException e) {
        String msg = e.getMessage();
        System.err.println("\n问题排查建议:");

        if (msg.contains("魔术数字")) {
            System.err.println("1. 请确认这是通过FileToBinaryConverterV3生成的二进制文件");
            System.err.println("2. 文件可能被文本编辑器修改过，请使用原始文件");
        } else if (msg.contains("版本号")) {
            System.err.println("1. 请使用相同版本的转换器进行还原");
            System.err.println("2. 当前支持版本: V" + SUPPORTED_VERSION);
        } else if (msg.contains("校验失败")) {
            System.err.println("1. 文件内容可能在传输过程中被修改");
            System.err.println("2. 请重新生成二进制文件");
        } else if (msg.contains("结束标记")) {
            System.err.println("1. 文件可能未完整传输");
            System.err.println("2. 请检查文件是否完整下载");
        } else {
            System.err.println("请检查文件路径是否正确，以及是否有读写权限");
        }
    }

}