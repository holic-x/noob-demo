package com.noob.base.utils;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SimpleITextWatermarkForPdfUtil 基于itext为pdf添加水印
 */
public class SimpleITextWatermarkForPdfUtil {
    public static void main(String[] args) {
        String sourcePath = "D:\\Desktop\\test\\watermark\\"; // 输入的PDF文件路径
        String outputPath = "D:\\Desktop\\test\\watermark\\target\\"; // 输出的PDF文件路径

        String inputPdf = sourcePath + "source.pdf";
        String outputPdf = outputPath + "output_with_watermark.pdf";
        String watermarkText = "CONFIDENTIAL"; // 文字水印内容
        String imagePath = sourcePath + "logo.png"; // 图片水印路径

        try {
            // 读取原始PDF
            PdfReader reader = new PdfReader(inputPdf);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPdf));

            // 获取PDF的总页数
            int totalPages = reader.getNumberOfPages();

            // 创建水印
            PdfContentByte content;
            BaseFont font = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);

            for (int i = 1; i <= totalPages; i++) {
                // 获取当前页的内容
                content = stamper.getUnderContent(i);

                // 添加文字水印
                addTextWatermark(content, watermarkText, font, 52, 45, 0.5f);

                // 添加图片水印
                addImageWatermark(content, imagePath, 200, 400, 45, 0.5f);
            }

            // 关闭PdfStamper
            stamper.close();
            reader.close();

            System.out.println("Watermark added successfully!");
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加文字水印
     *
     * @param content  PDF内容
     * @param text     水印文字
     * @param font     字体
     * @param fontSize 字体大小
     * @param rotation 旋转角度
     * @param opacity  透明度 (0.0 - 1.0)
     */
    private static void addTextWatermark(PdfContentByte content, String text, BaseFont font, float fontSize, float rotation, float opacity) {
        content.saveState();
        PdfGState gState = new PdfGState();
        gState.setFillOpacity(opacity); // 设置透明度
        content.setGState(gState);

        content.beginText();
        content.setFontAndSize(font, fontSize);
        content.setColorFill(BaseColor.LIGHT_GRAY);

        // 设置水印位置和旋转角度
        content.showTextAligned(PdfContentByte.ALIGN_CENTER, text, 300, 400, rotation);

        content.endText();
        content.restoreState();
    }

    /**
     * 添加图片水印
     *
     * @param content   PDF内容
     * @param imagePath 图片路径
     * @param x         水印起始x坐标
     * @param y         水印起始y坐标
     * @param rotation  旋转角度
     * @param opacity   透明度 (0.0 - 1.0)
     */
    private static void addImageWatermark(PdfContentByte content, String imagePath, float x, float y, float rotation, float opacity) {
        try {
            content.saveState();
            PdfGState gState = new PdfGState();
            gState.setFillOpacity(opacity); // 设置透明度
            content.setGState(gState);

            // 加载图片
            Image image = Image.getInstance(imagePath);

            // 设置图片位置和旋转角度
            image.setAbsolutePosition(x, y);
            image.setRotationDegrees(rotation);

            // 添加图片到PDF
            content.addImage(image);
            content.restoreState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}