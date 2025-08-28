package com.noob.base.batchDataHandler.docHandle.skill;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.poi.util.Units.toEMU;

/**
 * 大规模图片Word生成器：POI 4.1.0兼容版
 * 多线程读取+单线程写入，支持5万+张图片稳定生成
 */
public class OptimizedParallelWordProcessor {

    // 1. 核心配置（根据硬件配置调整）
    // private static final int TOTAL_IMAGES = 50000;       // 目标图片总数
    private static final int TOTAL_IMAGES = 1000;       // 目标图片总数
    private static final int READ_THREAD_COUNT = Runtime.getRuntime().availableProcessors(); // 读取线程数=CPU核心数
    private static final int BATCH_SIZE = 100;           // 读取批次大小
    private static final String IMAGE_SUFFIX = ".png";  // 图片格式
    private static final int PROGRESS_INTERVAL = 100;    // 进度打印间隔（张）- 调整为更频繁显示
    private static final int MEMORY_SAFE_BATCH = 1000;   // 内存安全批处理量（避免OOM）

    // 2. 目录配置
    private static final String testDir = "E:" + File.separator + "test" + File.separator + "test-files";
    private static final String IMAGE_DIR = testDir + File.separator + "batch_test" + File.separator + "batch_images" + File.separator;
    private static final String OUTPUT_WORD = testDir + File.separator + "batch_test" + File.separator +
            "output_" + UUID.randomUUID().toString().replace("-", "").substring(0, 6) + ".docx";

    // 3. 数据结构
    private static class ImageData {
        String imageId;
        byte[] data;
        boolean readSuccess;
        long readTime; // 读取耗时（毫秒）

        ImageData(String imageId) {
            this.imageId = imageId;
            this.readSuccess = false;
        }
    }

    public static void main(String[] args) throws Exception {
        // 建议JVM参数：-Xms10g -Xmx16g -XX:+UseG1GC -XX:MaxGCPauseMillis=200
        long startTime = System.currentTimeMillis();
        OptimizedWordImageWriter writer = new OptimizedWordImageWriter();
        writer.generateWordWithImages();
        long endTime = System.currentTimeMillis();
        System.out.println("\n===== 执行完成 =====");
        System.out.println("总耗时：" + (endTime - startTime) / 1000 + "秒");
        System.out.println("生成文件路径：" + OUTPUT_WORD);
    }

