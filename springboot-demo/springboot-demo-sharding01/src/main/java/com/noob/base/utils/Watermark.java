package com.noob.base.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Watermark {

    public static void main(String[] args) {
        try {
            String sourcePath = "D:\\Desktop\\test\\watermark\\";
            String targetPath = "D:\\Desktop\\test\\watermark\\target\\";
            // 读取原始图片和水印图片
            BufferedImage originalImage = ImageIO.read(new File(sourcePath + "original.png"));
            BufferedImage watermarkImage = ImageIO.read(new File(sourcePath + "watermark.png"));

            // 创建新图片
            BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = newImage.createGraphics();

            // 将水印图片平铺在新图片上
            for (int y = 0; y < originalImage.getHeight(); y += watermarkImage.getHeight()) {
                for (int x = 0; x < originalImage.getWidth(); x += watermarkImage.getWidth()) {
                    graphics.drawImage(watermarkImage, x, y, null);
                }
            }

            // 将原始图片贴在新图片上（设置位置）
            graphics.drawImage(originalImage, 0, 0, null);
            graphics.dispose();

            // 保存新图片
            ImageIO.write(newImage, "png", new File(targetPath + "result.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}