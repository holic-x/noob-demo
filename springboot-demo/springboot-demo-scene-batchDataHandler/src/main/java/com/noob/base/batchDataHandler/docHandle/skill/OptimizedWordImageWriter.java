package com.noob.base.batchDataHandler.docHandle.skill;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.poi.util.Units.toEMU;

/**
 * todo 优化版本测试验证
 * 大规模图片Word生成器：优化版
 * 多线程读取+单线程写入，支持5万+张图片稳定生成
 * - 5000    5s
 * - 10000   15s
 * - 20000   69s
 * - 30000
 * - 40000   339s
 * - 50000   大概6min
 */
public class OptimizedWordImageWriter {

    // 1. 核心配置（根据硬件配置调整）
    // private static final int TOTAL_IMAGES = 50000;       // 目标图片总数
    // private static final int TOTAL_IMAGES = 10000;       // 目标图片总数
    private static final int TOTAL_IMAGES = 30000;       // 目标图片总数
    private static final int READ_THREAD_COUNT = Runtime.getRuntime().availableProcessors(); // 读取线程数=CPU核心数
    private static final int BATCH_SIZE = 100;           // 读取批次大小
    private static final String IMAGE_SUFFIX = ".png";  // 图片格式
    private static final int PROGRESS_INTERVAL = 1000;   // 进度打印间隔（张）
    private static final int MEMORY_SAFE_BATCH = 1000;   // 内存安全批处理量（避免OOM）

    // 2. 目录配置
    private final String testDir = "E:" + File.separator + "test" + File.separator + "test-files";
    private final String IMAGE_DIR = testDir + File.separator + "batch_test" + File.separator + "batch_images" + File.separator;
    private final String OUTPUT_WORD = testDir + File.separator + "batch_test" + File.separator +
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
        long startTime = System.currentTimeMillis();
        OptimizedWordImageWriter writer = new OptimizedWordImageWriter();
        writer.generateWordWithImages();
        long endTime = System.currentTimeMillis();
        System.out.println("\n===== 执行完成 =====");
        System.out.println("总耗时：" + (endTime - startTime) / 1000 + "秒");
        System.out.println("生成文件路径：" + writer.OUTPUT_WORD);
    }

    /**
     * 主流程：分阶段处理，控制内存占用
     */
    public void generateWordWithImages() throws Exception {
        // 阶段1：预生成所有图片ID（避免内存一次性加载过多数据）
        List<String> allImageIds = IntStream.range(0, TOTAL_IMAGES)
                .mapToObj(i -> "mock_image_" + i + IMAGE_SUFFIX)
                .collect(Collectors.toList());

        // 创建文档对象
        XWPFDocument document = new XWPFDocument();
        long totalWriteTime = 0;

        try {
            // 阶段2：分块处理（每MEMORY_SAFE_BATCH张图片为一块）
            for (int block = 0; block < TOTAL_IMAGES; block += MEMORY_SAFE_BATCH) {
                int currentBlockSize = Math.min(MEMORY_SAFE_BATCH, TOTAL_IMAGES - block);
                System.out.println("\n===== 处理块 " + (block / MEMORY_SAFE_BATCH + 1) + " =====");
                System.out.println("块大小：" + currentBlockSize + "张图片");

                // 子列表：当前块的图片ID
                List<String> blockImageIds = allImageIds.subList(block, block + currentBlockSize);

                // 步骤1：多线程读取当前块的图片数据
                List<ImageData> blockImageData = readBlockImagesInParallel(blockImageIds);

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
            }

            // 最终保存文档
            try (FileOutputStream out = new FileOutputStream(OUTPUT_WORD)) {
                document.write(out);
            }

        } finally {
            document.close();
            System.out.println("\n===== 性能统计 =====");
            System.out.println("总写入耗时：" + totalWriteTime / 1000 + "秒");
            System.out.println("平均每张图片写入耗时：" + (totalWriteTime / TOTAL_IMAGES) + "毫秒");
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
                r -> new Thread(r, "image-reader-" + System.currentTimeMillis()),
                new ThreadPoolExecutor.CallerRunsPolicy()
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
                            data.data = Files.readAllBytes(Paths.get(path));
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
        }
    }

    /**
     * 单线程写入当前块的图片到Word
     */
    private void writeBlockImagesToWord(XWPFDocument document, List<ImageData> blockImageData, int blockStartIndex) throws Exception {
        int successCount = 0;

        for (ImageData data : blockImageData) {
            if (!data.readSuccess) continue;

            try {
                // 创建段落（单线程无需锁）
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setSpacingAfter(100);

                // 设置居中对齐
                // 设置居中对齐（POI 4.1.0 稳定兼容写法）
                CTP ctp = paragraph.getCTP();
                CTPPr pPr = ctp.getPPr() == null ? ctp.addNewPPr() : ctp.getPPr();
                CTJc jc = pPr.getJc() == null ? pPr.addNewJc() : pPr.getJc();
                // jc.setVal("center"); // 直接使用字符串值，避免类型问题
                jc.setVal(STJc.CENTER); // 直接使用字符串值，避免类型问题

                XWPFRun run = paragraph.createRun();

                // 生成唯一媒体名（带块编号，进一步确保唯一性）
                String uniqueMediaName = String.format("media_block%d_%s%s",
                        blockStartIndex / MEMORY_SAFE_BATCH,
                        UUID.randomUUID().toString().replace("-", "").substring(0, 8),
                        IMAGE_SUFFIX);

                // 显式添加图片数据（表示存储图片数据到文档）
                document.addPictureData(
                        new ByteArrayInputStream(data.data),
                        XWPFDocument.PICTURE_TYPE_PNG
                        // uniqueMediaName  // 只保留这三个参数
                );

                /*
                // poi 高级版本才有的设定
                document.addPictureData(
                        new ByteArrayInputStream(data.data),
                        XWPFDocument.PICTURE_TYPE_PNG,
                        uniqueMediaName,
                        toEMU(500),
                        toEMU(300)
                );
                 */

                // 插入图片（表示在文档中引用指定图片）
                run.addPicture(
                        new ByteArrayInputStream(data.data),
                        XWPFDocument.PICTURE_TYPE_PNG,
                        uniqueMediaName,
                        toEMU(500),
                        toEMU(300)
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

        System.out.println("块写入完成：成功写入" + successCount + "张");
    }
}
