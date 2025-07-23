package com.noob.base;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Word文档工具类，封装插入图片等操作 todo
 */
public class DocxUtil {

    /**
     * 在文档中插入图片，自适应页面宽度，自动识别图片类型
     * @param doc XWPFDocument
     * @param imageStream 图片输入流
     * @param fileName 图片文件名
     * @param maxWidth 最大宽度（像素），如660
     * @throws Exception
     */
    public static void insertPicture(XWPFDocument doc, InputStream imageStream, String fileName, int maxWidth) throws Exception {
        // 读取图片实际尺寸
        BufferedImage image = ImageIO.read(imageStream);
        if (image == null) {
            throw new IllegalArgumentException("无法读取图片: " + fileName);
        }
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        // 计算缩放比例
        int showWidth = imgWidth;
        int showHeight = imgHeight;
        if (imgWidth > maxWidth) {
            double ratio = (double) maxWidth / imgWidth;
            showWidth = maxWidth;
            showHeight = (int) (imgHeight * ratio);
        }

        // 重新打开图片流（ImageIO.read会消耗掉流）
        try (InputStream picStream = DocxUtil.class.getResourceAsStream("/" + fileName)) {
            if (picStream == null) {
                throw new IllegalArgumentException("图片文件未找到: " + fileName);
            }
            int format = getPictureFormat(fileName);
            XWPFParagraph paragraph = doc.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.addPicture(picStream, format, fileName, Units.toEMU(showWidth), Units.toEMU(showHeight));
        }
    }

    /**
     * 根据文件名获取图片格式
     */
    private static int getPictureFormat(String fileName) {
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".png")) return XWPFDocument.PICTURE_TYPE_PNG;
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return XWPFDocument.PICTURE_TYPE_JPEG;
        if (lower.endsWith(".gif")) return XWPFDocument.PICTURE_TYPE_GIF;
        if (lower.endsWith(".bmp")) return XWPFDocument.PICTURE_TYPE_BMP;
        if (lower.endsWith(".dib")) return XWPFDocument.PICTURE_TYPE_DIB;
        if (lower.endsWith(".tiff") || lower.endsWith(".tif")) return XWPFDocument.PICTURE_TYPE_TIFF;
        if (lower.endsWith(".emf")) return XWPFDocument.PICTURE_TYPE_EMF;
        if (lower.endsWith(".wmf")) return XWPFDocument.PICTURE_TYPE_WMF;
        if (lower.endsWith(".pict")) return XWPFDocument.PICTURE_TYPE_PICT;
        // 默认PNG
        return XWPFDocument.PICTURE_TYPE_PNG;
    }
}

推荐 maxWidth 取 660（像素），约等于 A4 页面宽度减去常规页边距。
若图片宽度小于 maxWidth，则按原尺寸插入；否则等比缩放。
注意：ImageIO.read(imageStream) 会消耗掉流，插入图片时需重新打开流。实际项目中建议将图片文件路径或 File 传入，或先将图片读入字节数组后多次使用。