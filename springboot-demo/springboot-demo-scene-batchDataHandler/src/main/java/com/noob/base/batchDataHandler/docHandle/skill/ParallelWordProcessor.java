package com.noob.base.batchDataHandler.docHandle.skill;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import static org.apache.poi.util.Units.toEMU;

/**
 * POI 4.1.0 合并拆分思路处理
 */
public class ParallelWordProcessor {

    // 配置参数
    private static final int TOTAL_IMAGES = 50000;
    private static final int SLICE_SIZE = 500;
    private static final int THREAD_COUNT = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
    private static final String IMAGE_SUFFIX = ".png";
    private static final int PROGRESS_INTERVAL = 250;

    // 目录配置
    private final String BASE_DIR = "E:" + File.separator + "test" + File.separator + "test-files" + File.separator + "batch_test";
    private final String IMAGE_DIR = BASE_DIR + File.separator + "batch_images";
    private final String SLICE_DIR = BASE_DIR + File.separator + "slices";
    private final String OUTPUT_DIR = BASE_DIR + File.separator + "output";
    private final String LOG_FILE = BASE_DIR + File.separator + "processing_log.txt";

    // 日志记录器
    private final Logger logger;

    public ParallelWordProcessor() throws FileNotFoundException {
        this.logger = new Logger(LOG_FILE);
    }

    public static void main(String[] args) throws Exception {
        ParallelWordProcessor processor = new ParallelWordProcessor();
        processor.initializeDirectories();

        long start = System.currentTimeMillis();
        try {
            List<String> slicePaths = processor.generateSlices();
            String finalDocPath = processor.mergeSlices(slicePaths);
            processor.cleanupSlices(slicePaths);

            System.out.println("最终文档生成完成：" + finalDocPath);
            System.out.println("总耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
            processor.logger.log("处理完成，总耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
        } catch (Exception e) {
            processor.logger.log("处理失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            processor.logger.close();
        }
    }

    private void initializeDirectories() {
        createDirectory(IMAGE_DIR);
        createDirectory(SLICE_DIR);
        createDirectory(OUTPUT_DIR);
    }

    private void createDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists() && !dir.mkdirs()) {
            logger.log("警告：无法创建目录 " + path);
        }
    }

    private List<String> generateSlices() throws Exception {
        logger.log("开始生成子文档，总图片数：" + TOTAL_IMAGES + "，分片大小：" + SLICE_SIZE);

        List<String> slicePaths = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(50), new ThreadFactory() {
            private int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "slice-generator-" + counter++);
                thread.setDaemon(false);
                return thread;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
            int totalSlices = (TOTAL_IMAGES + SLICE_SIZE - 1) / SLICE_SIZE;
            logger.log("总分片数：" + totalSlices + "，并行线程数：" + THREAD_COUNT);

            for (int i = 0; i < totalSlices; i++) {
                final int sliceIndex = i;
                completionService.submit(() -> generateSingleSlice(sliceIndex));
            }

            for (int i = 0; i < totalSlices; i++) {
                Future<String> future = completionService.take();
                slicePaths.add(future.get());
            }

            logger.log("所有子文档生成完成，共" + slicePaths.size() + "个");
            return slicePaths;
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        }
    }

    private String generateSingleSlice(int sliceIndex) throws Exception {
        long sliceStart = System.currentTimeMillis();
        int start = sliceIndex * SLICE_SIZE;
        int end = Math.min((sliceIndex + 1) * SLICE_SIZE, TOTAL_IMAGES);
        String threadName = Thread.currentThread().getName();

        logger.log("线程" + threadName + "开始处理分片" + sliceIndex + "，图片范围：" + start + "-" + (end - 1));

        XWPFDocument doc = new XWPFDocument();
        int successCount = 0;
        int failCount = 0;

        try {
            for (int i = start; i < end; i++) {
                String imageId = "mock_image_" + i + IMAGE_SUFFIX;
                try {
                    byte[] imageData = readImageData(imageId);
                    XWPFParagraph paragraph = doc.createParagraph();
                    setParagraphStyle(paragraph);
                    XWPFRun run = paragraph.createRun();
                    insertImageToRun(doc, run, imageData, imageId);

                    successCount++;

                    if ((i - start + 1) % PROGRESS_INTERVAL == 0) {
                        logger.log("分片" + sliceIndex + "已处理" + (i - start + 1) + "/" + (end - start) + "张图片");
                    }
                } catch (Exception e) {
                    failCount++;
                    logger.log("分片" + sliceIndex + "处理图片" + imageId + "失败：" + e.getMessage());
                }
            }

            repairDocumentRelationsWithSliceId(doc, sliceIndex);

            String slicePath = SLICE_DIR + File.separator + "slice_" + sliceIndex + "_" + UUID.randomUUID().toString().substring(0, 8) + ".docx";
            try (FileOutputStream out = new FileOutputStream(slicePath)) {
                doc.write(out);
            }

            long sliceTime = (System.currentTimeMillis() - sliceStart) / 1000;
            logger.log("线程" + threadName + "完成分片" + sliceIndex + "，成功：" + successCount + "，失败：" + failCount + "，耗时：" + sliceTime + "秒");

            return slicePath;
        } finally {
            doc.close();
        }
    }

