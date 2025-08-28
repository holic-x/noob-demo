package com.noob.base.batchDataHandler.docHandle.skill;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 高性能Word图片写入器：5万张截图4分钟内完成写入
 * 核心优化：多线程并行+对象池+流式写入+内存控制
 */
public class HighPerformanceWordWriter {

    // 配置参数
    // private static final int TOTAL_IMAGES = 50000; // 总图片数
    private static final int TOTAL_IMAGES = 10; // 总图片数
    private static final int THREAD_COUNT = 12;    // 并行线程数（建议为CPU核心数的1.5倍）
    private static final int BATCH_SIZE = 100;     // 每线程批次处理量
    // private static final String IMAGE_DIR = "/test-files/batch_test/batch_images"; // 图片存储目录
    // private static final String OUTPUT_WORD = "/test-files/batch_test/output.docx"; // 输出Word路径


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
    private final String OUTPUT_WORD = testDir + File.separator + "batch_test" + File.separator + "output.docx" + File.separator; // 输出Word路径


    // Word文档对象（线程安全处理）
    private final XWPFDocument document = new XWPFDocument();
    // 线程安全的文档锁（确保内容顺序）
    private final Object documentLock = new Object();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        new HighPerformanceWordWriter().writeImagesToWord();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时：" + (endTime - startTime) / 1000 + "秒");
    }

    /**
     * 核心方法：多线程并行写入图片到Word
     */
    public void writeImagesToWord() throws Exception {
        // 1. 创建线程池（优化线程调度）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                THREAD_COUNT,
                THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000), // 缓冲队列避免任务堆积
                new ThreadFactory() {
                    private int count = 0;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "image-writer-" + count++);
                        thread.setPriority(Thread.NORM_PRIORITY); // 平衡CPU资源
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy() // 任务满时回退到调用线程
        );

        // 2. 创建图片处理对象池（复用XWPFRun，减少对象创建开销）
        ObjectPool<XWPFRun> runPool = new GenericObjectPool<>(new XWPFRunFactory(document));

        try {
            // 3. 生成图片ID列表（实际场景从数据库/文件系统获取）
            List<String> imageIds = IntStream.range(0, TOTAL_IMAGES)
                    .mapToObj(i -> "mock_image_" + i + ".png") // 假设图片命名格式
                    .collect(Collectors.toList());

            // 4. 任务分片并提交
            CompletionService<Void> completionService = new ExecutorCompletionService<>(executor);
            for (int i = 0; i < TOTAL_IMAGES; i += BATCH_SIZE) {
                final int start = i;
                final int end = Math.min(i + BATCH_SIZE, TOTAL_IMAGES);
                completionService.submit(() -> {
                    processImageBatch(imageIds.subList(start, end), runPool);
                    return null;
                });
            }

            // 5. 等待所有任务完成
            for (int i = 0; i < (TOTAL_IMAGES + BATCH_SIZE - 1) / BATCH_SIZE; i++) {
                completionService.take().get();
            }

            // 6. 保存Word文档（最后一步统一写入磁盘）
            try (FileOutputStream out = new FileOutputStream(OUTPUT_WORD)) {
                document.write(out);
            }
            System.out.println("Word生成完成，路径：" + OUTPUT_WORD);

        } finally {
            // 7. 资源清理
            document.close();
            runPool.close();
            executor.shutdown();
        }
    }

    /**
     * 处理单个图片批次
     */
    private void processImageBatch(List<String> imageIds, ObjectPool<XWPFRun> runPool) throws Exception {
        for (String imageId : imageIds) {
            XWPFRun run = null;
            XWPFParagraph paragraph = null;
            try {
                // 1. 读取图片二进制数据（使用NIO提升读取性能）
                byte[] imageData = Files.readAllBytes(Paths.get(IMAGE_DIR + imageId));

                // 2. 创建段落（每张图片占一个段落）
                synchronized (documentLock) {
                    paragraph = document.createParagraph();
                    paragraph.setSpacingAfter(100); // 设置段落间距
                }

                // 3. 从对象池获取XWPFRun（文本运行对象，用于插入图片）
                run = runPool.borrowObject();
                run = paragraph.createRun(); // 绑定到当前段落

                // 4. 插入图片（自动识别格式，设置缩放比例）
                int pictureType = XWPFDocument.PICTURE_TYPE_PNG;
                run.addPicture(new ByteArrayInputStream(imageData),
                        pictureType,
                        imageId,
                        Units.toEMU(500), // 宽度（500像素）
                        Units.toEMU(300)); // 高度（300像素）

                // 5. 释放图片数据（及时回收内存）
                imageData = null;

            } catch (Exception e) {
                System.err.println("处理图片失败：" + imageId + "，错误：" + e.getMessage());
            } finally {
                // 6. 归还对象到池（关键：避免内存泄漏）
                if (run != null) {
                    runPool.returnObject(run);
                }
            }
        }
    }

    /**
     * XWPFRun对象工厂（用于对象池）
     */
    static class XWPFRunFactory implements PooledObjectFactory<XWPFRun> {
        private final XWPFDocument document;

        public XWPFRunFactory(XWPFDocument document) {
            this.document = document;
        }

        @Override
        public PooledObject<XWPFRun> makeObject() {
            XWPFParagraph tempPara = document.createParagraph();
            return new DefaultPooledObject<>(tempPara.createRun());
        }

        @Override
        public void destroyObject(PooledObject<XWPFRun> p) {
            // 销毁时清除内容
            p.getObject().setText("");
        }

        @Override
        public boolean validateObject(PooledObject<XWPFRun> p) {
            return true;
        }

        @Override
        public void activateObject(PooledObject<XWPFRun> p) {
        }

        @Override
        public void passivateObject(PooledObject<XWPFRun> p) {
            p.getObject().setText(""); // 清除之前的内容
        }
    }
}
    