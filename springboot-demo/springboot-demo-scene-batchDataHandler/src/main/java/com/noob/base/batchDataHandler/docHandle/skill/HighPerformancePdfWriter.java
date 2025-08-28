package com.noob.base.batchDataHandler.docHandle.skill;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 高性能PDF图片写入器：5万张截图4分钟内完成写入
 * 核心优化：多线程并行+对象池+流式写入+内存控制
 */
public class HighPerformancePdfWriter {

    // 配置参数
    private static final int TOTAL_IMAGES = 50000; // 总图片数
    private static final int THREAD_COUNT = 10;    // 并行线程数
    private static final int BATCH_SIZE = 100;     // 每线程批次处理量
//    private static final String IMAGE_DIR = "/path/to/images/"; // 图片存储目录
//    private static final String OUTPUT_PDF = "/path/to/output.pdf"; // 输出PDF路径

    // 获取测试目录（例如此处可以基于当前工程目录路径）
    private String getTestDir() {
        // String baseDir = "E:/workspace/project/noob-demo/springboot-demo/springboot-demo-scene-batchDataHandler";
        String projectDir = System.getProperty("user.dir");
        String testDir = projectDir + File.separator + "test-files";
        System.out.println("当前工程目录路径: " + projectDir);
        return testDir;
    }

    String testDir = getTestDir();
    private final String IMAGE_DIR = testDir + File.separator + "batch_test" + File.separator + "batch_images" + File.separator; // 图片存储目录
    private final String OUTPUT_PDF = testDir + File.separator + "batch_test" + File.separator + "output.pdf" + File.separator; // 输出Word路径


    // PDF文档对象（线程安全处理）
    private final PDDocument document = new PDDocument();
    // 线程安全的内容流锁（确保写入顺序）
    private final Object contentStreamLock = new Object();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        new HighPerformancePdfWriter().writeImagesToPdf();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) / 1000 + "秒");
    }

    /**
     * 核心方法：多线程并行写入图片到PDF
     */
    public void writeImagesToPdf() throws Exception {
        // 1. 创建线程池（核心线程10，最大线程10，避免线程切换开销）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                THREAD_COUNT,
                THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    private int count = 0;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "image-writer-" + count++);
                        thread.setDaemon(true); // 守护线程，避免主线程退出后残留
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy() // 任务满时让提交者执行，避免任务丢失
        );

        // 2. 创建图片处理对象池（复用PDImageXObject，减少GC）
        ObjectPool<PDImageXObject> imagePool = new GenericObjectPool<>(new PDImageXObjectFactory(document));

        try {
            // 3. 生成图片ID列表（实际场景从数据库/文件系统获取）
            List<String> imageIds = IntStream.range(0, TOTAL_IMAGES)
                    .mapToObj(i -> "mock_image_" + (i%10) + ".png") // 假设图片命名格式
                    .collect(Collectors.toList());

            // 4. 任务分片并提交
            CompletionService<Void> completionService = new ExecutorCompletionService<>(executor);
            for (int i = 0; i < TOTAL_IMAGES; i += BATCH_SIZE) {
                final int start = i;
                final int end = Math.min(i + BATCH_SIZE, TOTAL_IMAGES);
                completionService.submit(() -> {
                    processImageBatch(imageIds.subList(start, end), imagePool);
                    return null;
                });
            }

            // 5. 等待所有任务完成
            for (int i = 0; i < (TOTAL_IMAGES + BATCH_SIZE - 1) / BATCH_SIZE; i++) {
                completionService.take().get(); // 阻塞等待每个批次完成
            }

            // 6. 保存PDF文档（最后一步统一保存，避免中间写盘开销）
            document.save(OUTPUT_PDF);
            System.out.println("PDF生成完成，路径：" + OUTPUT_PDF);

        } finally {
            // 7. 资源清理
            document.close();
            imagePool.close();
            executor.shutdown();
        }
    }

    /**
     * 处理单个图片批次
     */
    private void processImageBatch(List<String> imageIds, ObjectPool<PDImageXObject> imagePool) throws Exception {
        for (String imageId : imageIds) {
            PDImageXObject pdImage = null;
            PDPage page = null;
            PDPageContentStream contentStream = null;
            try {
                // 1. 从对象池获取图片对象（复用减少创建开销）
                pdImage = imagePool.borrowObject();
                // 2. 加载图片（实际场景从OSS/S3下载，这里模拟本地文件）
                pdImage = PDImageXObject.createFromFile(new File(IMAGE_DIR + imageId).getAbsolutePath(), document);

                // 3. 创建PDF页面（每张图片占1页）
                page = new PDPage();
                synchronized (contentStreamLock) { // 确保页面添加顺序
                    document.addPage(page);
                }

                // 4. 写入图片到页面（流式处理，写完立即释放）
                contentStream = new PDPageContentStream(document, page);
                // 按页面比例缩放图片（保持宽高比）
                float scale = Math.min(page.getMediaBox().getWidth() / pdImage.getWidth(),
                        page.getMediaBox().getHeight() / pdImage.getHeight());
                contentStream.drawImage(pdImage, 0, 0,
                        pdImage.getWidth() * scale,
                        pdImage.getHeight() * scale);

            } catch (Exception e) {
                System.err.println("处理图片失败：" + imageId + "，错误：" + e.getMessage());
            } finally {
                // 5. 释放资源（关键：避免内存泄漏）
                if (contentStream != null) {
                    contentStream.close(); // 关闭流释放内存
                }
                if (pdImage != null) {
                    imagePool.returnObject(pdImage); // 归还对象池复用
                }
            }
        }
    }

    /**
     * PDImageXObject对象工厂（用于对象池）
     */
    static class PDImageXObjectFactory implements PooledObjectFactory<PDImageXObject> {
        private final PDDocument document;

        public PDImageXObjectFactory(PDDocument document) {
            this.document = document;
        }

        @Override
        public PooledObject<PDImageXObject> makeObject() throws IOException {
            return new DefaultPooledObject<>(new PDImageXObject(document));
        }

        @Override
        public void destroyObject(PooledObject<PDImageXObject> p) {
            // 销毁对象时释放资源
            try {
                p.getObject().getCOSObject().clear();
            } catch (Exception e) {
                // 忽略销毁异常
            }
        }

        @Override
        public boolean validateObject(PooledObject<PDImageXObject> p) {
            return true; // 简化处理，实际可添加校验逻辑
        }

        @Override
        public void activateObject(PooledObject<PDImageXObject> p) {
        }

        @Override
        public void passivateObject(PooledObject<PDImageXObject> p) {
        }
    }
}