    private byte[] readImageData(String imageId) throws IOException {
        String imagePath = IMAGE_DIR + File.separator + imageId;
        File imageFile = new File(imagePath);

        if (!imageFile.exists() || !imageFile.isFile()) {
            throw new FileNotFoundException("图片文件不存在：" + imagePath);
        }

        return Files.readAllBytes(Paths.get(imagePath));
    }

    private void setParagraphStyle(XWPFParagraph paragraph) {
        paragraph.setSpacingAfter(100);
        paragraph.setSpacingBefore(0);

        // 修复：POI 4.1.0 中CTJc的setVal参数为STJc类型
        CTP ctp = paragraph.getCTP();
        CTPPr pPr = ctp.getPPr() == null ? ctp.addNewPPr() : ctp.getPPr();
        CTJc jc = pPr.getJc() == null ? pPr.addNewJc() : pPr.getJc();
        jc.setVal(STJc.CENTER); // 使用枚举类型而非字符串
    }

    /**
     * 修复1：移除int强制转换，POI 4.1.0中addPictureData返回String类型的rId
     */
    private void insertImageToRun(XWPFDocument doc, XWPFRun run, byte[] imageData, String imageId) throws Exception {
        // POI 4.1.0中addPictureData返回的是String类型的关系ID，不是int
        String rId = doc.addPictureData(new ByteArrayInputStream(imageData), XWPFDocument.PICTURE_TYPE_PNG);

        // 验证rId格式
        if (rId == null || !rId.matches("rId\\d+")) {
            throw new IllegalArgumentException("无效的rId格式：" + rId + "，图片：" + imageId);
        }

        // 插入图片（POI 4.1.0兼容的参数顺序）
        run.addPicture(new ByteArrayInputStream(imageData), XWPFDocument.PICTURE_TYPE_PNG, imageId, toEMU(500), toEMU(300));
    }

    /**
     * 修复2：处理OPCPackage可能为null的情况，兼容POI 4.1.0
     */
    /*
    private void repairDocumentRelationsWithSliceId(XWPFDocument doc, int sliceIndex) {
        try {
            // 修复：POI 4.1.0 中通过 XWPFDocument 获取主文档部分
            XWPFMainDocumentPart mainPart = doc.getMainDocumentPart();
            if (mainPart == null) {
                logger.log("分片" + sliceIndex + "的主文档部分为空，无法修复rId");
                return;
            }

            // 获取所有关系
            List<PackageRelationship> originalRels = new ArrayList<>(mainPart.getRelationships());

            // 移除旧关系
            for (PackageRelationship rel : originalRels) {
                mainPart.removeRelationship(rel.getId());
            }

            // 添加新关系（带分片索引的rId）
            for (int i = 0; i < originalRels.size(); i++) {
                PackageRelationship rel = originalRels.get(i);
                String newRId = "rId_" + sliceIndex + "_" + (i + 1);

                mainPart.addRelationship(
                        rel.getTargetURI(),
                        rel.getTargetMode(),
                        rel.getType(),
                        newRId
                );
            }
            logger.log("分片" + sliceIndex + "的rId修复完成，共" + originalRels.size() + "个关系");
        } catch (Exception e) {
            logger.log("分片" + sliceIndex + "的rId修复警告：" + e.getMessage());
        }
    }
     */


