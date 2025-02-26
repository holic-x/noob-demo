package com.noob.base.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PdfBoxWatermarkForPdfUtil {

    /**
     * 添加文字水印
     *
     * @param document      PDF文档
     * @param page          当前页
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
    public static void addTextWatermark(PDDocument document, PDPage page, String text, PDType1Font font, float fontSize, Color color,
                                        float rotation, float opacity, boolean isTiled, float horizontalGap, float verticalGap) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
            // 设置字体和字号
            contentStream.setFont(font, fontSize);

            // 设置水印颜色
            contentStream.setNonStrokingColor(color);

            // 获取页面尺寸
            PDRectangle pageSize = page.getMediaBox();
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            // 计算水印文字的大小
            float textWidth = font.getStringWidth(text) / 1000 * fontSize;
            float textHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

            // 设置透明度
            PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
            graphicsState.setNonStrokingAlphaConstant(opacity);
            contentStream.setGraphicsStateParameters(graphicsState);

            if (isTiled) {
                // 平铺水印
                for (float x = 0; x < pageWidth; x += textWidth + horizontalGap) {
                    for (float y = 0; y < pageHeight; y += textHeight + verticalGap) {
                        addSingleTextWatermark(contentStream, text, x, y, rotation);
                    }
                }
            } else {
                // 单水印（居中）
                float x = (pageWidth - textWidth) / 2;
                float y = (pageHeight - textHeight) / 2;
                addSingleTextWatermark(contentStream, text, x, y, rotation);
            }
        }
    }

    /**
     * 添加单个文字水印
     */
    private static void addSingleTextWatermark(PDPageContentStream contentStream, String text, float x, float y, float rotation) throws IOException {
        contentStream.saveGraphicsState();
        Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotation), x, y);
        contentStream.transform(matrix);

        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();

        contentStream.restoreGraphicsState();
    }

    /**
     * 添加图片水印
     *
     * @param document      PDF文档
     * @param page          当前页
     * @param imagePath     图片路径
     * @param rotation      旋转角度
     * @param opacity       透明度 (0.0 - 1.0)
     * @param isTiled       是否平铺水印
     * @param horizontalGap 水平间距（平铺时使用）
     * @param verticalGap   垂直间距（平铺时使用）
     */
    public static void addImageWatermark(PDDocument document, PDPage page, String imagePath, float rotation, float opacity,
                                         boolean isTiled, float horizontalGap, float verticalGap) throws IOException {
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
            // 加载图片
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);

            // 获取页面尺寸
            PDRectangle pageSize = page.getMediaBox();
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            // 设置透明度
            PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
            graphicsState.setNonStrokingAlphaConstant(opacity);
            contentStream.setGraphicsStateParameters(graphicsState);

            if (isTiled) {
                // 平铺水印
                for (float x = 0; x < pageWidth; x += image.getWidth() + horizontalGap) {
                    for (float y = 0; y < pageHeight; y += image.getHeight() + verticalGap) {
                        addSingleImageWatermark(contentStream, image, x, y, rotation);
                    }
                }
            } else {
                // 单水印（居中）
                float x = (pageWidth - image.getWidth()) / 2;
                float y = (pageHeight - image.getHeight()) / 2;
                addSingleImageWatermark(contentStream, image, x, y, rotation);
            }
        }
    }

    /**
     * 添加单个图片水印
     */
    private static void addSingleImageWatermark(PDPageContentStream contentStream, PDImageXObject image, float x, float y, float rotation) throws IOException {
        contentStream.saveGraphicsState();
        Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotation), x + image.getWidth() / 2, y + image.getHeight() / 2);
        contentStream.transform(matrix);

        contentStream.drawImage(image, x, y, image.getWidth(), image.getHeight());

        contentStream.restoreGraphicsState();
    }

    /**
     * 为整个PDF文档添加水印
     *
     * @param inputPdfPath  输入PDF路径
     * @param outputPdfPath 输出PDF路径
     * @param watermarkText 文字水印内容（如果为null，则不添加文字水印）
     * @param imagePath     图片水印路径（如果为null，则不添加图片水印）
     * @param font          字体
     * @param fontSize      字体大小
     * @param color         颜色
     * @param rotation      旋转角度
     * @param opacity       透明度 (0.0 - 1.0)
     * @param isTiled       是否平铺水印
     * @param horizontalGap 水平间距（平铺时使用）
     * @param verticalGap   垂直间距（平铺时使用）
     */
    public static void addWatermarkToPdf(String inputPdfPath, String outputPdfPath, String watermarkText, String imagePath,
                                         PDType1Font font, float fontSize, Color color, float rotation, float opacity,
                                         boolean isTiled, float horizontalGap, float verticalGap) throws IOException {
        try (PDDocument document = PDDocument.load(new File(inputPdfPath))) {
            for (PDPage page : document.getDocumentCatalog().getPages()) {
                if (watermarkText != null) {
                    addTextWatermark(document, page, watermarkText, font, fontSize, color, rotation, opacity, isTiled, horizontalGap, verticalGap);
                }
                if (imagePath != null) {
                    addImageWatermark(document, page, imagePath, rotation, opacity, isTiled, horizontalGap, verticalGap);
                }
            }

            // 保存PDF
            document.save(outputPdfPath);
            System.out.println("Watermark added successfully!");
        }
    }

    public static void main(String[] args) {
        String sourcePath = "D:\\Desktop\\test\\watermark\\";
        String targetPath = "D:\\Desktop\\test\\watermark\\target\\";

        try {
            // 示例：添加平铺文字水印
            addWatermarkToPdf(sourcePath + "input.pdf", targetPath + "output_tiled_text.pdf", "CONFIDENTIAL", null,
                    PDType1Font.HELVETICA_BOLD, 45, Color.LIGHT_GRAY, 45, 0.3f, true, 0, 0);

            // 示例：添加平铺图片水印
            addWatermarkToPdf(sourcePath + "input.pdf", targetPath + "output_tiled_image.pdf", null, sourcePath + "logo.png",
                    null, 0, null, 45, 0.3f, true, 25, 25);

            // 示例：添加单文字水印
            addWatermarkToPdf(sourcePath + "input.pdf", targetPath + "output_single_text.pdf", "SAMPLE", null,
                    PDType1Font.HELVETICA_BOLD, 72, Color.RED, 30, 0.5f, false, 0, 0);

            // 示例：添加单图片水印
            addWatermarkToPdf(sourcePath + "input.pdf", targetPath + "output_single_image.pdf", null, sourcePath + "logo.png",
                    null, 0, null, 0, 0.5f, false, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}