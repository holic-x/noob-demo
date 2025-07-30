package com.noob.base.batchDataHandler.docHandle;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class DocxUtilTest {

    private XWPFDocument doc;

    @Before
    public void setUp() {
        doc = new XWPFDocument();
    }

    @After
    public void tearDown() throws Exception {
        doc.close();
    }

    @Test
    public void testAddHorizontalLine() {
        int paraCountBefore = doc.getParagraphs().size();
        DocxUtil.addHorizontalLine(doc);
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        assertEquals(paraCountBefore + 2, paragraphs.size());
        // 检查分隔线段落
        assertEquals(org.apache.poi.xwpf.usermodel.Borders.SINGLE, paragraphs.get(paragraphs.size() - 2).getBorderBottom());
    }

    @Test
    public void testGetPictureTypeAllSupported() throws Exception {
        Method m = DocxUtil.class.getDeclaredMethod("getPictureType", String.class);
        m.setAccessible(true);
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_EMF, m.invoke(null, "a.emf"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_WMF, m.invoke(null, "a.wmf"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_PICT, m.invoke(null, "a.pict"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_JPEG, m.invoke(null, "a.jpeg"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_JPEG, m.invoke(null, "a.JPG"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_PNG, m.invoke(null, "a.png"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_DIB, m.invoke(null, "a.dib"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_GIF, m.invoke(null, "a.gif"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_TIFF, m.invoke(null, "a.tiff"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_EPS, m.invoke(null, "a.eps"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_BMP, m.invoke(null, "a.bmp"));
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_WPG, m.invoke(null, "a.wpg"));
        // 默认
        assertEquals(org.apache.poi.xwpf.usermodel.XWPFDocument.PICTURE_TYPE_PNG, m.invoke(null, "a.unknown"));
    }

    @Test
    public void testInsertPicturePng() throws Exception {
        BufferedImage img = createTestImage(100, 50, Color.RED);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        DocxUtil.insertPicture(doc, is, "test.png", 60, 30);
        // 检查文档段落数增加
        assertTrue(doc.getParagraphs().size() > 0);
    }

    @Test
    public void testInsertPictureJpg() throws Exception {
        BufferedImage img = createTestImage(200, 100, Color.BLUE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "jpg", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        DocxUtil.insertPicture(doc, is, "test.jpg", 60, 30);
        assertTrue(doc.getParagraphs().size() > 0);
    }

    @Test
    public void testInsertPictureGif() throws Exception {
        BufferedImage img = createTestImage(50, 50, Color.GREEN);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "gif", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        DocxUtil.insertPicture(doc, is, "test.gif", 60, 30);
        assertTrue(doc.getParagraphs().size() > 0);
    }

    @Test
    public void testInsertPictureBmp() throws Exception {
        BufferedImage img = createTestImage(30, 30, Color.YELLOW);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "bmp", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        DocxUtil.insertPicture(doc, is, "test.bmp", 60, 30);
        assertTrue(doc.getParagraphs().size() > 0);
    }

    @Test
    public void testInsertPictureImageNull() throws Exception {
        // 非图片流
        InputStream is = new ByteArrayInputStream("not an image".getBytes("UTF-8"));
        int paraCountBefore = doc.getParagraphs().size();
        DocxUtil.insertPicture(doc, is, "bad.png", 60, 30);
        // 应该插入红色错误提示段落
        assertTrue(doc.getParagraphs().size() > paraCountBefore);
        XWPFParagraph lastPara = doc.getParagraphs().get(doc.getParagraphs().size() - 1);
        assertTrue(lastPara.getText().contains("图片无法识别或格式不支持"));
    }

    @Test
    public void testInsertPictureAddPictureException() throws Exception {
        // 用非法图片类型触发addPicture异常
        BufferedImage img = createTestImage(10, 10, Color.BLACK);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        // 用非法文件名后缀，模拟异常
        DocxUtil.insertPicture(doc, is, "test.unsupported", 60, 30);
        // 检查文档段落数增加
        assertTrue(doc.getParagraphs().size() > 0);
    }

    @Test
    public void testInsertPictureEdgeCaseLargeImage() throws Exception {
        BufferedImage img = createTestImage(2000, 1000, Color.MAGENTA);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "png", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        DocxUtil.insertPicture(doc, is, "large.png", 100, 50);
        // 检查文档段落数增加
        assertTrue(doc.getParagraphs().size() > 0);
    }

    private BufferedImage createTestImage(int w, int h, Color color) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return img;
    }
}