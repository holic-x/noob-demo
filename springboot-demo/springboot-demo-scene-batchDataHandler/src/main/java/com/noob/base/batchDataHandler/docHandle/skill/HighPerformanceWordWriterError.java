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
 * 高性能Word图片写入器：兼容POI 4.1.0版本
 * 移除XWPFRun对象池以解决激活错误，保证稳定性
 * ❌ POI.4.1.0 无法支持多线程并发写入文稿
 */
public class HighPerformanceWordWriterError {

    // 配置参数
    private static final int TOTAL_IMAGES = 1000; // 测试用图片数
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;
    private static final int BATCH_SIZE = 100;

    // 目录配置
    private String getTestDir() {
        String projectDir = System.getProperty("user.dir");
        String testDir = projectDir + File.separator + "test-files";
        System.out.println("当前工程目录路径: " + projectDir);
        return testDir;
    }

    /*
    String testDir = getTestDir();
    private final String IMAGE_DIR = testDir + File.separator + "batch_test" + File.separator + "batch_images" + File.separator;
    private final String OUTPUT_WORD = testDir + File.separator + "batch_test" + File.separator + "output.docx";
     */

    // window 下测试目录
    String testDir = "E:" + File.separator + "test" + File.separator + "test-files";
    private final String IMAGE_DIR = testDir + File.separator + "batch_test" + File.separator + "batch_images" + File.separator;
    private final String OUTPUT_WORD = testDir + File.separator + "batch_test" + File.separator + "output_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6) + ".docx";

    // Word文档对象和锁
    private final XWPFDocument document = new XWPFDocument();
    private final Object documentLock = new Object();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        new HighPerformanceWordWriterError().writeImagesToWord();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) / 1000 + "秒");
    }

    /**
     * 核心方法：多线程并行写入图片到Word
     */
    public void writeImagesToWord() throws Exception {
        // 1. 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                THREAD_COUNT,
                THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                new ThreadFactory() {
                    private int count = 0;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "image-writer-" + count++);
                        thread.setPriority(Thread.NORM_PRIORITY);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        try {
            // 2. 生成图片ID列表
            List<String> imageIds = IntStream.range(0, TOTAL_IMAGES)
                    .mapToObj(i -> "mock_image_" + i + ".png")
                    .collect(Collectors.toList());

            // 3. 任务分片并提交
            CompletionService<Void> completionService = new ExecutorCompletionService<>(executor);
            for (int i = 0; i < TOTAL_IMAGES; i += BATCH_SIZE) {
                final int start = i;
                final int end = Math.min(i + BATCH_SIZE, TOTAL_IMAGES);
                completionService.submit(() -> {
                    processImageBatch(imageIds.subList(start, end));
                    return null;
                });
            }

            // 4. 等待所有任务完成
            for (int i = 0; i < (TOTAL_IMAGES + BATCH_SIZE - 1) / BATCH_SIZE; i++) {
                completionService.take().get();
            }

            // 5. 保存Word文档
            try (FileOutputStream out = new FileOutputStream(OUTPUT_WORD)) {
                document.write(out);
            }
            System.out.println("Word生成完成，路径：" + OUTPUT_WORD);

        } finally {
            // 6. 资源清理
            document.close();
            executor.shutdown();
        }
    }

    /**
     * 处理单个图片批次 - 移除对象池，直接创建XWPFRun
     */
    private void processImageBatch(List<String> imageIds) {
        for (String imageId : imageIds) {
            try {
                // 1. 读取图片二进制数据
                String imagePath = IMAGE_DIR + imageId;
                byte[] imageData = Files.readAllBytes(Paths.get(imagePath));

                // 2. 创建段落和Run（在同一个同步块中，确保关联正确）
                XWPFParagraph paragraph;
                XWPFRun run;
                synchronized (documentLock) {
                    paragraph = document.createParagraph();
                    paragraph.setSpacingAfter(100);

                    // 设置居中对齐（POI 4.1.0 稳定兼容写法）
                    CTP ctp = paragraph.getCTP();
                    CTPPr pPr = ctp.getPPr() == null ? ctp.addNewPPr() : ctp.getPPr();
                    CTJc jc = pPr.getJc() == null ? pPr.addNewJc() : pPr.getJc();
                    // jc.setVal("center"); // 直接使用字符串值，避免类型问题
                    jc.setVal(STJc.CENTER); // 直接使用字符串值，避免类型问题

                    // 直接创建Run，与当前段落绑定（最稳定方式）
                    run = paragraph.createRun();
                }

                // 3. 插入图片
                int pictureType = XWPFDocument.PICTURE_TYPE_PNG;
                run.addPicture(new ByteArrayInputStream(imageData),
                        pictureType,
                        imageId,
                        toEMU(500),
                        toEMU(300));

            } catch (Exception e) {
                System.err.println("处理图片失败：" + imageId + "，错误：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
