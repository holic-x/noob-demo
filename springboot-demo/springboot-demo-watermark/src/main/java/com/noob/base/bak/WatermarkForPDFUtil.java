package com.noob.base.bak;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;

public class WatermarkForPDFUtil {

    public static void main(String[] args) throws IOException {
        String pdfFile = "D:\\Desktop\\test\\tmp.pdf"; // 输入的PDF文件路径
        String outputFile = "D:\\Desktop\\test\\output.pdf"; // 输出的PDF文件路径
        String watermarkText = "hello world"; // 水印文字

        try (PDDocument document = PDDocument.load(new File(pdfFile))) {
            document.addPage(new PDPage()); // 添加一个空白页以便应用水印

            for (PDPage page : document.getPages()) {
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 40);
                // 设置水印文字的颜色和透明度
                contentStream.setNonStrokingColor(200, 200, 200);
                // 设置水印文字旋转45度
                contentStream.setTextMatrix(Matrix.getRotateInstance(Math.toRadians(45), 200, 200));
                // 水印位置设置在页面中心
                contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 100, page.getMediaBox().getHeight() / 2 + 50);
                contentStream.showText(watermarkText);
                contentStream.endText();
                contentStream.close();
            }

            document.save(outputFile); // 保存修改后的PDF
        }
    }

}
