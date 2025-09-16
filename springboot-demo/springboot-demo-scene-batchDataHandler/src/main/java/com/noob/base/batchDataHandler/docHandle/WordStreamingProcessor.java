package com.noob.base.batchDataHandler.docHandle;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xwpf.usermodel.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Word文档流式处理器，用于低内存占用处理大型或批量Word文档
 */
public class WordStreamingProcessor {
    // 配置参数
    private static final int CHUNK_SIZE = 100; // 每次处理的段落数量
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors(); // 线程池大小

    private final String inputDir;
    private final String outputDir;

    public WordStreamingProcessor(String inputDir, String outputDir) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;

        // 确保输出目录存在
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 批量处理目录中的所有Word文档
     */
    public void processAllDocuments() {
        File dir = new File(inputDir);
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".docx"));

        if (files == null || files.length == 0) {
            System.out.println("未找到任何docx文件");
            return;
        }

        // 使用线程池并行处理多个文档，但控制并发数避免内存峰值过高
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (File file : files) {
            executor.submit(() -> processSingleDocument(file.getAbsolutePath()));
        }

        executor.shutdown();
        try {
            // 等待所有任务完成
            executor.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("处理被中断: " + e.getMessage());
        }
    }

    /**
     * 流式处理单个Word文档
     */
    public void processSingleDocument(String filePath) {
        OPCPackage opcPackage = null;
        XWPFDocument document = null;

        try {
            System.out.println("开始处理文档: " + filePath);

            // 使用OPCPackage打开文档，设置为只读模式
            opcPackage = OPCPackage.open(new File(filePath), PackageAccess.READ);
            document = new XWPFDocument(opcPackage);

            // 获取文档中的段落迭代器
            Iterator<XWPFParagraph> paragraphIterator = document.getParagraphsIterator();

            // 分块处理段落
            processParagraphsInChunks(paragraphIterator, filePath);

            // 处理表格（单独处理，因为表格可能不包含在段落迭代器中）
            processTables(document, filePath);

            System.out.println("文档处理完成: " + filePath);

        } catch (Exception e) {
            System.err.println("处理文档 " + filePath + " 时出错: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 强制关闭资源，释放内存
            try {
                if (document != null) {
                    document.close();
                }
                if (opcPackage != null) {
                    opcPackage.close();
                }
            } catch (Exception e) {
                System.err.println("关闭文档资源时出错: " + e.getMessage());
            }

            // 提示垃圾回收器工作
            System.gc();
        }
    }

    /**
     * 分块处理段落
     */
    private void processParagraphsInChunks(Iterator<XWPFParagraph> iterator, String sourceFilePath) {
        int chunkNumber = 1;
        int count = 0;

        // 创建临时缓冲区存储当前块
        StringBuilder chunkBuffer = new StringBuilder();

        while (iterator.hasNext()) {
            XWPFParagraph paragraph = iterator.next();
            String text = paragraph.getText();

            // 处理段落文本
            if (!text.isEmpty()) {
                chunkBuffer.append(processText(text)).append("\n");
            }

            count++;

            // 当达到块大小或没有更多段落时，处理当前块
            if (count >= CHUNK_SIZE || !iterator.hasNext()) {
                writeChunkToFile(chunkBuffer.toString(), sourceFilePath, "paragraphs", chunkNumber);

                // 重置缓冲区
                chunkBuffer.setLength(0);
                chunkNumber++;
                count = 0;

                // 每处理完一个块，提示GC工作
                System.gc();
            }
        }
    }

    /**
     * 处理表格
     */
    private void processTables(XWPFDocument document, String sourceFilePath) {
        List<XWPFTable> tables = document.getTables();
        if (tables.isEmpty()) {
            return;
        }

        int tableNumber = 1;
        for (XWPFTable table : tables) {
            StringBuilder tableData = new StringBuilder();

            // 逐行处理表格，避免一次性加载整个表格
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    tableData.append(processText(cell.getText())).append("\t");
                }
                tableData.append("\n");
            }

            // 写入表格数据
            writeChunkToFile(tableData.toString(), sourceFilePath, "tables", tableNumber);
            tableNumber++;

            // 处理完一个表格就释放内存
            tableData.setLength(0);
            System.gc();
        }
    }

    /**
     * 处理文本内容（可根据实际需求扩展）
     */
    private String processText(String text) {
        // 示例：简单处理，去除多余空格
        return text.replaceAll("\\s+", " ").trim();
    }

    /**
     * 将处理后的块写入文件，避免内存堆积
     */
    private void writeChunkToFile(String content, String sourceFilePath, String type, int chunkNumber) {
        try {
            String fileName = new File(sourceFilePath).getName().replace(".docx", "")
                    + "_" + type + "_chunk_" + chunkNumber + ".txt";
            File outputFile = new File(outputDir, fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(content);
            }
        } catch (IOException e) {
            System.err.println("写入块数据时出错: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 示例用法
        String inputDirectory = "path/to/input/documents";
        String outputDirectory = "path/to/output/processed";

        WordStreamingProcessor processor = new WordStreamingProcessor(inputDirectory, outputDirectory);
        processor.processAllDocuments();
    }
}
