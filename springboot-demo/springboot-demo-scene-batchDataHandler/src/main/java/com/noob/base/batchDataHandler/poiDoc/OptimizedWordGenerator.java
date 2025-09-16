package com.noob.base.batchDataHandler.poiDoc;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 主程序入口类（支持三级标题和多图片）
 * 职责：协调所有组件，控制整体流程（加载数据→创建文档→添加目录→处理内容→保存文档）
 */
public class OptimizedWordGenerator {
    // -------------------------- 可配置参数 --------------------------
    /**
     * 总业务组数
     */
    private static final int TOTAL_BUSINESS_GROUPS = 300; // 300就触发oom
    /**
     * 图片读取线程数
     */
    private static final int READ_THREAD_COUNT = Math.min(Runtime.getRuntime().availableProcessors(), 4);
    /**
     * 进度打印间隔
     */
    private static final int PROGRESS_INTERVAL = 10;
    /**
     * 图片读取超时时间(分钟)
     */
    private static final int READ_TIMEOUT_MINUTES = 10;


    // -------------------------- 路径配置 --------------------------
    private final String baseDir;
    private final String imageDir;
    private final String outputPath;


    // -------------------------- 图片样式配置 --------------------------
    private final String imagePrefix = "mock_image_";
    private final String imageSuffix = ".png";
    private final int imageWidth = 500;  // 像素
    private final int imageHeight = 300; // 像素


    /**
     * 构造函数 - 支持自定义路径配置
     */
    public OptimizedWordGenerator() {
        this(
                "E:\\test\\test-files\\batch_test_ver02",
                "batch_images",
                "output"
        );
    }

    /**
     * 自定义路径构造函数
     *
     * @param baseDir      基础目录
     * @param imageSubDir  图片子目录
     * @param outputSubDir 输出子目录
     */
    public OptimizedWordGenerator(String baseDir, String imageSubDir, String outputSubDir) {
        this.baseDir = baseDir;
        this.imageDir = baseDir + java.io.File.separator + imageSubDir + java.io.File.separator;
        this.outputPath = baseDir + java.io.File.separator + outputSubDir + java.io.File.separator +
                "business_doc_v3_" + System.currentTimeMillis() + ".docx";
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            new OptimizedWordGenerator().startBatchGeneration();
        } catch (Exception e) {
            System.err.println("批量生成失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            long totalTime = System.currentTimeMillis() - startTime;
            System.out.println("\n===== 批量文档生成结束 =====");
            System.out.println("总耗时：" + formatTime(totalTime));
            System.out.println("提示：打开文档后按「F9」更新目录");
        }
    }


    /**
     * 核心流程：批量生成Word文档
     */
    public void startBatchGeneration() throws Exception {
        // 步骤1：加载业务数据
        System.out.println("===== 步骤1：加载业务数据 =====");
        List<BusinessImageDTO> allBusinessData = BusinessDataProcessor.generateMockData(
                TOTAL_BUSINESS_GROUPS, imageDir, imagePrefix, imageSuffix
        );
        int totalDataSize = allBusinessData.size();
        System.out.println("业务数据加载完成：共" + totalDataSize + "个二级单元");

        if (totalDataSize == 0) {
            System.out.println("无业务数据可处理，程序退出");
            return;
        }

        // 步骤2：创建文档并添加目录
        System.out.println("===== 步骤2：创建文档并添加目录 =====");
        XWPFDocument wordDoc = WordImageTool.createEmptyDoc();
        WordImageTool.addTableOfContentsAtFirst(wordDoc, "目录");

        // 步骤3：处理数据
        int totalSuccess = 0;

        // 子步骤1：多线程读取图片
        long readStart = System.currentTimeMillis();
        int readSuccess = multiThreadReadImage(allBusinessData);
        long readTime = System.currentTimeMillis() - readStart;
        System.out.printf("\n图片读取完成：耗时%s，成功%d个单元\n",
                formatTime(readTime), readSuccess);

        // 子步骤2：写入Word
        long writeStart = System.currentTimeMillis();
        totalSuccess = writeToWord(wordDoc, allBusinessData);
        long writeTime = System.currentTimeMillis() - writeStart;
        System.out.printf("文档写入完成：耗时%s，成功%d个单元\n",
                formatTime(writeTime), totalSuccess);

        // 子步骤3：清理内存
        clearMemory(allBusinessData);

        // 步骤4：保存文档
        System.out.println("\n===== 步骤4：保存文档 =====");
        WordImageTool.saveDoc(wordDoc, outputPath);
        System.out.println("文档已保存至：" + outputPath);
        System.out.println("总成功生成：" + totalSuccess + "/" + totalDataSize + "个二级单元");
    }