    // 修复关系添加逻辑
    private void repairDocumentRelationsWithSliceId(XWPFDocument doc, int sliceIndex) {
        try {
            OPCPackage pkg = doc.getPackage();
            if (pkg == null) {
                logger.log("分片" + sliceIndex + "的文档包为空，无法修复rId");
                return;
            }

            // 获取所有原始关系
            List<PackageRelationship> originalRels = new ArrayList<>();
            for (PackageRelationship rel : pkg.getRelationships()) {
                originalRels.add(rel);
            }

            // 移除旧关系
            for (PackageRelationship rel : originalRels) {
                pkg.removeRelationship(rel.getId());
            }

            // 添加新关系（POI 4.1.0 正确重载方法）
            for (int i = 0; i < originalRels.size(); i++) {
                PackageRelationship rel = originalRels.get(i);
                String newRId = "rId_" + sliceIndex + "_" + (i + 1);

                try {
                    // 1. 将目标URI转换为PackagePartName（POI 4.1.0要求）
                    PackagePartName targetPartName = PackagingURIHelper.createPartName(rel.getTargetURI());

                    // 2. 直接使用String类型的关系类型
                    // String relationshipType = rel.getType();

                    // 3. 调用正确的重载方法
                    pkg.addRelationship(targetPartName,         // 目标部分名称（PackagePartName类型）
                            rel.getTargetMode(),    // 目标模式（TargetMode类型）
                            rel.getRelationshipType(),       // 关系类型（String类型）
                            newRId                  // 关系ID（String类型）
                    );
                } catch (Exception e) {
                    logger.log("分片" + sliceIndex + "添加关系失败：" + e.getMessage());
                }
            }
            logger.log("分片" + sliceIndex + "的rId修复完成，共" + originalRels.size() + "个关系");
        } catch (Exception e) {
            logger.log("分片" + sliceIndex + "的rId修复警告：" + e.getMessage());
        }
    }


    private String mergeSlices(List<String> slicePaths) throws Exception {
        logger.log("开始合并子文档，共" + slicePaths.size() + "个");
        long mergeStart = System.currentTimeMillis();

        XWPFDocument finalDoc = new XWPFDocument();
        int globalRidCounter = 1;

        try {
            int sliceNumber = 0;
            for (String slicePath : slicePaths) {
                sliceNumber++;
                long sliceMergeStart = System.currentTimeMillis();

                try (InputStream is = new FileInputStream(slicePath); XWPFDocument sliceDoc = new XWPFDocument(is)) {

                    // 合并段落
                    for (XWPFParagraph para : sliceDoc.getParagraphs()) {
                        XWPFParagraph newPara = finalDoc.createParagraph();

                        // 修复3：复制段落样式时进行空判断和类型转换
                        if (para.getCTP().getPPr() != null) {
                            CTPPr newPPr = (CTPPr) para.getCTP().getPPr().copy();
                            newPara.getCTP().setPPr(newPPr);
                        }

                        for (XWPFRun run : para.getRuns()) {
                            XWPFRun newRun = newPara.createRun();

                            if (run.getText(0) != null) {
                                newRun.setText(run.getText(0));
                            }

                            // 修复4：复制Run样式时的类型处理
                            if (run.getCTR().getRPr() != null) {
                                CTRPr newRPr = (CTRPr) run.getCTR().getRPr().copy();
                                newRun.getCTR().setRPr(newRPr);
                            }

                            // 处理图片
                            for (XWPFPicture picture : run.getEmbeddedPictures()) {
                                XWPFPictureData picData = picture.getPictureData();
                                byte[] imageData = picData.getData();

                                // 添加图片数据
                                finalDoc.addPictureData(new ByteArrayInputStream(imageData), picData.getPictureType());

                                // 修复：POI 4.1.0 中通过 CT 类获取图片宽高
                                int width = (int) picture.getCTPicture().getSpPr().getXfrm().getExt().getCx();
                                int height = (int) picture.getCTPicture().getSpPr().getXfrm().getExt().getCy();

                                // 创建新图片
                                XWPFRun picRun = newPara.createRun();
                                picRun.addPicture(new ByteArrayInputStream(imageData), picData.getPictureType(), picData.getFileName(), width, height
                                        /*
                                        picture.getWidth(),
                                        picture.getHeight()
                                         */);

                                globalRidCounter++;
                            }
                        }
                    }

                    // 复制表格
                    for (XWPFTable table : sliceDoc.getTables()) {
                        XWPFTable newTable = finalDoc.createTable();
                        copyTableContent(table, newTable);
                    }

                    long sliceMergeTime = (System.currentTimeMillis() - sliceMergeStart) / 1000;
                    logger.log("已合并第" + sliceNumber + "/" + slicePaths.size() + "个子文档，耗时：" + sliceMergeTime + "秒");
                } catch (Exception e) {
                    logger.log("合并子文档" + slicePath + "失败：" + e.getMessage());
                }
            }

            // 保存最终文档
            String finalFileName = "final_merged_" + UUID.randomUUID().toString().substring(0, 8) + ".docx";
            String finalPath = OUTPUT_DIR + File.separator + finalFileName;

            try (FileOutputStream out = new FileOutputStream(finalPath)) {
                finalDoc.write(out);
            }

            long totalMergeTime = (System.currentTimeMillis() - mergeStart) / 1000;
            logger.log("所有子文档合并完成，耗时：" + totalMergeTime + "秒");
            return finalPath;
        } finally {
            finalDoc.close();
        }
    }

