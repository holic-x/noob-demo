package com.noob.base.batchDataHandler.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class MultiThreadTemplateImageGenerator {

    // ==================== 核心配置区（按需修改） ====================
    // 1. 路径与数量配置（重点：生成数量支持上万张）
    private static final String TEMPLATE_IMAGE_PATH = "E:\\test\\test-files\\batch_test_ver02\\template.png";
    private static final String OUTPUT_DIR = "E:\\test\\test-files\\batch_test_ver02\\batch_images";
    private static final int TOTAL_GENERATE_COUNT = 10000; // 总生成数量（支持1万/5万/10万）

    // 2. 多线程配置（关键：根据CPU核心数调整，避免资源浪费）
    private static final int CPU_CORES = Runtime.getRuntime().availableProcessors(); // 获取CPU核心数
    private static final int CORE_POOL_SIZE = CPU_CORES * 2; // 核心线程数（CPU×2，平衡IO）
    private static final int MAX_POOL_SIZE = CPU_CORES * 4; // 最大线程数（防止线程爆炸）
    private static final long KEEP_ALIVE_TIME = 5000; // 空闲线程存活时间（5秒）
    private static final int QUEUE_CAPACITY = 100; // 任务队列大小（避免任务堆积占内存）

    // 3. 序号样式配置（与原逻辑一致，支持自定义）
    private static final int FONT_SIZE = 128;
    private static final String FONT_NAME = "SimHei";
    private static final Color TEXT_COLOR = Color.RED;
    private static final Color TEXT_STROKE_COLOR = Color.BLACK;
    private static final float TEXT_STROKE_WIDTH = 3.0f;

    // 4. 序号位置配置（居中/自定义）
    private static final String POSITION_MODE = "CENTER";
    private static final int CENTER_OFFSET_X = 0;
    private static final int CENTER_OFFSET_Y = 0;
    private static final int CUSTOM_POS_X = 200;
    private static final int CUSTOM_POS_Y = 300;
    // =====================================================================================

    // 全局共享：模板图片（仅加载一次，避免多线程重复读取IO）
    private static BufferedImage templateImage;
    // 全局共享：图片格式（仅解析一次）
    private static String imageFormat;


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // 计时：统计总耗时

        try {
            // 1. 初始化：提前加载模板图片（仅1次，多线程共享）
            initTemplateResource();

            // 2. 创建输出目录
            createOutputDir();

            // 3. 初始化线程池（核心：控制并发量）
            ExecutorService threadPool = createThreadPool();

            // 4. 提交任务：批量生成图片（从1到TOTAL_GENERATE_COUNT）
            submitGenerateTasks(threadPool);

            // 5. 关闭线程池（等待所有任务完成，不接受新任务）
            threadPool.shutdown();
            // 等待所有任务结束（超时时间：1小时，防止无限阻塞）
            if (threadPool.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.printf("%n✅ 所有任务完成！总计生成：%d张，总耗时：%.2f秒%n",
                        TOTAL_GENERATE_COUNT, (System.currentTimeMillis() - startTime) / 1000.0);
            } else {
                System.err.println("❌ 部分任务超时未完成！请检查线程池配置或图片生成逻辑");
            }

        } catch (Exception e) {
            System.err.println("❌ 程序异常终止：" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 初始化：加载模板图片和格式（仅1次，避免多线程重复IO）
     */
    private static void initTemplateResource() throws IOException {
        // 加载模板图片
        File templateFile = new File(TEMPLATE_IMAGE_PATH);
        if (!templateFile.exists()) {
            throw new IOException("模板图片不存在：" + TEMPLATE_IMAGE_PATH);
        }
        templateImage = ImageIO.read(templateFile);

        // 解析图片格式
        imageFormat = getImageFormat(TEMPLATE_IMAGE_PATH);

        // 打印模板信息
        System.out.printf("✅ 模板初始化完成！分辨率：%dx%d，格式：%s，CPU核心数：%d%n",
                templateImage.getWidth(), templateImage.getHeight(),
                imageFormat, CPU_CORES);
    }


    /**
     * 创建输出目录（多线程前确保目录存在）
     */
    private static void createOutputDir() throws IOException {
        File outputDir = new File(OUTPUT_DIR);
        if (!outputDir.exists()) {
            boolean mkdirSuccess = outputDir.mkdirs();
            if (!mkdirSuccess) {
                throw new IOException("创建输出目录失败：" + OUTPUT_DIR);
            }
            System.out.println("✅ 输出目录创建完成：" + OUTPUT_DIR);
        } else {
            System.out.println("✅ 输出目录已存在：" + OUTPUT_DIR);
        }
    }


    /**
     * 创建线程池（自定义参数，避免OOM）
     */
    private static ExecutorService createThreadPool() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,          // 核心线程数
                MAX_POOL_SIZE,           // 最大线程数
                KEEP_ALIVE_TIME,         // 空闲线程存活时间
                TimeUnit.MILLISECONDS,   // 时间单位
                new LinkedBlockingQueue<>(QUEUE_CAPACITY), // 任务队列（有界，防止堆积）
                new ThreadPoolExecutor.CallerRunsPolicy()  // 队列满时的策略：由提交任务的线程（主线程）执行，避免任务丢失
        );
    }


    /**
     * 提交生成任务到线程池（批量提交，支持断点续跑）
     */
    private static void submitGenerateTasks(ExecutorService threadPool) {
        for (int serial = 1; serial <= TOTAL_GENERATE_COUNT; serial++) {
            int finalSerial = serial; // 匿名内部类需要final变量
            // 提交单个生成任务（每个任务处理1张图片）
            threadPool.submit(() -> {
                try {
                    generateSingleImage(finalSerial); // 生成单张图片
                } catch (IOException e) {
                    System.err.printf("❌ 生成失败：mock_image_%d.%s，原因：%s%n",
                            finalSerial, imageFormat, e.getMessage());
                }
            });
        }
        System.out.printf("✅ 所有任务提交完成！共提交：%d个任务，线程池核心数：%d%n",
                TOTAL_GENERATE_COUNT, CORE_POOL_SIZE);
    }


    /**
     * 单张图片生成逻辑（线程安全：无共享资源，每个任务独立处理）
     */
    private static void generateSingleImage(int serial) throws IOException {
        // 断点续跑：先检查图片是否已存在，存在则跳过（避免重复生成）
        String imageName = "mock_image_" + serial + "." + imageFormat;
        File imageFile = new File(OUTPUT_DIR, imageName);
        if (imageFile.exists()) {
            System.out.printf("ℹ️  跳过已存在：%s%n", imageName);
            return;
        }

        int imgWidth = templateImage.getWidth();
        int imgHeight = templateImage.getHeight();
        Graphics2D g2d = null;
        BufferedImage newImage = null;

        try {
            // 1. 创建独立的图片缓冲区（每个任务单独创建，避免线程共享）
            newImage = new BufferedImage(imgWidth, imgHeight, templateImage.getType());
            g2d = newImage.createGraphics();

            // 2. 绘制模板图片（基础内容）
            g2d.drawImage(templateImage, 0, 0, imgWidth, imgHeight, null);

            // 3. 配置序号样式
            configureTextStyle(g2d);

            // 4. 计算序号位置
            String serialText = String.valueOf(serial);
            Point textPos = calculateTextPosition(g2d, serialText, imgWidth, imgHeight);

            // 5. 绘制序号（先描边后填色）
            g2d.setColor(TEXT_STROKE_COLOR);
            g2d.drawString(serialText, textPos.x, textPos.y);
            g2d.setColor(TEXT_COLOR);
            g2d.drawString(serialText, textPos.x, textPos.y);

            // 6. 保存图片到磁盘
            ImageIO.write(newImage, imageFormat, imageFile);

            // 打印成功日志（包含线程名，方便排查问题）
            System.out.printf("✅ [线程：%s] 生成成功：%s | 大小：%.2fKB | 位置：(%d,%d)%n",
                    Thread.currentThread().getName(),
                    imageName,
                    imageFile.length() / 1024.0,
                    textPos.x, textPos.y);

        } finally {
            // 强制释放资源（关键：避免多线程内存泄漏）
            if (g2d != null) {
                g2d.dispose();
            }
            // BufferedImage无close方法，置为null让GC回收
            newImage = null;
        }
    }


    // ------------------- 以下方法与原逻辑一致，仅适配多线程共享变量 -------------------
    private static void configureTextStyle(Graphics2D g2d) {
        Font textFont = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
        g2d.setFont(textFont);

        Stroke textStroke = new BasicStroke(TEXT_STROKE_WIDTH);
        g2d.setStroke(textStroke);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private static Point calculateTextPosition(Graphics2D g2d, String text, int imgWidth, int imgHeight) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();

        if ("CENTER".equalsIgnoreCase(POSITION_MODE)) {
            int centerX = imgWidth / 2;
            int centerY = imgHeight / 2;
            int x = centerX - (textWidth / 2) + CENTER_OFFSET_X;
            int y = centerY + (textHeight / 2) + CENTER_OFFSET_Y;
            return new Point(x, y);
        } else if ("CUSTOM".equalsIgnoreCase(POSITION_MODE)) {
            return new Point(CUSTOM_POS_X, CUSTOM_POS_Y + textHeight);
        } else {
            System.out.println("ℹ️  位置模式配置错误，默认使用居中模式！");
            int x = (imgWidth - textWidth) / 2;
            int y = (imgHeight + textHeight) / 2;
            return new Point(x, y);
        }
    }

    private static String getImageFormat(String templatePath) {
        int dotIndex = templatePath.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == templatePath.length() - 1) {
            return "png";
        }
        return templatePath.substring(dotIndex + 1).toLowerCase();
    }
}