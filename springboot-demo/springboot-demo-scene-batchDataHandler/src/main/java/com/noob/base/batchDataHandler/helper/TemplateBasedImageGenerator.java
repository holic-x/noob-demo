package com.noob.base.batchDataHandler.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TemplateBasedImageGenerator {

    // ==================== 核心配置区（所有参数在这里修改，无需改逻辑） ====================
    // 1. 路径配置
    private static final String TEMPLATE_IMAGE_PATH = "E:\\test\\test-files\\batch_test_ver02\\template.png"; // 模板图片路径
    private static final String OUTPUT_DIR = "E:\\test\\test-files\\batch_test_ver02\\batch_images"; // 输出路径
    private static final int GENERATE_COUNT = 50; // 生成图片数量

    // 2. 序号文本样式配置
    private static final int FONT_SIZE = 128; // 字体大小（px）
    private static final String FONT_NAME = "SimHei"; // 字体（支持"Arial"、"SimHei"等）
    private static final Color TEXT_COLOR = Color.RED; // 序号字体颜色（如Color.RED、new Color(255,0,0)）
    private static final Color TEXT_STROKE_COLOR = Color.BLACK; // 序号描边颜色（增强清晰度）
    private static final float TEXT_STROKE_WIDTH = 3.0f; // 描边粗细（px）

    // 3. 序号位置配置（重点！支持居中+偏移）
    private static final String POSITION_MODE = "CENTER"; // 位置模式：CENTER（居中）、CUSTOM（自定义坐标）
    // 模式1：POSITION_MODE=CENTER 时生效（居中基础上的偏移量，正数向右/向下，负数向左/向上）
    private static final int CENTER_OFFSET_X = 0; // 水平偏移（0=纯居中）
    private static final int CENTER_OFFSET_Y = 0; // 垂直偏移（0=纯居中）
    // 模式2：POSITION_MODE=CUSTOM 时生效（自定义绝对坐标，左上角为(0,0)）
    private static final int CUSTOM_POS_X = 200; // 自定义X坐标
    private static final int CUSTOM_POS_Y = 300; // 自定义Y坐标
    // =====================================================================================

    public static void main(String[] args) {
        try {
            // 1. 读取模板图片
            BufferedImage templateImage = readTemplateImage();
            if (templateImage == null) {
                System.err.println("模板图片读取失败，请检查路径：" + TEMPLATE_IMAGE_PATH);
                return;
            }
            System.out.printf("模板图片读取成功！分辨率：%dx%d，格式：%s%n",
                    templateImage.getWidth(), templateImage.getHeight(),
                    getImageFormat(TEMPLATE_IMAGE_PATH));

            // 2. 创建输出目录
            createOutputDir();

            // 3. 批量生成带序号的图片
            generateImagesWithSerialNumber(templateImage);

            System.out.printf("%n✅ 生成完成！总计：%d张，输出路径：%s%n",
                    GENERATE_COUNT, OUTPUT_DIR);

        } catch (Exception e) {
            System.err.println("图片生成异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 读取模板图片（保持原分辨率和格式）
     */
    private static BufferedImage readTemplateImage() {
        try {
            File templateFile = new File(TEMPLATE_IMAGE_PATH);
            if (!templateFile.exists()) {
                System.err.println("模板图片不存在：" + TEMPLATE_IMAGE_PATH);
                return null;
            }
            return ImageIO.read(templateFile);
        } catch (IOException e) {
            System.err.println("模板图片读取IO异常：" + e.getMessage());
            return null;
        }
    }

    /**
     * 创建输出目录（不存在则自动创建多级目录）
     */
    private static void createOutputDir() throws IOException {
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            boolean mkdirSuccess = outputDir.mkdirs();
            if (!mkdirSuccess) {
                throw new IOException("创建输出目录失败：" + OUTPUT_DIR);
            }
            System.out.println("已自动创建输出目录：" + OUTPUT_DIR);
        }
    }

    /**
     * 核心逻辑：给模板图片添加序号并生成新图片
     */
    private static void generateImagesWithSerialNumber(BufferedImage templateImage) throws IOException {
        int imgWidth = templateImage.getWidth();
        int imgHeight = templateImage.getHeight();
        String imgFormat = getImageFormat(TEMPLATE_IMAGE_PATH);

        for (int serial = 1; serial <= GENERATE_COUNT; serial++) {
            // 1. 创建新图片缓冲区（复制模板像素）
            BufferedImage newImage = new BufferedImage(imgWidth, imgHeight, templateImage.getType());
            Graphics2D g2d = newImage.createGraphics();

            try {
                // 2. 绘制模板图片（基础内容）
                g2d.drawImage(templateImage, 0, 0, imgWidth, imgHeight, null);

                // 3. 配置序号样式（字体、描边、抗锯齿）
                configureTextStyle(g2d);

                // 4. 计算序号位置（根据模式选择居中/自定义）
                String serialText = String.valueOf(serial);
                Point textPos = calculateTextPosition(g2d, serialText, imgWidth, imgHeight);

                // 5. 绘制序号（先描边后填色，避免与背景融合）
                // 5.1 绘制描边（单独处理，避免描边覆盖字体）
                g2d.setColor(TEXT_STROKE_COLOR);
                g2d.drawString(serialText, textPos.x, textPos.y);
                // 5.2 绘制字体（覆盖在描边上）
                g2d.setColor(TEXT_COLOR);
                g2d.drawString(serialText, textPos.x, textPos.y);

                // 6. 保存图片
                String imgName = "mock_image_" + serial + "." + imgFormat;
                File imgFile = new File(OUTPUT_DIR, imgName);
                ImageIO.write(newImage, imgFormat, imgFile);

                // 打印日志（显示大小和位置）
                System.out.printf("已生成：%s | 序号：%d | 大小：%.2fKB | 位置：(%d,%d)%n",
                        imgName, serial, imgFile.length() / 1024.0, textPos.x, textPos.y);

            } finally {
                g2d.dispose(); // 释放资源，防止内存泄漏
            }
        }
    }

    /**
     * 配置序号文本样式（字体、描边、抗锯齿）
     */
    private static void configureTextStyle(Graphics2D g2d) {
        // 1. 设置字体（加粗）
        Font textFont = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
        g2d.setFont(textFont);

        // 2. 设置描边（增强文字清晰度）
        Stroke textStroke = new BasicStroke(TEXT_STROKE_WIDTH);
        g2d.setStroke(textStroke);

        // 3. 开启抗锯齿（文字边缘更平滑）
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /**
     * 计算序号位置（支持居中/自定义两种模式）
     * @return 序号的左上角坐标 (x,y)
     */
    private static Point calculateTextPosition(Graphics2D g2d, String text, int imgWidth, int imgHeight) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text); // 序号文本宽度
        int textHeight = metrics.getAscent(); // 序号文本高度（从基线到顶部）

        if ("CENTER".equalsIgnoreCase(POSITION_MODE)) {
            // 模式1：居中（基于图片中心计算）
            int centerX = imgWidth / 2; // 图片水平中心
            int centerY = imgHeight / 2; // 图片垂直中心
            // 文本左上角X = 中心X - 文本宽度/2 + 水平偏移
            int x = centerX - (textWidth / 2) + CENTER_OFFSET_X;
            // 文本左上角Y = 中心Y + 文本高度/2 + 垂直偏移（因FontMetrics以基线计算，需调整）
            int y = centerY + (textHeight / 2) + CENTER_OFFSET_Y;
            return new Point(x, y);
        } else if ("CUSTOM".equalsIgnoreCase(POSITION_MODE)) {
            // 模式2：自定义坐标（直接使用配置的X/Y）
            // 注意：Y坐标是文本基线位置，不是左上角（符合Graphics2D默认规则）
            return new Point(CUSTOM_POS_X, CUSTOM_POS_Y + textHeight);
        } else {
            // 默认 fallback 到居中
            System.out.println("位置模式配置错误，默认使用居中模式！");
            int x = (imgWidth - textWidth) / 2;
            int y = (imgHeight + textHeight) / 2;
            return new Point(x, y);
        }
    }

    /**
     * 从模板路径提取图片格式（如 png、jpg）
     */
    private static String getImageFormat(String templatePath) {
        int dotIndex = templatePath.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == templatePath.length() - 1) {
            return "png"; // 默认格式
        }
        return templatePath.substring(dotIndex + 1).toLowerCase();
    }
}