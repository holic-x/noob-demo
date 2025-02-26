package com.noob.base.bak;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 图片水印工具类
 * 1.添加图片水印
 * 2.添加文字水印
 */
public class ImageMarkLogoUtil {
    // 水印透明度
    private static float alpha = 0.5f;
    // 水印横向位置
    private static int positionWidth = 150;
    // 水印纵向位置
    private static int positionHeight = 300;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 30);
    // 水印文字颜色
    private static Color color = Color.red;

    /**
     * @param alpha          水印透明度
     * @param positionWidth  水印横向位置
     * @param positionHeight 水印纵向位置
     * @param font           水印文字字体
     * @param color          水印文字颜色
     */
    public static void setImageMarkOptions(float alpha, int positionWidth, int positionHeight, Font font, Color color) {
        if (alpha != 0.0f) ImageMarkLogoUtil.alpha = alpha;
        if (positionWidth != 0) ImageMarkLogoUtil.positionWidth = positionWidth;
        if (positionHeight != 0) ImageMarkLogoUtil.positionHeight = positionHeight;
        if (font != null) ImageMarkLogoUtil.font = font;
        if (color != null) ImageMarkLogoUtil.color = color;
    }

    /**
     * 给图片添加水印图片
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath) {
        markImageByIcon(iconPath, srcImgPath, targetPath, null);
    }

    /**
     * 给图片添加水印图片、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     * @param degree     水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 5、得到Image对象。
            Image img = imgIcon.getImage();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 6、水印图片的位置
            g.drawImage(img, positionWidth, positionHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();
            // 8、生成图片
            os = new FileOutputStream(targetPath);
            ImageIO.write(buffImg, "JPG", os);
            System.out.println("图片完成添加水印图片");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给图片添加水印文字
     *
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByText(String logoText, String srcImgPath,
                                       String targetPath) {
        markImageByText(logoText, srcImgPath, targetPath, null);
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param srcImgPath
     * @param targetPath
     * @param degree
     */
    public static void markImageByText(String logoText, String srcImgPath, String targetPath, Integer degree) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, positionWidth, positionHeight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targetPath);
            ImageIO.write(buffImg, "JPG", os);
            System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    // 支持图片类型：png、jpg、jpeg、bmp
    public static void main(String[] args) {
        // 测试的时候需注意图像对比问题，有些水印设置不显示可能有多种原因（水印和原色没有对比度、水印信息不存在等），需要切换几组数据进行测试（可以选择对比度明显的数据进行测试：例如黑白）
        String sourcePath = "D:\\Desktop\\test\\watermark\\";
        String targetPath = "D:\\Desktop\\test\\watermark\\target\\";

        String srcImgPath = sourcePath + "original.png"; // images.jpg test.bmp
        String iconPath = sourcePath + "watermark.png";

        // 水印文字
        String logoText = "哈哈哈";
        String targetImgPath1 = targetPath + "target1.png";
        String targetImgPath2 = targetPath + "target2.png";
        System.out.println("给图片添加水印文字 start...");
        ImageMarkLogoUtil.markImageByText(logoText, srcImgPath, targetImgPath1, 0);// 给图片添加文字水印
        System.out.println("给图片添加水印文字 end...");
        System.out.println("给图片添加水印logo start...");
        // 给图片添加文字水印
        ImageMarkLogoUtil.markImageByIcon(iconPath, srcImgPath, targetImgPath2, 40); // 给图片添加icon水印
        System.out.println("给图片添加水印logo end...");
    }

}