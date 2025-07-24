package com.noob.base.batchDataHandler.docHandle.v4_optimize_batchAndGM;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DocxMerger {
    public static void mergeHandler(String targetDir) throws Exception {
        // 获取排序后的文件列表
        List<File> files = Files.list(Paths.get(targetDir))
                .filter(p -> p.toString().toLowerCase().endsWith(".docx"))
                .sorted()
                .map(Path::toFile)
                .collect(Collectors.toList());

        // 创建主文档
        XWPFDocument mergedDoc = new XWPFDocument();

        // 添加目录页（POI4.1.0需手动设置样式）
        XWPFParagraph tocTitle = mergedDoc.createParagraph();
        tocTitle.setStyle("Heading1");
        tocTitle.createRun().setText("目录");
        mergedDoc.createParagraph().createRun().addBreak(BreakType.PAGE);

        // 合并文档内容
        for (File file : files) {
            try (FileInputStream fis = new FileInputStream(file)) {
                XWPFDocument doc = new XWPFDocument(fis);
                copyContent(mergedDoc, doc);
            }
        }

        // 保存结果
        try (FileOutputStream out = new FileOutputStream(
                targetDir + File.separator + "merged_result.docx")) {
            mergedDoc.write(out);
        }
    }

    private static void copyContent(XWPFDocument dest, XWPFDocument src) {
        for (IBodyElement element : src.getBodyElements()) {
            if (element instanceof XWPFParagraph) {
                XWPFParagraph srcPara = (XWPFParagraph) element;
                XWPFParagraph newPara = dest.createParagraph();
                newPara.getCTP().setPPr((CTPPr) srcPara.getCTP().getPPr().copy());
                for (XWPFRun run : srcPara.getRuns()) {
                    XWPFRun newRun = newPara.createRun();
                    newRun.setText(run.getText(0));
                    newRun.getCTR().setRPr((CTRPr) run.getCTR().getRPr().copy());
                }
            } else if (element instanceof XWPFTable) {
                XWPFTable srcTable = (XWPFTable) element;
                XWPFTable newTable = dest.createTable();
                newTable.getCTTbl().setTblPr((CTTblPr) srcTable.getCTTbl().getTblPr().copy());

                for (XWPFTableRow srcRow : srcTable.getRows()) {
                    XWPFTableRow newRow = newTable.createRow();
                    newRow.getCtRow().setTrPr((CTTrPr) srcRow.getCtRow().getTrPr().copy());

                    for (XWPFTableCell srcCell : srcRow.getTableCells()) {
                        XWPFTableCell newCell = newRow.createCell();
                        newCell.getCTTc().setTcPr((CTTcPr) srcCell.getCTTc().getTcPr().copy());

                        for (IBodyElement cellElem : srcCell.getBodyElements()) {
                            if (cellElem instanceof XWPFParagraph) {
                                XWPFParagraph newPara = newCell.addParagraph();
                                newPara.getCTP().setPPr((CTPPr) ((XWPFParagraph) cellElem).getCTP().getPPr().copy());
                                for (XWPFRun run : ((XWPFParagraph) cellElem).getRuns()) {
                                    XWPFRun newRun = newPara.createRun();
                                    newRun.setText(run.getText(0));
                                    newRun.getCTR().setRPr((CTRPr) run.getCTR().getRPr().copy());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