    /**
     * 修复5：表格复制兼容POI 4.1.0，移除不兼容方法
     */
    private void copyTableContent(XWPFTable source, XWPFTable target) {
        // 复制表格样式
        target.getCTTbl().setTblPr(source.getCTTbl().getTblPr());

        // 清空目标表格的默认行（POI 4.1.0兼容方式）
        if (target.getRows().size() > 0) {
            target.removeRow(0);
        }

        // 复制行和单元格
        for (XWPFTableRow sourceRow : source.getRows()) {
            XWPFTableRow targetRow = target.createRow();
            targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());

            List<XWPFTableCell> sourceCells = sourceRow.getTableCells();
            List<XWPFTableCell> targetCells = targetRow.getTableCells();

            // 确保目标行有足够的单元格
            for (int i = targetCells.size(); i < sourceCells.size(); i++) {
                targetRow.addNewTableCell();
                targetCells = targetRow.getTableCells();
            }

            // 复制单元格内容
            for (int i = 0; i < sourceCells.size(); i++) {
                XWPFTableCell sourceCell = sourceCells.get(i);
                XWPFTableCell targetCell = targetCells.get(i);

                // 复制单元格样式
                targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());

                // 清空目标单元格段落（POI 4.1.0兼容方式）
                List<XWPFParagraph> paragraphs = targetCell.getParagraphs();
                for (int j = paragraphs.size() - 1; j >= 0; j--) {
                    targetCell.removeParagraph(j);
                }

                // 复制段落
                for (XWPFParagraph para : sourceCell.getParagraphs()) {
                    XWPFParagraph newPara = targetCell.addParagraph();
                    if (para.getCTP().getPPr() != null) {
                        newPara.getCTP().setPPr((CTPPr) para.getCTP().getPPr().copy());
                    }

                    for (XWPFRun run : para.getRuns()) {
                        XWPFRun newRun = newPara.createRun();
                        if (run.getText(0) != null) {
                            newRun.setText(run.getText(0));
                        }
                        if (run.getCTR().getRPr() != null) {
                            newRun.getCTR().setRPr((CTRPr) run.getCTR().getRPr().copy());
                        }
                    }
                }
            }
        }
    }

    private void cleanupSlices(List<String> slicePaths) {
        logger.log("开始清理临时子文档...");
        int deleted = 0;
        int failed = 0;

        for (String path : slicePaths) {
            File file = new File(path);
            if (file.exists() && file.delete()) {
                deleted++;
            } else {
                failed++;
                logger.log("无法删除临时文件：" + path);
            }
        }

        logger.log("临时文件清理完成：删除" + deleted + "个，失败" + failed + "个");
    }

    /**
     * 日志工具类
     */
    private static class Logger implements AutoCloseable {
        private final PrintWriter writer;

        public Logger(String logFile) throws FileNotFoundException {
            this.writer = new PrintWriter(new FileOutputStream(logFile, true));
        }

        public void log(String message) {
            String timestamp = new Date().toString();
            String logMessage = "[" + timestamp + "] " + message;
            System.out.println(logMessage);
            writer.println(logMessage);
            writer.flush();
        }

        @Override
        public void close() {
            writer.close();
        }
    }
}
