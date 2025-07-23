package com.noob.base.batchDataHandler.docHandle;

import org.apache.poi.util.Units;
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
     * 在文档中插入图片（自动识别图片类型）
     *
     * @param doc         XWPFDocument对象
     * @param imageStream 图片输入流
     * @param fileName    图片文件名
     * @param width       宽度（像素）
     * @param height      高度（像素）
     * @throws Exception
     */
    public static void insertPicture(XWPFDocument doc, InputStream imageStream, String fileName, int width, int height) throws Exception {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(fileName);
        run.addBreak();

        int pictureType = getPictureType(fileName);
        // 添加图片数据
        try {
            run.addPicture(imageStream, pictureType, fileName, Units.toEMU(width), Units.toEMU(height));
            System.out.println(String.format("【%s】图片数据插入成功", fileName));
        } catch (Exception e) {
            run.setColor("FF0000");
            run.setText("图片插入失败: " + fileName + "，异常：" + e.getMessage());
        }
        run.addBreak();
    }

}