package com.noob.base.batchDataHandler.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 基于模板图片生成带序号标记的新图片
 * 逻辑：读取模板图片 → 在右下角添加顺序序号 → 生成新图片（mock_image_1.png...）
 */
public class TemplateBasedImageGeneratorForSimple {

    // ------------------- 配置参数（需根据你的模板图片调整） -------------------
    private static final String TEMPLATE_IMAGE_PATH = "E:\\test\\test-files\\batch_test_ver02\\template.png"; // 你的模板图片路径
    private static final String OUTPUT_DIR = "E:\\test\\test-files\\batch_test_ver02\\batch_images"; // 新图片输出路径
    private static final int GENERATE_COUNT = 50; // 要生成的新图片数量（如50张）
    // 序号标记样式配置（可按需调整）
    /*
    private static final int FONT_SIZE = 24; // 序号字体大小（px）
    private static final int TEXT_MARGIN_RIGHT = 20; // 序号距图片右边缘距离（px）
    private static final int TEXT_MARGIN_BOTTOM = 40; // 序号距图片下边缘距离（px）
     */

    private static final int FONT_SIZE = 24; // 序号字体大小（px）
    private static final int TEXT_MARGIN_RIGHT = 20; // 序号距图片右边缘距离（px）
    private static final int TEXT_MARGIN_BOTTOM = 40; // 序号距图片下边缘距离（px）



    public static void main(String[] args) {
        try {
            // 1. 读取模板图片（核心：确保模板图片路径正确）
            BufferedImage templateImage = readTemplateImage();
            if (templateImage == null) {
                System.err.println("模板图片读取失败，请检查路径：" + TEMPLATE_IMAGE_PATH);
                return;
            }
            System.out.println("模板图片读取成功！分辨率：" + templateImage.getWidth() + "×" + templateImage.getHeight());

            // 2. 创建输出目录（不存在则自动创建）
            createOutputDir();

            // 3. 循环生成带序号的新图片
            generateImagesWithSerialNumber(templateImage);

            System.out.println("\n✅ 所有图片生成完成！总计：" + GENERATE_COUNT + "张，输出路径：" + OUTPUT_DIR);

        } catch (Exception e) {
            System.err.println("图片生成异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 步骤1：读取模板图片
     */
    private static BufferedImage readTemplateImage() {
        try {
            File templateFile = new File(TEMPLATE_IMAGE_PATH);
            if (!templateFile.exists()) {
                System.err.println("模板图片不存在：" + TEMPLATE_IMAGE_PATH);
                return null;
            }
            // 读取模板图片（保持原分辨率和格式）
            return ImageIO.read(templateFile);
        } catch (IOException e) {
            System.err.println("模板图片读取IO异常：" + e.getMessage());
            return null;
        }
    }

    /**
     * 步骤2：创建输出目录
     */
    private static void createOutputDir() throws IOException {
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            boolean mkdirSuccess = outputDir.mkdirs(); // 支持多级目录创建
            if (!mkdirSuccess) {
                throw new IOException("创建输出目录失败：" + OUTPUT_DIR);
            }
            System.out.println("已自动创建输出目录：" + OUTPUT_DIR);
        }
    }

    /**
     * 步骤3：核心逻辑——给模板图片添加序号并生成新图片
     */
    private static void generateImagesWithSerialNumber(BufferedImage templateImage) throws IOException {
        int imageWidth = templateImage.getWidth(); // 继承模板宽度
        int imageHeight = templateImage.getHeight(); // 继承模板高度
        String imageFormat = getImageFormat(TEMPLATE_IMAGE_PATH); // 继承模板格式（png/jpg等）

        // 循环生成每张带序号的图片
        for (int serial = 1; serial <= GENERATE_COUNT; serial++) {
            // 1. 创建新图片缓冲区（复制模板内容）
            BufferedImage newImage = new BufferedImage(imageWidth, imageHeight, templateImage.getType());
            Graphics2D g2d = newImage.createGraphics();

            try {
                // 2. 复制模板图片到新缓冲区（基础内容不变）
                g2d.drawImage(templateImage, 0, 0, imageWidth, imageHeight, null);

                // 3. 配置序号文本样式（白色字体+黑色描边，确保清晰） todo 文本样式颜色
                configureTextStyle(g2d);

                // 4. 计算序号文本的位置（右下角对齐）
                String serialText = String.valueOf(serial); // 序号文本（1、2、3...）
                FontMetrics fontMetrics = g2d.getFontMetrics();
                // 文本X坐标 = 图片宽度 - 右间距 - 文本宽度
                int textX = imageWidth - TEXT_MARGIN_RIGHT - fontMetrics.stringWidth(serialText);
                // 文本Y坐标 = 图片高度 - 下间距（FontMetrics.getAscent()确保文本底部对齐）
                int textY = imageHeight - TEXT_MARGIN_BOTTOM + fontMetrics.getAscent();

                // 5. 绘制序号文本（先画黑色描边，再画白色字体，避免与模板背景融合）
                g2d.drawString(serialText, textX, textY); // 黑色描边（通过Stroke实现）
                g2d.setColor(Color.WHITE); // 切换为白色字体
                g2d.drawString(serialText, textX, textY); // 白色序号

                // 6. 生成新图片文件
                String newImageName = "mock_image_" + serial + "." + imageFormat;
                File newImageFile = new File(OUTPUT_DIR, newImageName);
                ImageIO.write(newImage, imageFormat, newImageFile);

                // 打印进度日志
                System.out.printf("已生成：%s（序号：%d，大小：%.2fKB）%n",
                        newImageFile.getName(),
                        serial,
                        newImageFile.length() / 1024.0); // 显示文件大小（KB）

            } finally {
                // 释放图形资源，避免内存泄漏
                g2d.dispose();
            }
        }
    }

    /**
     * 辅助：配置序号文本样式（加粗、描边）
     */
    private static void configureTextStyle(Graphics2D g2d) {
        // 1. 设置字体（加粗，支持中文）
        Font textFont = new Font("SimHei", Font.BOLD, FONT_SIZE);
        g2d.setFont(textFont);

        // 2. 设置文本描边（黑色，粗细2px，避免序号与模板背景混淆）
        Stroke textStroke = new BasicStroke(2.0f);
        g2d.setStroke(textStroke);
        g2d.setColor(Color.BLACK); // 先设置黑色描边

        // 3. 开启抗锯齿（让文本边缘更平滑）
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 辅助：从模板图片路径中提取格式（如 "png"、"jpg"）
     */
    private static String getImageFormat(String templatePath) {
        int dotIndex = templatePath.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == templatePath.length() - 1) {
            return "png"; // 默认格式为png
        }
        return templatePath.substring(dotIndex + 1).toLowerCase();
    }
}