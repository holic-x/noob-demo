package com.noob.base.batchDataHandler.docHandle.otherTest;

import com.noob.base.batchDataHandler.docHandle.v4_optimize_batchAndGM.DocxMerger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocxMergerTest {

    private File tempDir;

    @Before
    public void setUp() throws Exception {
        tempDir = Files.createTempDirectory("docxmerge_test").toFile();
    }

    @After
    public void tearDown() {
        if (tempDir != null && tempDir.exists()) {
            for (File f : tempDir.listFiles()) {
                f.delete();
            }
            tempDir.delete();
        }
    }

    @Test
    public void testMergeHandler_normal() throws Exception {
        // 创建两个简单docx
        File f1 = new File(tempDir, "test_1.docx");
        File f2 = new File(tempDir, "test_2.docx");
        createSimpleDocx(f1, "内容1");
        createSimpleDocx(f2, "内容2");

        DocxMerger.mergeHandler(tempDir.getAbsolutePath());

        // 检查合并文件
        File[] files = tempDir.listFiles();
        boolean foundMerged = false;
        for (File f : files) {
            if (f.getName().startsWith("merged_") && f.getName().endsWith(".docx")) {
                foundMerged = true;
                // 检查合并内容
                try (FileInputStream in = new FileInputStream(f);
                     XWPFDocument doc = new XWPFDocument(in)) {
                    List<XWPFParagraph> paras = doc.getParagraphs();
                    boolean hasToc = false, has1 = false, has2 = false;
                    for (XWPFParagraph p : paras) {
                        String text = p.getText();
                        if (text.contains("文档目录")) hasToc = true;
                        if (text.contains("内容1")) has1 = true;
                        if (text.contains("内容2")) has2 = true;
                    }
                    assertTrue(hasToc && has1 && has2);
                }
            }
        }
        assertTrue(foundMerged);
    }

    @Test
    public void testMergeHandler_emptyDir() throws Exception {
        DocxMerger.mergeHandler(tempDir.getAbsolutePath());
        // 只生成目录页
        File[] files = tempDir.listFiles();
        boolean foundMerged = false;
        for (File f : files) {
            if (f.getName().startsWith("merged_") && f.getName().endsWith(".docx")) {
                foundMerged = true;
                try (FileInputStream in = new FileInputStream(f);
                     XWPFDocument doc = new XWPFDocument(in)) {
                    List<XWPFParagraph> paras = doc.getParagraphs();
                    boolean hasToc = false;
                    for (XWPFParagraph p : paras) {
                        if (p.getText().contains("文档目录")) hasToc = true;
                    }
                    assertTrue(hasToc);
                }
            }
        }
        assertTrue(foundMerged);
    }

    @Test
    public void testGetSortedDocxFiles_sorting() throws Exception {
        File f1 = new File(tempDir, "test_10.docx");
        File f2 = new File(tempDir, "test_2.docx");
        createSimpleDocx(f1, "A");
        createSimpleDocx(f2, "B");
        List<File> sorted = invokeGetSortedDocxFiles(tempDir.toPath());
        assertEquals("test_2.docx", sorted.get(0).getName());
        assertEquals("test_10.docx", sorted.get(1).getName());
    }

    @Test
    public void testMergeHandler_withTableAndPicture() throws Exception {
        File f1 = new File(tempDir, "table.docx");
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFTable table = doc.createTable(1, 1);
            table.getRow(0).getCell(0).setText("表格单元格");
            try (FileOutputStream out = new FileOutputStream(f1)) {
                doc.write(out);
            }
        }
        DocxMerger.mergeHandler(tempDir.getAbsolutePath());
        // 检查合并文件存在
        File[] files = tempDir.listFiles();
        boolean foundMerged = false;
        for (File f : files) {
            if (f.getName().startsWith("merged_") && f.getName().endsWith(".docx")) {
                foundMerged = true;
            }
        }
        assertTrue(foundMerged);
    }

    @Test
    public void testCalculatePictureSize() throws Exception {
        // 直接测试私有方法
        double[] size = invokeCalculatePictureSize(100, 100);
        assertTrue(size[0] > 0 && size[1] > 0);
    }

    // 辅助方法
    private void createSimpleDocx(File file, String text) throws Exception {
        try (XWPFDocument doc = new XWPFDocument()) {
            XWPFParagraph para = doc.createParagraph();
            para.createRun().setText(text);
            try (FileOutputStream out = new FileOutputStream(file)) {
                doc.write(out);
            }
        }
    }

    // 反射调用私有方法
    private List<File> invokeGetSortedDocxFiles(Path dirPath) throws Exception {
        java.lang.reflect.Method m = DocxMerger.class.getDeclaredMethod("getSortedDocxFiles", Path.class);
        m.setAccessible(true);
        return (List<File>) m.invoke(null, dirPath);
    }

    private double[] invokeCalculatePictureSize(double w, double h) throws Exception {
        java.lang.reflect.Method m = DocxMerger.class.getDeclaredMethod("calculatePictureSize", double.class, double.class);
        m.setAccessible(true);
        return (double[]) m.invoke(null, w, h);
    }
}