    /*
    public void startBatchGeneration() throws Exception {
        // 步骤1：加载所有业务数据（仅加载路径，不加载图片）
        List<BusinessImageDTO> allBusinessData = BusinessDataProcessor.generateMockData(  TOTAL_BUSINESS_GROUPS, imageDir, imagePrefix, imageSuffix);
        int totalDataSize = allBusinessData.size();
        if (totalDataSize == 0) return;

        // 步骤2：创建文档并添加目录
        XWPFDocument wordDoc = WordImageTool.createEmptyDoc();
        WordImageTool.addTableOfContentsAtFirst(wordDoc, "目录");

        // 关键：设置批次大小（根据内存调整，例如每次处理5个二级单元）
        int batchSize = 5;
        int totalSuccess = 0;

        // 分批处理：加载图片→写入文档→释放内存
        for (int i = 0; i < totalDataSize; i += batchSize) {
            int end = Math.min(i + batchSize, totalDataSize);
            List<BusinessImageDTO> batchData = allBusinessData.subList(i, end);

            // 子步骤1：读取当前批次的图片（仅加载这一批的图片字节）
            int readSuccess = multiThreadReadImage(batchData);

            // 子步骤2：将当前批次写入文档（立即写入磁盘缓冲）
            int writeSuccess = writeToWord(wordDoc, batchData);
            totalSuccess += writeSuccess;

            // 子步骤3：立即释放当前批次的图片内存（关键！）
            clearMemory(batchData);
        }

        // 最后统一保存文档（此时内存中只有少量数据）
        WordImageTool.saveDoc(wordDoc, outputPath);
    }


     */


    // 修复后的方法
    private int multiThreadReadImage(List<BusinessImageDTO> allData) throws InterruptedException {
        if (allData.isEmpty()) return 0;

        // 计算子批次大小，确保每个批次负载均衡
        int subBatchSize = (int) Math.ceil((double) allData.size() / READ_THREAD_COUNT);
        List<List<BusinessImageDTO>> subBatches = allData.stream()
                .collect(Collectors.groupingBy(dto -> allData.indexOf(dto) / subBatchSize))
                .values().stream()
                .collect(Collectors.toList());

        // 手动管理线程池生命周期（ExecutorService不支持try-with-resources）
        ExecutorService readExecutor = Executors.newFixedThreadPool(READ_THREAD_COUNT);
        try {
            // 提交所有任务并获取Future
            List<Future<?>> futures = subBatches.stream()
                    .map(subBatch -> readExecutor.submit(() ->
                            BusinessDataProcessor.batchReadImageWithPlaceholder(
                                    subBatch, imageWidth, imageHeight  // 修复常量名大写
                            )
                    ))
                    .collect(Collectors.toList());

            // 等待所有任务完成或超时
            for (Future<?> future : futures) {
                try {
                    // 修复超时时间常量（需定义READ_TIMEOUT_MINUTES）
                    future.get(READ_TIMEOUT_MINUTES, TimeUnit.MINUTES);
                } catch (TimeoutException e) {
                    System.err.println("图片读取超时，任务被中断");
                    future.cancel(true);
                } catch (ExecutionException e) {
                    System.err.println("图片处理任务执行异常: " + e.getCause().getMessage());
                }
            }
        } finally {
            // 确保线程池正确关闭
            readExecutor.shutdown();
            if (!readExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                readExecutor.shutdownNow();
            }
        }

        return (int) allData.stream()
                .filter(BusinessImageDTO::isProcessSuccess)
                .count();
    }

