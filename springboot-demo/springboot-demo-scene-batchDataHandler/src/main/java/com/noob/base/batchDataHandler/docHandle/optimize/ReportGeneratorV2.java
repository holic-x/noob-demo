package com.noob.base.batchDataHandler.docHandle.optimize;

import com.noob.base.batchDataHandler.docHandle.DocxUtil;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import com.noob.base.batchDataHandler.docHandle.v1.MockFileService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 报告生成器：将核查结果及图片插入Word文档
 * - 优化版本：批次处理优化
 */
public class ReportGeneratorV2 {

    // 模拟文件处理服务
    private MockBatchFileService fileService;

    public ReportGeneratorV2(MockBatchFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 生成核查报告
     *
     * @param checkResults 核查结果列表
     * @param outputPath   输出文档路径
     * @throws Exception
     */
    /*
    public void generateReport(List<CheckResult> checkResults, String outputPath) throws Exception {
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

            // 网站状态、备注（每条信息用addBreak分隔，避免setText覆盖）
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
                            try (InputStream imageStream = fileService.getFileStreamByFileName(file.getFileName())) {
                                // 指定插入图片的最大宽度、最大高度
                                DocxUtil.insertPicture(doc, imageStream, file.getFileName(), 400, 200); // 600 400
                            } catch (Exception e) {
                                XWPFParagraph errPara = doc.createParagraph();
                                XWPFRun errRun = errPara.createRun();
                                errRun.setColor("FF0000");
                                errRun.setText("图片插入失败：" + file.getFileName() + "，异常：" + e.getMessage());
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
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            doc.write(out);
        }
    }

     */
}