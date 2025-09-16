package com.noob.base.batchDataHandler.poiDoc;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 红底占位图工具类（独立组件）
 * 职责：专门生成红底白字的占位图片，用于替换不存在或读取失败的图片
 * 特点：无POI依赖，可单独复用在其他场景
 */
public class ImagePlaceholderUtil {

    /**
     * 生成红底白字的占位图片（PNG格式）
     * @param width 图片宽度（像素）
     * @param height 图片高度（像素）
     * @param text 占位文字（默认"图片缺失"）
     * @return 图片字节数组（PNG格式）
     * @throws IOException 图片生成失败时抛出
     */
    public static byte[] generateRedPlaceholder(int width, int height, String text) throws IOException {
        // 参数默认值处理
        String placeholderText = (text == null || text.trim().isEmpty()) ? "图片缺失" : text;

        // 1. 创建 BufferedImage 对象（RGB色彩模式）
        // TYPE_INT_RGB：表示每个像素由红、绿、蓝三个8位分量组成
        BufferedImage placeholder = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2. 获取绘图上下文（用于绘制图形和文字）
        Graphics2D g2d = placeholder.createGraphics();

        // 3. 设置抗锯齿（使文字边缘更平滑）
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 4. 绘制红色背景（RGB值：255,0,0）
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, width, height);  // 填充整个矩形区域

        // 5. 绘制白色文字（居中显示）
        g2d.setColor(Color.WHITE);  // 文字颜色：白色
        g2d.setFont(new Font("微软雅黑", Font.BOLD, 20));  // 字体：微软雅黑，加粗，20号

        // 计算文字居中位置
        FontMetrics metrics = g2d.getFontMetrics();  // 获取字体度量信息
        int textWidth = metrics.stringWidth(placeholderText);  // 文字宽度
        int textHeight = metrics.getHeight();  // 文字高度（包含行间距）
        int textX = (width - textWidth) / 2;  // 水平居中
        int textY = (height - textHeight) / 2 + metrics.getAscent();  // 垂直居中（ascent是基线到文字顶部的距离）

        // 绘制文字
        g2d.drawString(placeholderText, textX, textY);

        // 6. 释放资源（必须调用，否则可能导致内存泄漏）
        g2d.dispose();

        // 7. 将BufferedImage转换为PNG格式的字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(placeholder, "png", baos);  // 写入到字节输出流

        return baos.toByteArray();  // 返回字节数组
    }

    /**
     * 重载方法：使用默认文字"图片缺失"
     */
    public static byte[] generateRedPlaceholder(int width, int height) throws IOException {
        return generateRedPlaceholder(width, height, "图片缺失");
    }
}
