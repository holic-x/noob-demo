package com.noob.base.bak;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
public class WatermarkImage {
    public static void main(String[] args) throws IOException {
        String sourcePath = "D:\\Desktop\\test\\watermark\\";
        String targetPath = "D:\\Desktop\\test\\watermark\\target\\";


        String watermarkText = "Watermark";
        File inputImageFile = new File(sourcePath+"test.png"); // 输入图片路径
        File outputImageFile = new File(targetPath+"output.png"); // 输出图片路径
 
        BufferedImage inputImage = ImageIO.read(inputImageFile);
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
 
        // 绘制原始图片
        g2d.drawImage(inputImage, 0, 0, width, height, null);
 
        // 设置水印属性
        Font font = new Font("Arial", Font.BOLD, 30); // 设置水印字体、样式和大小
        g2d.setFont(font);
        g2d.setColor(Color.BLACK); // 设置水印颜色
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
 
        // 计算水印重复次数
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(watermarkText);
        int textHeight = fm.getHeight();
        int x = 0, y = 0;
 
        // 绘制水印
        while (y < height) {
            x = 0;
            while (x < width) {
                g2d.drawString(watermarkText, x, y);
                x += textWidth + 10; // 水平间隔
            }
            y += textHeight + 10; // 垂直间隔
        }
 
        g2d.dispose();
 
        // 输出图片
        ImageIO.write(outputImage, "jpg", outputImageFile);
    }
}