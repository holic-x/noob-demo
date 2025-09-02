package com.noob.base.batchDataHandler.docHandle.skill;

import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.*;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 基于JDK1.8和docx4j 8.2.1的批量图片文档生成器
 * 适配8.x版本API，确保依赖可获取
 */
public class BatchImageDocGenerator {
//
//
//    // 配置参数（根据实际情况修改）
//    private static final int TOTAL_IMAGES = 50000;          // 总图片数量
//    private static final int BATCH_SIZE = 50;               // 每批处理数量
//    private static final String IMAGE_DIR = "images/";      // 图片存放目录
//    private static final String OUTPUT_FILE = "output.docx";// 输出文件路径
//
//    private WordprocessingMLPackage docPackage;
//    private ObjectFactory factory;
//
//    public static void main(String[] args) {
//        try {
//            // 确保图片目录和输出目录存在
//            Files.createDirectories(Paths.get(IMAGE_DIR));
//
//            BatchImageDocGenerator generator = new BatchImageDocGenerator();
//            generator.init();
//
//            long startTime = System.currentTimeMillis();
//            generator.generateDocument();
//            long endTime = System.currentTimeMillis();
//
//            System.out.println("文档生成完成，耗时: " + (endTime - startTime) / 1000 + "秒");
//            System.out.println("输出文件: " + new File(OUTPUT_FILE).getAbsolutePath());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 初始化文档对象
//     */
//    public void init() throws Exception {
//        // 8.x版本创建文档的标准方式
//        docPackage = WordprocessingMLPackage.createPackage();
//        factory = Context.getWmlObjectFactory();
//    }
//
//    /**
//     * 生成包含所有图片的文档
//     */
//    public void generateDocument() throws Exception {
//        int totalBatches = (int) Math.ceil((double) TOTAL_IMAGES / BATCH_SIZE);
//
//        for (int batch = 0; batch < totalBatches; batch++) {
//            int start = batch * BATCH_SIZE;
//            int end = Math.min(start + BATCH_SIZE, TOTAL_IMAGES);
//
//            System.out.println("处理批次 " + (batch + 1) + "/" + totalBatches +
//                    " (" + start + "-" + (end - 1) + ")");
//
//            processBatch(start, end);
//
//            // 每处理5个批次保存一次，释放内存
//            if ((batch + 1) % 5 == 0) {
//                saveDocument();
//                cleanupMemory();
//            }
//        }
//
//        // 最终保存
//        saveDocument();
//    }
//
//    /**
//     * 处理单个批次的图片
//     */
//    private void processBatch(int start, int end) throws Exception {
//        for (int i = start; i < end; i++) {
//            String imageName = "image_" + i + ".png";
//            File imageFile = new File(IMAGE_DIR + imageName);
//
//            // 添加标题段落
//            addParagraph("截图 " + (i + 1) + "：图片描述", true);
//
//            // 添加图片或占位符
//            if (imageFile.exists() && imageFile.length() > 0) {
//                addImage(imageFile);
//            } else {
//                addParagraph("[图片缺失: " + imageName + "]", false);
//            }
//
//            // 添加空行分隔
//            addParagraph("", false);
//        }
//    }
//
//    /**
//     * 添加段落（支持标题和普通文本）
//     */
//    private void addParagraph(String text, boolean isTitle) {
//        P paragraph = factory.createP();
//        R run = factory.createR();
//        Text textElement = factory.createText();
//
//        textElement.setValue(text);
//        run.getContent().add(textElement);
//        paragraph.getContent().add(run);
//
//        // 设置标题样式
//        if (isTitle) {
//            PPr pPr = factory.createPPr();
//            PPrBase.PStyle style = factory.createPStyle();
//            style.setVal("Heading2");
//            pPr.setPStyle(style);
//            paragraph.setPPr(pPr);
//        }
//
//        docPackage.getMainDocumentPart().getContent().add(paragraph);
//    }
//
//    /**
//     * 添加图片到文档（适配8.2.1版本API）
//     */
//    private void addImage(File imageFile) throws Exception {
//        // 读取图片文件
//        byte[] imageBytes = new byte[(int) imageFile.length()];
//        try (FileInputStream fis = new FileInputStream(imageFile)) {
//            fis.read(imageBytes);
//        }
//
//        // 8.x版本创建图片部分的方式
//        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
//                .createImagePart(docPackage, imageBytes);
//
//        // 创建图片元素（8.x版本参数兼容）
//        Inline inline = imagePart.createImageInline(
//                imageFile.getName(),
//                "图片描述",
//                BigInteger.valueOf(1),  // 图片ID
//                BigInteger.valueOf(1),  // 图片索引
//                false                  // 不浮于文字上方
//        );
//
//        // 创建包含图片的段落
//        P imagePara = factory.createP();
//        R imageRun = factory.createR();
//        Drawing drawing = factory.createDrawing();
//
//        drawing.getAnchorOrInline().add(inline);
//        imageRun.getContent().add(drawing);
//        imagePara.getContent().add(imageRun);
//
//        docPackage.getMainDocumentPart().getContent().add(imagePara);
//    }
//
//    /**
//     * 保存文档到磁盘
//     */
//    private void saveDocument() throws Exception {
//        try (FileOutputStream fos = new FileOutputStream(OUTPUT_FILE)) {
//            Docx4J.write(docPackage, fos);
//        }
//    }
//
//    /**
//     * 清理内存，防止溢出
//     */
//    private void cleanupMemory() {
//        // 清除已添加内容的引用
//        docPackage.getMainDocumentPart().getContent().clear();
//
//        // 提示垃圾回收
//        System.gc();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
}
    