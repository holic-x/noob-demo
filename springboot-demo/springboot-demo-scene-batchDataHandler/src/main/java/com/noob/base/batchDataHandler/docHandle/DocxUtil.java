package com.noob.base.batchDataHandler.docHandle;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.InputStream;

/**
 * Word文档工具类，封装插入图片等操作
 */
public class DocxUtil {

    /**
     * 在Word文档中插入一条水平分隔线（通过段落底部边框实现）
     *
     * @param document XWPFDocument对象
     */
    public static void addHorizontalLine(XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(org.apache.poi.xwpf.usermodel.Borders.SINGLE);
        // 可选：添加空行以分隔内容
        document.createParagraph();
    }

    /**
     * 根据文件名后缀判断图片类型
     */
    private static int getPictureType(String fileName) {
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".emf")) return XWPFDocument.PICTURE_TYPE_EMF;
        if (lower.endsWith(".wmf")) return XWPFDocument.PICTURE_TYPE_WMF;
        if (lower.endsWith(".pict")) return XWPFDocument.PICTURE_TYPE_PICT;
        if (lower.endsWith(".jpeg") || lower.endsWith(".jpg")) return XWPFDocument.PICTURE_TYPE_JPEG;
        if (lower.endsWith(".png")) return XWPFDocument.PICTURE_TYPE_PNG;
        if (lower.endsWith(".dib")) return XWPFDocument.PICTURE_TYPE_DIB;
        if (lower.endsWith(".gif")) return XWPFDocument.PICTURE_TYPE_GIF;
        if (lower.endsWith(".tiff")) return XWPFDocument.PICTURE_TYPE_TIFF;
        if (lower.endsWith(".eps")) return XWPFDocument.PICTURE_TYPE_EPS;
        if (lower.endsWith(".bmp")) return XWPFDocument.PICTURE_TYPE_BMP;
        if (lower.endsWith(".wpg")) return XWPFDocument.PICTURE_TYPE_WPG;
        // 默认PNG
        return XWPFDocument.PICTURE_TYPE_PNG;
        // throw new IllegalArgumentException("不支持的图片格式: " + fileName);
    }

    /**
     * 插入图片到Word文档，自动等比缩放到最大宽度（如600px），高度自适应，兼容主流图片格式。
     *
     * @param doc         XWPFDocument
     * @param imageStream 图片输入流（会被关闭）
     * @param fileName    图片文件名（用于识别图片类型）
     * @param maxWidth    最大宽度（像素）
     * @param maxHeight   最大高度（像素），如不限制可传Integer.MAX_VALUE
     * @throws Exception
     */
    public static void insertPicture(XWPFDocument doc, InputStream imageStream, String fileName, int maxWidth, int maxHeight) throws Exception {
        // 1. 先将图片流读入字节数组，避免流被多次消耗
        byte[] imageBytes;
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = imageStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            imageBytes = baos.toByteArray();
        }

        // 2. 用ImageIO读取图片实际尺寸
        java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));
        if (image == null) {
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setColor("FF0000");
            run.setText("图片无法识别或格式不支持: " + fileName);
            return;
        }
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        // 3. 计算等比缩放后的尺寸
        double widthRatio = (double) maxWidth / imgWidth;
        double heightRatio = (double) maxHeight / imgHeight;
        double ratio = Math.min(1.0, Math.min(widthRatio, heightRatio)); // 不放大，只缩小
        int showWidth = (int) (imgWidth * ratio);
        int showHeight = (int) (imgHeight * ratio);

        // 4. 插入图片
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(fileName);
        run.addBreak();

        int pictureType = getPictureType(fileName);
        try (java.io.ByteArrayInputStream picStream = new java.io.ByteArrayInputStream(imageBytes)) {
            run.addPicture(picStream, pictureType, fileName, org.apache.poi.util.Units.toEMU(showWidth), org.apache.poi.util.Units.toEMU(showHeight));
            System.out.println(String.format("【%s】图片数据插入成功，实际插入尺寸：%d x %d", fileName, showWidth, showHeight));
        } catch (Exception e) {
            run.setColor("FF0000");
            run.setText("图片插入失败: " + fileName + "，异常：" + e.getMessage());
        }
        run.addBreak();
    }

}