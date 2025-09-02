package com.noob.base.batchDataHandler.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 用于大文稿生成测试
 * 随机图片生成工具：生成指定数量的 80-100KB PNG 图片到目标路径
 * 命名格式：mock_image_1.png、mock_image_2.png...
 * 随机图片生成工具：生成 80-100KB PNG 图片（修复 Color 数值越界问题）
 * todo 无法适配生成
 */
public class RandomImageGeneratorForLimitIn100KB {

    // 核心配置：1200×800 分辨率（确保 PNG 体积在 80-100KB）
    private static final int DEFAULT_IMAGE_WIDTH = 1200;
    private static final int DEFAULT_IMAGE_HEIGHT = 800;
    private static final String DEFAULT_IMAGE_FORMAT = "png";
    private static final Random RANDOM = new Random();
    // 体积控制范围（KB）
    private static final int MIN_TARGET_SIZE_KB = 80;
    private static final int MAX_TARGET_SIZE_KB = 100;

    public static void main(String[] args) {
        try {
            String targetDir = "E:/test/test-files/batch_test/batch_images";
            int imageCount = 10; // 测试生成10张，可调整
            generateRandomImages(targetDir, imageCount);
        } catch (Exception e) {
            System.err.println("图片生成失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 核心方法：生成指定数量的 80-100KB PNG 图片
     */
    public static void generateRandomImages(String targetDir, int imageCount) throws IOException {
        // 参数校验
        if (imageCount < 1) {
            throw new IllegalArgumentException("图片数量必须≥1，当前传入：" + imageCount);
        }
        if (targetDir == null || targetDir.trim().isEmpty()) {
            throw new IllegalArgumentException("目标路径不能为空");
        }

        // 创建目标路径
        File dir = new File(targetDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("创建目标路径失败：" + dir.getAbsolutePath());
        }
        System.out.println("目标路径：" + dir.getAbsolutePath() + "（已就绪）");

        // 循环生成图片（带体积校验）
        for (int i = 1; i <= imageCount; i++) {
            String imageName = "mock_image_" + i + "." + DEFAULT_IMAGE_FORMAT;
            File imageFile = new File(dir, imageName);
            boolean sizeValid = false;

            // 最多重试3次（确保体积合规）
            for (int retry = 0; retry < 3 && !sizeValid; retry++) {
                // 生成图片并写入
                BufferedImage randomImage = createRandomImage(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
                ImageIO.write(randomImage, DEFAULT_IMAGE_FORMAT, imageFile);

                // 检查体积
                long fileSizeKb = imageFile.length() / 1024;
                if (fileSizeKb >= MIN_TARGET_SIZE_KB && fileSizeKb <= MAX_TARGET_SIZE_KB) {
                    sizeValid = true;
                    System.out.printf("已生成图片[%d/%d]：%s（大小：%dKB）%n",
                            i, imageCount, imageFile.getAbsolutePath(), fileSizeKb);
                } else {
                    System.out.printf("重试[%d]：图片大小%dKB超出范围（需80-100KB），重新生成...%n",
                            retry + 1, fileSizeKb);
                    // 删除无效文件
                    if (imageFile.exists() && !imageFile.delete()) {
                        throw new IOException("删除无效图片失败：" + imageFile.getAbsolutePath());
                    }
                }
            }

            // 3次重试仍无效，抛出异常
            if (!sizeValid) {
                throw new IOException("图片" + imageName + "生成3次仍超出范围，请调整分辨率或图形复杂度");
            }
        }

        System.out.println("\n全部图片生成完成！总计：" + imageCount + "张");
    }

    /**
     * 创建单张高细节图片（核心：修复颜色越界）
     */
    private static BufferedImage createRandomImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        try {
            drawGradientBackground(g2d, width, height); // 修复后的渐变背景
            drawHighDensityShapes(g2d, width, height);   // 高密度图形
            drawMultiLineRandomText(g2d, width, height); // 多行文本
        } finally {
            g2d.dispose(); // 释放资源
        }

        return image;
    }

    /**
     * 修复：渐变背景（增加 RGB 数值范围校验，避免越界）
     */
    private static void drawGradientBackground(Graphics2D g2d, int width, int height) {
        // 1. 生成起始色（确保 R/G/B 在 180-250 之间，预留调整空间）
        int startR = 180 + RANDOM.nextInt(70); // 180-250
        int startG = 180 + RANDOM.nextInt(70);
        int startB = 180 + RANDOM.nextInt(70);
        Color startColor = new Color(startR, startG, startB);

        // 2. 生成结束色（基于起始色微调，且通过 clamp 确保在 0-255 之间）
        int endR = clamp(startR - 20 + RANDOM.nextInt(40), 0, 255); // 波动范围：-20~+20
        int endG = clamp(startG - 20 + RANDOM.nextInt(40), 0, 255);
        int endB = clamp(startB - 20 + RANDOM.nextInt(40), 0, 255);
        Color endColor = new Color(endR, endG, endB);

        // 3. 绘制线性渐变
        GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

    /**
     * 辅助方法：将数值限制在 [min, max] 范围内（核心修复逻辑）
     */
    private static int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    /**
     * 高密度图形绘制（增加细节，确保体积）
     */
    private static void drawHighDensityShapes(Graphics2D g2d, int width, int height) {
        int shapeCount = 5 + RANDOM.nextInt(6); // 5-10个图形

        for (int i = 0; i < shapeCount; i++) {
            // 生成图形颜色（通过 clamp 确保 R/G/B 在 0-220 之间）
            int r = clamp(RANDOM.nextInt(250), 0, 220);
            int g = clamp(RANDOM.nextInt(250), 0, 220);
            int b = clamp(RANDOM.nextInt(250), 0, 220);
            Color shapeColor = new Color(r, g, b);

            g2d.setColor(shapeColor);
            g2d.setStroke(new BasicStroke(2 + RANDOM.nextInt(4))); // 线条粗细 2-5px

            // 图形位置和大小（避免超出边界）
            int x = RANDOM.nextInt(width * 3 / 4);
            int y = RANDOM.nextInt(height * 3 / 4);
            int w = 80 + RANDOM.nextInt(width / 4);
            int h = 80 + RANDOM.nextInt(height / 4);

            // 随机图形类型（圆形、矩形、圆角矩形、折线、点集）
            int shapeType = RANDOM.nextInt(5);
            switch (shapeType) {
                case 0: // 圆形（填充+描边）
                    g2d.fillOval(x, y, w, w);
                    g2d.setColor(shapeColor.darker());
                    g2d.drawOval(x, y, w, w);
                    break;
                case 1: // 矩形（填充+描边）
                    g2d.fillRect(x, y, w, h);
                    g2d.setColor(shapeColor.darker());
                    g2d.drawRect(x, y, w, h);
                    break;
                case 2: // 圆角矩形
                    int arc = 20 + RANDOM.nextInt(30);
                    g2d.fillRoundRect(x, y, w, h, arc, arc);
                    g2d.setColor(shapeColor.darker());
                    g2d.drawRoundRect(x, y, w, h, arc, arc);
                    break;
                case 3: // 折线（3-5个折点）
                    int pointCount = 3 + RANDOM.nextInt(3);
                    int[] xs = new int[pointCount];
                    int[] ys = new int[pointCount];
                    for (int p = 0; p < pointCount; p++) {
                        xs[p] = x + RANDOM.nextInt(w);
                        ys[p] = y + RANDOM.nextInt(h);
                    }
                    g2d.drawPolyline(xs, ys, pointCount);
                    break;
                case 4: // 点集（10-20个点）
                    int dotCount = 10 + RANDOM.nextInt(11);
                    for (int d = 0; d < dotCount; d++) {
                        int dotX = x + RANDOM.nextInt(w);
                        int dotY = y + RANDOM.nextInt(h);
                        g2d.fillOval(dotX, dotY, 5 + RANDOM.nextInt(5), 5 + RANDOM.nextInt(5));
                    }
                    break;
            }
        }
    }

    /**
     * 多行随机文本（增加细节）
     */
    private static void drawMultiLineRandomText(Graphics2D g2d, int width, int height) {
        String[] textLines = new String[]{
                "Size: " + width + "×" + height + " | Rand: " + RANDOM.nextInt(10000),
                "POI Test: Mock Screenshot for Merging",
                "Time: " + System.currentTimeMillis() % 1000000,
                "Detail: " + RANDOM.nextInt(500) + " Shapes | " + RANDOM.nextInt(20) + " Colors"
        };
        int lineCount = 3 + RANDOM.nextInt(3); // 3-5行文本
        if (lineCount > textLines.length) lineCount = textLines.length;

        // 文本颜色（深色，避免越界）
        int textR = clamp(30 + RANDOM.nextInt(80), 0, 110);
        int textG = clamp(30 + RANDOM.nextInt(80), 0, 110);
        int textB = clamp(30 + RANDOM.nextInt(80), 0, 110);
        g2d.setColor(new Color(textR, textG, textB));

        // 文本字体
        Font font = new Font(
                new String[]{"Arial", "SimHei", "Courier New", "Times New Roman"}[RANDOM.nextInt(4)],
                Font.BOLD + RANDOM.nextInt(2),
                18 + RANDOM.nextInt(6)
        );
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();

        // 文本位置（居中偏下）
        int startY = height - 100 - RANDOM.nextInt(30);
        for (int l = 0; l < lineCount; l++) {
            String line = textLines[l];
            int textX = (width - metrics.stringWidth(line)) / 2;
            int textY = startY + l * 25; // 行间距25px
            g2d.drawString(line, textX, textY);
        }
    }
}