    /**
     * 写入Word文档（优化索引计算和异常处理）
     */
    private int writeToWord(XWPFDocument doc, List<BusinessImageDTO> allData) {
        int writeSuccessCount = 0;
        // 预计算索引映射，避免多次调用indexOf带来的性能损耗
        final int[] dataIndex = {0}; // 使用数组实现闭包可修改

        for (BusinessImageDTO dto : allData) {
            final int currentDataIndex = dataIndex[0]++; // 预计算当前索引

            if (!dto.isProcessSuccess()) {
                continue;
            }

            try {
                // 1. 写入二级标题
                WordImageTool.addTitleWithBuiltinStyle(
                        doc,
                        dto.getWebsiteName(),
                        "Heading2",
                        14,
                        STJc.LEFT
                );

                // 2. 写入三级核查项
                List<CheckItem> checkItems = dto.getCheckItems();
                for (int itemIndex = 0; itemIndex < checkItems.size(); itemIndex++) {
                    CheckItem checkItem = checkItems.get(itemIndex);

                    // 2.1 写入三级标题
                    WordImageTool.addTitleWithBuiltinStyle(
                            doc,
                            checkItem.getTitle(),
                            "Heading3",
                            12,
                            STJc.LEFT
                    );

                    // 2.2 写入核查详情
                    addCheckDetails(doc, checkItem.getCheckDetails());

                    // 2.3 写入图片
                    addCheckItemImages(doc, checkItem.getImageBytesList(),
                            currentDataIndex, itemIndex);
                }

                writeSuccessCount++;
                if (writeSuccessCount % PROGRESS_INTERVAL == 0) {
                    System.out.println("当前进度：已写入" + writeSuccessCount + "个二级单元");
                }

            } catch (Exception e) {
                System.err.println("写入失败（二级标题：" + dto.getWebsiteName() + "）：" + e.getMessage());
            }
        }

        return writeSuccessCount;
    }


    /**
     * 添加核查项详情（提取为独立方法，提高可读性）
     */
    private void addCheckDetails(XWPFDocument doc, List<String> details) {
        if (details == null || details.isEmpty()) return;

        for (String detail : details) {
            XWPFParagraph detailPara = doc.createParagraph();
            XWPFRun detailRun = detailPara.createRun();
            detailRun.setText("• " + detail);
            detailRun.setFontSize(10);
            detailRun.setFontFamily("微软雅黑");
            detailPara.setSpacingAfter(100);
        }
    }


    /**
     * 添加核查项图片（提取为独立方法，提高可读性）
     */
    private void addCheckItemImages(XWPFDocument doc, List<byte[]> imageBytesList,
                                    int dataIndex, int itemIndex) throws Exception {
        if (imageBytesList == null || imageBytesList.isEmpty()) return;

        for (int imgIndex = 0; imgIndex < imageBytesList.size(); imgIndex++) {
            WordImageTool.addCenteredImage(
                    doc,
                    imageBytesList.get(imgIndex),
                    dataIndex,
                    itemIndex * 100 + imgIndex,  // 确保图片名唯一
                    WordImageTool.toEMU(imageWidth),
                    WordImageTool.toEMU(imageHeight)
            );
        }
    }


    /**
     * 清理内存（增强版）
     */
    private void clearMemory(List<BusinessImageDTO> allData) {
        if (allData == null) return;

        for (BusinessImageDTO dto : allData) {
            if (dto == null) continue;

            // 清理核查项
            List<CheckItem> checkItems = dto.getCheckItems();
            if (checkItems != null) {
                for (CheckItem item : checkItems) {
                    if (item != null) {
                        item.setImageBytesList(null);
                        item.setCheckDetails(null);
                        item.setImagePaths(null);
                    }
                }
                checkItems.clear();
            }
            dto.setCheckItems(null);

            // 清理标题
            dto.setWebsiteName(null);
        }

        allData.clear();
        // 建议GC但不强制
        System.gc();
    }


    /**
     * 格式化时间显示（毫秒 → 分:秒格式）
     */
    private static String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%d分%d秒", minutes, seconds);
    }
}