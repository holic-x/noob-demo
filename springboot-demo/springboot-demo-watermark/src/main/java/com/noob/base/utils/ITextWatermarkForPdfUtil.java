package com.noob.base.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基于itext为pdf生成水印
 */
public class ITextWatermarkForPdfUtil {

    /**
     * 添加文字水印
     *
     * @param inputPdfPath  输入PDF路径
     * @param outputPdfPath 输出PDF路径
     * @param text          水印文字
     * @param font          字体
     * @param fontSize      字体大小
     * @param color         颜色
     * @param rotation      旋转角度
     * @param opacity       透明度 (0.0 - 1.0)
     * @param isTiled       是否平铺水印
     * @param horizontalGap 水平间距（平铺时使用）
     * @param verticalGap   垂直间距（平铺时使用）
     */
    public static void addTextWatermark(String inputPdfPath, String outputPdfPath, String text, BaseFont font, float fontSize,
                                        BaseColor color, float rotation, float opacity, boolean isTiled, float horizontalGap, float verticalGap)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(inputPdfPath);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPdfPath));

        // 获取PDF的总页数
        int totalPages = reader.getNumberOfPages();

        // 遍历每一页
        for (int i = 1; i <= totalPages; i++) {
            PdfContentByte content = stamper.getUnderContent(i); // 在内容下方添加水印
            PdfGState gState = new PdfGState();
            gState.setFillOpacity(opacity); // 设置透明度
            content.setGState(gState);

            // 获取页面尺寸
            PdfDictionary pageDict = reader.getPageN(i);
            PdfArray mediaBox = pageDict.getAsArray(PdfName.MEDIABOX);
            float pageWidth = mediaBox.getAsNumber(2).floatValue();
            float pageHeight = mediaBox.getAsNumber(3).floatValue();

            if (isTiled) {
                // 平铺水印
                float textWidth = font.getWidthPoint(text, fontSize);
                float textHeight = font.getAscentPoint(text, fontSize) - font.getDescentPoint(text, fontSize);

                for (float x = 0; x < pageWidth; x += textWidth + horizontalGap) {
                    for (float y = 0; y < pageHeight; y += textHeight + verticalGap) {
                        addSingleTextWatermark(content, text, font, fontSize, color, rotation, x, y);
                    }
                }
            } else {
                // 单水印（居中）
                float x = (pageWidth - font.getWidthPoint(text, fontSize)) / 2;
                float y = (pageHeight - font.getAscentPoint(text, fontSize)) / 2;
                addSingleTextWatermark(content, text, font, fontSize, color, rotation, x, y);
            }
        }

        stamper.close();
        reader.close();
    }

    /**
     * 添加单个文字水印
     */
    private static void addSingleTextWatermark(PdfContentByte content, String text, BaseFont font, float fontSize,
                                               BaseColor color, float rotation, float x, float y) {
        content.saveState();
        content.beginText();
        content.setFontAndSize(font, fontSize);
        content.setColorFill(color);

        // 旋转水印
        content.showTextAligned(PdfContentByte.ALIGN_CENTER, text, x, y, rotation);

        content.endText();
        content.restoreState();
    }

    /**
     * 添加图片水印
     *
     * @param inputPdfPath  输入PDF路径
     * @param outputPdfPath 输出PDF路径
     * @param imagePath     图片路径
     * @param rotation      旋转角度
     * @param opacity       透明度 (0.0 - 1.0)
     * @param isTiled       是否平铺水印
     * @param horizontalGap 水平间距（平铺时使用）
     * @param verticalGap   垂直间距（平铺时使用）
     */
    public static void addImageWatermark(String inputPdfPath, String outputPdfPath, String imagePath, float rotation,
                                         float opacity, boolean isTiled, float horizontalGap, float verticalGap)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(inputPdfPath);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPdfPath));

        // 加载图片
        Image image = Image.getInstance(imagePath);

        // 获取PDF的总页数
        int totalPages = reader.getNumberOfPages();

        // 遍历每一页
        for (int i = 1; i <= totalPages; i++) {
            PdfContentByte content = stamper.getUnderContent(i); // 在内容下方添加水印
            PdfGState gState = new PdfGState();
            gState.setFillOpacity(opacity); // 设置透明度
            content.setGState(gState);

            // 获取页面尺寸
            PdfDictionary pageDict = reader.getPageN(i);
            PdfArray mediaBox = pageDict.getAsArray(PdfName.MEDIABOX);
            float pageWidth = mediaBox.getAsNumber(2).floatValue();
            float pageHeight = mediaBox.getAsNumber(3).floatValue();

            if (isTiled) {
                // 平铺水印
                for (float x = 0; x < pageWidth; x += image.getWidth() + horizontalGap) {
                    for (float y = 0; y < pageHeight; y += image.getHeight() + verticalGap) {
                        addSingleImageWatermark(content, image, rotation, x, y);
                    }
                }
            } else {
                // 单水印（居中）
                float x = (pageWidth - image.getWidth()) / 2;
                float y = (pageHeight - image.getHeight()) / 2;
                addSingleImageWatermark(content, image, rotation, x, y);
            }
        }

        stamper.close();
        reader.close();
    }

    /**
     * 添加单个图片水印
     */
    private static void addSingleImageWatermark(PdfContentByte content, Image image, float rotation, float x, float y) throws DocumentException {
        content.saveState();
        content.addImage(image, image.getWidth(), 0, 0, image.getHeight(), x, y);
        content.restoreState();
    }

    public static void main(String[] args) {
        String sourcePath = "D:\\Desktop\\test\\watermark\\";
        String targetPath = "D:\\Desktop\\test\\watermark\\target\\";

        try {
            // 示例：添加平铺文字水印
            addTextWatermark(sourcePath + "input.pdf", targetPath + "output_tiled_text.pdf", "CONFIDENTIAL",
                    BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED),
                    52, BaseColor.LIGHT_GRAY, 45, 0.3f, true, 100, 100);

            // 示例：添加平铺图片水印
            addImageWatermark(sourcePath + "input.pdf", targetPath + "output_tiled_image.pdf", sourcePath + "logo.png",
                    45, 0.3f, true, 150, 150);

            // 示例：添加单文字水印
            addTextWatermark(sourcePath + "input.pdf", targetPath + "output_single_text.pdf", "SAMPLE",
                    BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED),
                    72, BaseColor.RED, 30, 0.5f, false, 0, 0);

            // 示例：添加单图片水印
            addImageWatermark(sourcePath + "input.pdf", targetPath + "output_single_image.pdf", sourcePath + "logo.png",
                    0, 0.5f, false, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}