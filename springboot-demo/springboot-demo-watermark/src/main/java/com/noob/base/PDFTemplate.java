package com.noob.base;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 模板 PDF
 */
public class PDFTemplate {


    // 创建PDF模板
    public static void main(String[] args) throws Exception {
        // 1.创建PDF对象并打开它
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\output1.pdf")); // 指定文件路径和名称
        document.open();


        // 2.设置PDF字体样式
        // BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//         Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
        // font.setBaseFont(baseFont); 可自定义FONT字体样式（例如从windows本机中获取）
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD,15);

        // 3.添加文本内容
//        Paragraph paragraph = new Paragraph("这是一段黑体字体的文本。", font); // 对于中文字体需要选择使用匹配的字体文件(需创建一个支持中文的字体)，否则中文无法正常渲染
        Paragraph paragraph = new Paragraph("hello world。", font);
        document.add(paragraph);

        // 4.保存PDF文件（关闭资源）
        document.close();
    }

}
