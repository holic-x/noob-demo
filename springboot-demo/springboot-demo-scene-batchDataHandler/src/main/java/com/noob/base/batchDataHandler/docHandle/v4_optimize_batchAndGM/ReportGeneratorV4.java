package com.noob.base.batchDataHandler.docHandle.v4_optimize_batchAndGM;

import com.noob.base.batchDataHandler.docHandle.DocxUtil;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet.MockBatchFileService;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 报告生成器：将核查结果及图片插入Word文档
 * - 优化版本：批量获取 + 分组合并处理文稿
 */
public class ReportGeneratorV4 {

    // 模拟文件处理服务
    private MockBatchFileService fileService;

    public ReportGeneratorV4(MockBatchFileService fileService) {
        this.fileService = fileService;
    }

    @SneakyThrows
    private Map<String, InputStream> getFileStreamMap(List<CheckResult> checkResults) {
        // 1. 收集核查结果列表的所有截图文件
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (CheckResult checkResult : checkResults) {
            List<ResultItem> items = checkResult.getResults();
            if (items != null && !items.isEmpty()) {
                for (ResultItem item : items) {
                    List<FileInfo> files = item.getFiles();
                    if (files != null && !files.isEmpty()) {
                        fileInfoList.addAll(files);
                    }
                }
            }
        }

        // 2. 批量获取所有图片流封装为Map
        Map<String, InputStream> fileStreamMap = fileService.getFileStreamByFileInfo_forBatch(fileInfoList);
        System.out.println("当前批次图片流数据个数为：" + fileStreamMap.size());

        // 返回构建结果
        return fileStreamMap;
    }

    /**
     * 根据当前批次数据生成文稿
     */
    @SneakyThrows
    private void createDocx(List<CheckResult> checkResults, Map<String, InputStream> fileStreamMap, String outputPath) {
        // 构建文稿
        XWPFDocument doc = new XWPFDocument();

        int webSiteNum = 1;
        for (CheckResult checkResult : checkResults) {
            // 网站大标题
            XWPFParagraph siteTitle = doc.createParagraph();
            siteTitle.setStyle("Heading1");
            XWPFRun siteRun = siteTitle.createRun();
            siteRun.setBold(true);
            siteRun.setFontSize(16);
            siteRun.setText(String.format("%d. %s（%s）", webSiteNum, checkResult.getWebName(), checkResult.getWebKey()));

            // 网站状态、备注
            XWPFParagraph statusPara = doc.createParagraph();
            XWPFRun statusRun = statusPara.createRun();
            statusRun.setText("核查网站webKey：" + checkResult.getWebKey());
            statusRun.addBreak();
            statusRun.setText("核查网站webName：" + checkResult.getWebName());
            statusRun.addBreak();
            statusRun.setText("核查状态：" + checkResult.getStatus());
            if (checkResult.getNote() != null) {
                statusRun.addBreak();
                statusRun.setText("备注：" + checkResult.getNote());
            }

            // 空行
            doc.createParagraph();

            List<ResultItem> items = checkResult.getResults();
            if (items != null && !items.isEmpty()) {
                int resultItemNum = 1;
                for (ResultItem item : items) {
                    // 子标题：核查结果编号
                    XWPFParagraph itemTitle = doc.createParagraph();
                    itemTitle.setStyle("Heading2");
                    XWPFRun itemRun = itemTitle.createRun();
                    itemRun.setBold(true);
                    itemRun.setFontSize(13);
                    String title = item.getKey() == null ? "" : (item.getKey().getIdNo() + "-" + item.getKey().getName());
                    itemRun.setText(String.format("%d.%d 【%s】核查结果", webSiteNum, resultItemNum, title));

                    // 企业信息
                    KeyInfo key = item.getKey();
                    XWPFParagraph keyPara = doc.createParagraph();
                    XWPFRun keyRun = keyPara.createRun();
                    keyRun.setText("企业名称：" + (key != null ? key.getName() : ""));
                    keyRun.addBreak();
                    keyRun.setText("统一社会信用代码：" + (key != null ? key.getIdNo() : ""));

                    // 截图
                    List<FileInfo> files = item.getFiles();
                    if (files != null && !files.isEmpty()) {
                        for (FileInfo file : files) {
                            // 从核查文件结果集中获取对应的截图数据
                            InputStream imageStream = fileStreamMap.get(file.getFileKey());
                            if (imageStream != null) {
                                try {
                                    DocxUtil.insertPicture(doc, imageStream, file.getFileName(), 400, 200);
                                } catch (Exception e) {
                                    XWPFParagraph errPara = doc.createParagraph();
                                    XWPFRun errRun = errPara.createRun();
                                    errRun.setColor("FF0000");
                                    errRun.setText("图片插入失败：" + file.getFileName() + "，异常：" + e.getMessage());
                                } finally {
                                    try {
                                        imageStream.close();
                                    } catch (Exception ignore) {
                                    }
                                }
                            } else {
                                XWPFParagraph errPara = doc.createParagraph();
                                XWPFRun errRun = errPara.createRun();
                                errRun.setColor("FF0000");
                                errRun.setText("图片未找到：" + file.getFileName());
                            }
                        }
                    }

                    // 分隔线
                    DocxUtil.addHorizontalLine(doc);

                    resultItemNum++;
                }
            } else {
                XWPFParagraph noResultPara = doc.createParagraph();
                noResultPara.createRun().setText("无核查结果数据。");
            }

            // 网站之间空行
            doc.createParagraph();
            webSiteNum++;
        }

        // 保存文档
        /*
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            doc.write(out);
        }
         */

        // 安全保存文档（校验父目录是否存在，如果不存在则需构建目录）
        writeWithParentCheck(doc, outputPath);
    }

