package com.noob.base.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImageWatermarkUtil 为图片添加文字、图片水印
 * - 添加到指定位置 或者 平铺整个图片
 */
public class ImageWatermarkUtil {

    /**
     * 添加文字水印
     *
     * @param sourceImagePath 源图片路径
     * @param targetImagePath 目标图片路径
     * @param text            水印文字
     * @param font            字体
     * @param color           颜色
     * @param x               水印起始x坐标
     * @param y               水印起始y坐标
     * @param alpha           透明度 (0.0 - 1.0)
     * @throws IOException 如果图片读取或写入失败
     */
    public static void addTextWatermark(String sourceImagePath, String targetImagePath, String text, Font font, Color color, int x, int y, float alpha) throws IOException {
        File sourceImageFile = new File(sourceImagePath);
        BufferedImage sourceImage = ImageIO.read(sourceImageFile);

        // 创建Graphics2D对象
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // 设置水印透明度
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        // 设置水印字体和颜色
        g2d.setFont(font);
        g2d.setColor(color);

        // 绘制水印文字
        g2d.drawString(text, x, y);

        // 释放资源
        g2d.dispose();

        // 保存目标图片
        ImageIO.write(sourceImage, "png", new File(targetImagePath));
    }

    /**
     * 添加图片水印
     *
     * @param sourceImagePath    源图片路径
     * @param targetImagePath    目标图片路径
     * @param watermarkImagePath 水印图片路径
     * @param x                  水印起始x坐标
     * @param y                  水印起始y坐标
     * @param alpha              透明度 (0.0 - 1.0)
     * @throws IOException 如果图片读取或写入失败
     */
    public static void addImageWatermark(String sourceImagePath, String targetImagePath, String watermarkImagePath, int x, int y, float alpha) throws IOException {
        File sourceImageFile = new File(sourceImagePath);
        File watermarkImageFile = new File(watermarkImagePath);

        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);

        // 创建Graphics2D对象
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // 设置水印透明度
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        // 绘制水印图片
        g2d.drawImage(watermarkImage, x, y, null);

        // 释放资源
        g2d.dispose();

        // 保存目标图片
        ImageIO.write(sourceImage, "png", new File(targetImagePath));
    }

    /**
     * 添加平铺文字水印（覆盖整个图片）
     *
     * @param sourceImagePath 源图片路径
     * @param targetImagePath 目标图片路径
     * @param text            水印文字
     * @param font            字体
     * @param color           颜色
     * @param alpha           透明度 (0.0 - 1.0)
     * @throws IOException 如果图片读取或写入失败
     */
    public static void addTiledTextWatermark(String sourceImagePath, String targetImagePath, String text, Font font, Color color, float alpha) throws IOException {
        File sourceImageFile = new File(sourceImagePath);
        BufferedImage sourceImage = ImageIO.read(sourceImageFile);

        // 创建Graphics2D对象
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // 设置水印透明度
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        // 设置水印字体和颜色
        g2d.setFont(font);
        g2d.setColor(color);

        // 计算水印文字的大小
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();

        // 平铺水印
        for (int i = 0; i < sourceImage.getWidth(); i += textWidth + 50) {
            for (int j = 0; j < sourceImage.getHeight(); j += textHeight + 50) {
                g2d.drawString(text, i, j);
            }
        }

        // 释放资源
        g2d.dispose();

        // 保存目标图片
        ImageIO.write(sourceImage, "png", new File(targetImagePath));
    }

    /**
     * 添加平铺图片水印（覆盖整个图片）
     *
     * @param sourceImagePath    源图片路径
     * @param targetImagePath    目标图片路径
     * @param watermarkImagePath 水印图片路径
     * @param alpha              透明度 (0.0 - 1.0)
     * @throws IOException 如果图片读取或写入失败
     */
    public static void addTiledImageWatermark(String sourceImagePath, String targetImagePath, String watermarkImagePath, float alpha) throws IOException {
        File sourceImageFile = new File(sourceImagePath);
        File watermarkImageFile = new File(watermarkImagePath);

        BufferedImage sourceImage = ImageIO.read(sourceImageFile);
        BufferedImage watermarkImage = ImageIO.read(watermarkImageFile);

        // 创建Graphics2D对象
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

        // 设置水印透明度
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alphaComposite);

        // 平铺水印图片
        for (int i = 0; i < sourceImage.getWidth(); i += watermarkImage.getWidth()) {
            for (int j = 0; j < sourceImage.getHeight(); j += watermarkImage.getHeight()) {
                g2d.drawImage(watermarkImage, i, j, null);
            }
        }

        // 释放资源
        g2d.dispose();

        // 保存目标图片
        ImageIO.write(sourceImage, "png", new File(targetImagePath));
    }

    public static void main(String[] args) {
        try {
            String sourcePath = "D:\\Desktop\\test\\watermark\\";
            String targetPath = "D:\\Desktop\\test\\watermark\\target\\";

            // 示例：添加文字水印
            addTextWatermark(sourcePath + "original.png", targetPath + "output_text.png", "Watermark Text", new Font("Arial", Font.BOLD, 30), Color.RED, 50, 50, 0.5f);

            // 示例：添加图片水印
            addImageWatermark(sourcePath + "original.png", targetPath + "output_image.png", sourcePath + "watermark.png", 50, 50, 0.5f);

            // 示例：添加平铺文字水印
            addTiledTextWatermark(sourcePath + "original.png", targetPath + "output_tiled_text.png", "Watermark", new Font("Arial", Font.BOLD, 30), Color.BLUE, 0.3f);

            // 示例：添加平铺图片水印
            addTiledImageWatermark(sourcePath + "original.png", targetPath + "output_tiled_image.png", sourcePath + "watermark.png", 0.3f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}