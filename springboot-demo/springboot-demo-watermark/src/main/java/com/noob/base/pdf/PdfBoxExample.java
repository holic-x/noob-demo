

package com.noob.base.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * 基于pdfbox生成pdf文档
 */
public class PdfBoxExample {
    public static void main(String[] args) {
        // 创建PDF文档
        try (PDDocument document = new PDDocument()) {
            // 创建新页面
            PDPage page = new PDPage();
            document.addPage(page);

            // 创建内容流
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // 设置字体和字号
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                // 开始文本内容
                contentStream.beginText();

                // 设置文本位置
                contentStream.newLineAtOffset(100, 700);

                // 添加文本
                contentStream.showText("Hello, this is a PDF created using Apache PDFBox!");
                contentStream.showText("This is a second line of text.");

                // 结束文本内容
                contentStream.endText();
            }

            // 保存PDF文件
            // document.save("example.pdf");
            document.save("D:\\Desktop\\test\\exam_pdfbox.pdf");
            System.out.println("PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}