    /**
     * 文档保存
     * @param doc
     * @param outputPath
     * @throws IOException
     */
    public static void writeWithParentCheck(XWPFDocument doc, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        File parentDir = outputFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create parent directories: " + parentDir.getAbsolutePath());
            }
        }

        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            doc.write(out);
        }
    }


    /**
     * 生成核查报告
     *
     * @param checkResults 核查结果列表
     * @param outPutDir    输出文档目录路径
     * @param fileName     输出文档名称
     * @throws Exception
     */
    public void generateReport(List<CheckResult> checkResults, String outPutDir, String fileName) throws Exception {

        // 1.业务分组（按照核查网站进行业务分组，划分批次）
        Map<String, List<CheckResult>> groupByWeb = checkResults.stream().collect(Collectors.groupingBy(CheckResult::getWebKey));

        // 2.分组批次处理：例如一次处理5-10个网站的核查数据，或者直接按照核查网站分组作为批次进行处理（1个核查网站关联核查数据生成一个文稿）
        String batchId = UUID.randomUUID().toString().replaceAll("-", "");
        String targetBatchDir = outPutDir + File.separator + batchId;
        Files.createDirectories(Paths.get(targetBatchDir)); // 确保目录存在

        // 3.1 顺序处理
        /*
        int num = 1;
        for (Map.Entry<String, List<CheckResult>> entry : groupByWeb.entrySet()) {
            String webKey = entry.getKey();
            List<CheckResult> checkResultList = entry.getValue();
            System.out.println(String.format("当前处理批次：%d, 处理网站为：%s, 关联核查数据记录：%s", num, webKey, checkResultList.size()));
            String subFileName = targetBatchDir + File.separator + String.format("%s_%d.docx", batchId, num);
            partitionHandler(checkResultList, subFileName);

            // 当前批次处理完成
            num++;
        }
         */

        // 3.2 使用并行流处理每个分组
        AtomicInteger num = new AtomicInteger(1);
        groupByWeb.entrySet().parallelStream().forEach(entry -> {
            String webKey = entry.getKey();
            List<CheckResult> checkResultList = entry.getValue();
            System.out.println(String.format(
                    "[线程 %s] 处理批次：%d, 网站：%s, 记录数：%d",
                    Thread.currentThread().getName(),
                    num.getAndIncrement(),
                    webKey,
                    checkResultList.size()
            ));

            String subFileName = targetBatchDir + File.separator + String.format("%s_%d.docx", batchId, num.get());
            try {
                partitionHandler(checkResultList, subFileName);
            } catch (Exception e) {
                System.err.println("处理失败: " + webKey);
                e.printStackTrace();
            }
        });

        // 4.合并分组处理数据（需确保所有线程已完成）
        DocxMerger.mergeHandler(targetBatchDir);
    }

    /**
     * 分批处理生成文稿
     *
     * @param checkResults
     * @return
     */
    private void partitionHandler(List<CheckResult> checkResults, String targetFilePath) {

        // 获取截图数据Map
        long startTime1 = System.currentTimeMillis();
        Map<String, InputStream> fileStreamMap = getFileStreamMap(checkResults);
        long endTime1 = System.currentTimeMillis();

        // 处理生成文稿
        long startTime2 = System.currentTimeMillis();
        createDocx(checkResults, fileStreamMap, targetFilePath);
        long endTime2 = System.currentTimeMillis();

        System.out.println("本次截图数据获取处理耗时：" + (endTime1 - startTime1) + "ms");
        System.out.println("本次文稿处理耗时：" + (endTime2 - startTime2) + "ms");

    }

}