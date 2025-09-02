package com.noob.base.batchDataHandler.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 用于大文稿生成测试
 * 随机图片生成工具：生成指定数量的PNG图片到目标路径
 * 命名格式：mock_image_1.png、mock_image_2.png...
 */
public class RandomImageGeneratorForSimple {

    // 默认配置（可根据需求调整）
    private static final int DEFAULT_IMAGE_WIDTH = 500;  // 图片默认宽度（像素）
    private static final int DEFAULT_IMAGE_HEIGHT = 300; // 图片默认高度（像素）
    private static final String DEFAULT_IMAGE_FORMAT = "png"; // 图片格式（固定为PNG）
    private static final Random RANDOM = new Random();   // 随机数生成器（全局复用）

    // ------------------- 测试入口 -------------------
    public static void main(String[] args) {
        try {
            // 1. 自定义配置（可根据需求修改）
            // String targetDir = System.getProperty("user.dir") + "/test-files/batch_test/batch_images"; // 测试目录
            String targetDir = "E:/test/test-files/batch_test/batch_images";
            int imageCount = 50000; // 要生成的图片数量

            // 2. 调用生成方法
            generateRandomImages(targetDir, imageCount);

        } catch (Exception e) {
            System.err.println("图片生成失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 核心方法：生成指定数量的随机图片到目标路径
     *
     * @param targetDir  目标路径（不存在会自动创建）
     * @param imageCount 要生成的图片数量（必须≥1）
     * @throws IOException 路径创建或图片写入失败时抛出
     */
    public static void generateRandomImages(String targetDir, int imageCount) throws IOException {
        // 1. 校验参数合法性
        if (imageCount < 1) {
            throw new IllegalArgumentException("图片数量必须≥1，当前传入：" + imageCount);
        }
        if (targetDir == null || targetDir.trim().isEmpty()) {
            throw new IllegalArgumentException("目标路径不能为空");
        }

        // 2. 创建目标路径（不存在则递归创建）
        File dir = new File(targetDir);
        if (!dir.exists()) {
            boolean mkdirSuccess = dir.mkdirs(); // 支持多级目录创建
            if (!mkdirSuccess) {
                throw new IOException("创建目标路径失败：" + dir.getAbsolutePath());
            }
            System.out.println("已自动创建目标路径：" + dir.getAbsolutePath());
        }

        // 3. 循环生成指定数量的图片
        for (int i = 1; i <= imageCount; i++) {
            // 生成图片文件名：mock_image_1.png、mock_image_2.png...
            String imageName = "mock_image_" + i + "." + DEFAULT_IMAGE_FORMAT;
            File imageFile = new File(dir, imageName);

            // 生成随机图片并写入文件
            BufferedImage randomImage = createRandomImage(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
            ImageIO.write(randomImage, DEFAULT_IMAGE_FORMAT, imageFile);

            // 打印生成日志
            System.out.println("已生成图片：" + imageFile.getAbsolutePath());
        }

        System.out.println("\n图片生成完成！总计：" + imageCount + "张，路径：" + dir.getAbsolutePath());
    }

    /**
     * 辅助方法：创建单张随机内容的图片（避免空白，包含随机色彩和简单图形）
     *
     * @param width  图片宽度
     * @param height 图片高度
     * @return 随机内容的BufferedImage对象
     */
    private static BufferedImage createRandomImage(int width, int height) {
        // 1. 创建空白图片（RGB格式，支持透明）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        try {
            // 2. 设置随机背景色（淡色系，避免过暗）
            Color randomBgColor = new Color(
                    220 + RANDOM.nextInt(35),  // R通道（220-255）
                    220 + RANDOM.nextInt(35),  // G通道（220-255）
                    220 + RANDOM.nextInt(35)   // B通道（220-255）
            );
            g2d.setColor(randomBgColor);
            g2d.fillRect(0, 0, width, height); // 填充背景

            // 3. 绘制随机图形（增加图片辨识度，避免空白）
            drawRandomShapes(g2d, width, height);

            // 4. 绘制随机文本（可选：标注图片序号，增强可读性）
            drawRandomText(g2d, width, height);

        } finally {
            g2d.dispose(); // 释放图形资源，避免内存泄漏
        }

        return image;
    }

    /**
     * 辅助方法：在图片上绘制随机图形（圆形、矩形、线条）
     */
    private static void drawRandomShapes(Graphics2D g2d, int width, int height) {
        // 随机生成图形数量（1-3个）
        int shapeCount = 1 + RANDOM.nextInt(3);

        for (int i = 0; i < shapeCount; i++) {
            // 随机图形颜色（鲜艳色系）
            Color shapeColor = new Color(
                    RANDOM.nextInt(200),  // R通道（0-200）
                    RANDOM.nextInt(200),  // G通道（0-200）
                    RANDOM.nextInt(200)   // B通道（0-200）
            );
            g2d.setColor(shapeColor);

            // 随机选择图形类型（0=圆形，1=矩形，2=线条）
            int shapeType = RANDOM.nextInt(3);
            // 图形位置和大小（避免超出图片边界）
            int x = RANDOM.nextInt(width / 4);  // 起始X（0-1/4宽度）
            int y = RANDOM.nextInt(height / 4); // 起始Y（0-1/4高度）
            int w = 50 + RANDOM.nextInt(width / 2);  // 宽度（50-1/2宽度）
            int h = 50 + RANDOM.nextInt(height / 2); // 高度（50-1/2高度）

            switch (shapeType) {
                case 0: // 圆形（使用椭圆绘制，宽高相等即为圆形）
                    g2d.fillOval(x, y, w, w);
                    break;
                case 1: // 矩形
                    g2d.fillRect(x, y, w, h);
                    break;
                case 2: // 线条（随机两点连线）
                    int x2 = RANDOM.nextInt(width);
                    int y2 = RANDOM.nextInt(height);
                    g2d.setStroke(new BasicStroke(3)); // 线条粗细
                    g2d.drawLine(x, y, x2, y2);
                    break;
            }
        }
    }

    /**
     * 辅助方法：在图片上绘制随机文本（标注图片序号相关信息）
     */
    private static void drawRandomText(Graphics2D g2d, int width, int height) {
        // 随机文本内容（示例：图片尺寸、随机数字）
        String text = "Size: " + width + "x" + height + " | Rand: " + RANDOM.nextInt(1000);
        // 文本颜色（深色，与浅色背景对比）
        g2d.setColor(new Color(50 + RANDOM.nextInt(100), 50 + RANDOM.nextInt(100), 50 + RANDOM.nextInt(100)));
        // 字体（随机样式和大小）
        Font font = new Font(
                new String[]{"Arial", "SimHei", "Courier New"}[RANDOM.nextInt(3)], // 随机字体
                Font.BOLD + RANDOM.nextInt(2), // 粗体/正常
                16 + RANDOM.nextInt(8) // 字体大小（16-24）
        );
        g2d.setFont(font);

        // 文本位置（居中偏下，避免被图形遮挡）
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = (width - metrics.stringWidth(text)) / 2; // 水平居中
        int textY = height - 20 - RANDOM.nextInt(20); // 垂直偏下（20-40像素）
        g2d.drawString(text, textX, textY);
    }

}