    /**
     * 主流程：分阶段处理，控制内存占用
     */
    public void generateWordWithImages() throws Exception {
        // 验证目录存在性
        validateDirectory(IMAGE_DIR);

        // 阶段1：预生成所有图片ID（避免内存一次性加载过多数据）
        List<String> allImageIds = IntStream.range(0, TOTAL_IMAGES)
                .mapToObj(i -> "mock_image_" + i + IMAGE_SUFFIX)
                .collect(Collectors.toList());

        // 创建文档对象 - POI 4.1.0兼容的空文档初始化
        XWPFDocument document = new XWPFDocument();
        // 初始化文档体（POI 4.1.0有时不会自动创建）
        CTBody body = document.getDocument().getBody();
        if (body == null) {
            document.getDocument().addNewBody();
        }

        long totalWriteTime = 0;
        long totalReadTime = 0;

        try {
            // 阶段2：分块处理（每MEMORY_SAFE_BATCH张图片为一块）
            for (int block = 0; block < TOTAL_IMAGES; block += MEMORY_SAFE_BATCH) {
                int currentBlockSize = Math.min(MEMORY_SAFE_BATCH, TOTAL_IMAGES - block);
                System.out.println("\n===== 处理块 " + (block / MEMORY_SAFE_BATCH + 1) + " =====");
                System.out.println("块大小：" + currentBlockSize + "张图片");

                // 子列表：当前块的图片ID
                List<String> blockImageIds = allImageIds.subList(block, block + currentBlockSize);

                // 步骤1：多线程读取当前块的图片数据
                long blockReadStart = System.currentTimeMillis();
                List<ImageData> blockImageData = readBlockImagesInParallel(blockImageIds);
                long blockReadTime = System.currentTimeMillis() - blockReadStart;
                totalReadTime += blockReadTime;
                System.out.println("块读取耗时：" + blockReadTime / 1000 + "秒");

                // 步骤2：单线程写入当前块的图片到文档
                long blockWriteStart = System.currentTimeMillis();
                writeBlockImagesToWord(document, blockImageData, block);
                long blockWriteTime = System.currentTimeMillis() - blockWriteStart;
                totalWriteTime += blockWriteTime;
                System.out.println("块写入耗时：" + blockWriteTime / 1000 + "秒");

                // 清理内存：释放已写入的图片数据
                for (ImageData data : blockImageData) {
                    data.data = null; // 帮助GC回收
                }
                // 显式触发GC（在大内存块处理后）
                System.gc();
            }

            // 最终保存文档（使用缓冲流提升性能）
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(OUTPUT_WORD), 8192)) {
                document.write(out);
            }

        } finally {
            document.close();
            System.out.println("\n===== 性能统计 =====");
            System.out.println("总读取耗时：" + totalReadTime / 1000 + "秒");
            System.out.println("总写入耗时：" + totalWriteTime / 1000 + "秒");
            System.out.println("平均每张图片处理耗时：" +
                    ((totalReadTime + totalWriteTime) / TOTAL_IMAGES) + "毫秒");
        }
    }

    /**
     * 验证目录是否存在，不存在则创建
     */
    private void validateDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("创建目录：" + path);
            } else {
                throw new RuntimeException("无法创建目录：" + path);
            }
        }
    }

    /**
     * 多线程读取当前块的图片数据
     */
    private List<ImageData> readBlockImagesInParallel(List<String> blockImageIds) throws Exception {
        // 初始化当前块的图片数据列表
        List<ImageData> blockImageData = blockImageIds.stream()
                .map(ImageData::new)
                .collect(Collectors.toList());

        // 创建读取线程池（核心线程数=CPU核心数）
        ThreadPoolExecutor readExecutor = new ThreadPoolExecutor(
                READ_THREAD_COUNT,
                READ_THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(BATCH_SIZE * 2),
                r -> {
                    Thread t = new Thread(r, "image-reader-" + System.currentTimeMillis());
                    t.setPriority(Thread.NORM_PRIORITY);
                    return t;
                },
                new ThreadPoolExecutor.CallerRunsPolicy() // 队列满时由调用线程执行，避免任务丢失
        );

        try {
            CompletionService<Void> completionService = new ExecutorCompletionService<>(readExecutor);
            int totalImages = blockImageData.size();

            // 分批次提交读取任务
            for (int i = 0; i < totalImages; i += BATCH_SIZE) {
                final int start = i;
                final int end = Math.min(i + BATCH_SIZE, totalImages);
                completionService.submit(() -> {
                    List<ImageData> batch = blockImageData.subList(start, end);
                    for (ImageData data : batch) {
                        long startRead = System.currentTimeMillis();
                        try {
                            String path = IMAGE_DIR + data.imageId;
                            File imageFile = new File(path);
                            if (!imageFile.exists() || !imageFile.isFile()) {
                                throw new FileNotFoundException("文件不存在");
                            }
                            // 使用缓冲流读取，提升大文件读取性能

                            // JDK 9 +
                            /*
                            try (InputStream is = new BufferedInputStream(new FileInputStream(imageFile))) {
                                data.data = is.readAllBytes();
                            }
                             */

                            // 使用缓冲流读取，提升大文件读取性能（JDK 1.8兼容版）
                            try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(imageFile))) {
                                // 预估缓冲区大小，避免频繁扩容
                                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                                byte[] dataBuffer = new byte[8192]; // 8KB缓冲区
                                int bytesRead;
                                while ((bytesRead = is.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                                    buffer.write(dataBuffer, 0, bytesRead);
                                }
                                buffer.flush();
                                data.data = buffer.toByteArray();
                            }

                            data.readSuccess = true;
                        } catch (Exception e) {
                            System.err.println("读取失败：" + data.imageId + " - " + e.getMessage());
                            data.readSuccess = false;
                        }
                        data.readTime = System.currentTimeMillis() - startRead;
                    }
                    return null;
                });
            }

            // 等待所有批次完成
            int totalBatches = (totalImages + BATCH_SIZE - 1) / BATCH_SIZE;
            for (int i = 0; i < totalBatches; i++) {
                completionService.take().get();
            }

            // 统计读取结果
            long successCount = blockImageData.stream().filter(d -> d.readSuccess).count();
            System.out.println("图片读取完成：成功" + successCount + "张，失败" + (totalImages - successCount) + "张");
            return blockImageData;

        } finally {
            readExecutor.shutdown();
            // 等待线程池关闭
            if (!readExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                readExecutor.shutdownNow();
            }
        }
    }

    /**
     * 单线程写入当前块的图片到Word（POI 4.1.0兼容版）
     */
    private void writeBlockImagesToWord(XWPFDocument document, List<ImageData> blockImageData, int blockStartIndex) throws Exception {
        int successCount = 0;
        // 创建段落样式模板（复用，减少XML操作）
        XWPFParagraph templatePara = document.createParagraph();
        configureParagraphStyle(templatePara);

        for (ImageData data : blockImageData) {
            if (!data.readSuccess) continue;

            try {
                // 复制模板段落样式（比重新创建更高效）
                XWPFParagraph paragraph = document.createParagraph();
                copyParagraphStyle(templatePara, paragraph);

                XWPFRun run = paragraph.createRun();

                // 生成唯一媒体名（带块编号，确保唯一性）
                String uniqueMediaName = String.format("media_block%d_%s%s",
                        blockStartIndex / MEMORY_SAFE_BATCH,
                        UUID.randomUUID().toString().replace("-", "").substring(0, 8),
                        IMAGE_SUFFIX);

                // POI 4.1.0兼容写法：addPictureData只接受前三个参数
                // 返回值为String类型的关系ID（rId）
                String pictureRelId = document.addPictureData(
                        new ByteArrayInputStream(data.data),
                        XWPFDocument.PICTURE_TYPE_PNG
                );

                // 验证关系ID有效性（POI 4.1.0有时会生成无效ID）
                if (pictureRelId == null || !pictureRelId.matches("rId\\d+")) {
                    throw new RuntimeException("生成无效的图片关系ID: " + pictureRelId);
                }

                // 插入图片（POI 4.1.0兼容的参数顺序）
                run.addPicture(
                        new ByteArrayInputStream(data.data),
                        XWPFDocument.PICTURE_TYPE_PNG,
                        uniqueMediaName,
                        toEMU(500),  // 宽度
                        toEMU(300)   // 高度
                );

                successCount++;
                // 打印进度
                if (successCount % PROGRESS_INTERVAL == 0) {
                    int globalIndex = blockStartIndex + successCount;
                    System.out.println("已写入：" + globalIndex + "张（当前块成功：" + successCount + "张）");
                }

            } catch (Exception e) {
                System.err.println("写入失败：" + data.imageId + " - " + e.getMessage());
            }
        }

        // 移除模板段落（只用于样式复制，不实际显示）
        document.removeBodyElement(document.getPosOfParagraph(templatePara));

        System.out.println("块写入完成：成功写入" + successCount + "张");
    }

    /**
     * 配置段落样式（POI 4.1.0兼容）
     */
    private void configureParagraphStyle(XWPFParagraph paragraph) {
        paragraph.setSpacingAfter(100);
        paragraph.setSpacingBefore(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        // 底层XML配置（确保POI 4.1.0兼容性）
        CTPPr pPr = paragraph.getCTP().getPPr();
        if (pPr == null) {
            pPr = paragraph.getCTP().addNewPPr();
        }
        CTJc jc = pPr.getJc();
        if (jc == null) {
            jc = pPr.addNewJc();
        }
        jc.setVal(STJc.CENTER); // 使用STJc枚举，避免字符串类型问题
    }

    /**
     * 复制段落样式（减少重复XML操作）
     */
    private void copyParagraphStyle(XWPFParagraph source, XWPFParagraph target) {
        // 复制段落对齐和间距
        target.setAlignment(source.getAlignment());
        target.setSpacingAfter(source.getSpacingAfter());
        target.setSpacingBefore(source.getSpacingBefore());

        // 复制底层XML样式（POI 4.1.0兼容）
        CTPPr sourcePr = source.getCTP().getPPr();
        if (sourcePr != null) {
            CTPPr targetPr = target.getCTP().getPPr();
            if (targetPr == null) {
                targetPr = target.getCTP().addNewPPr();
            }
            targetPr.set(sourcePr); // 直接复制整个样式对象
        }
    }
}
