package com.noob.base.batchDataHandler.docHandle.v4_optimize_batchAndGM;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 文稿合并
 */
public class DocxMerger {
    private static final double A4_WIDTH_POINTS = 595;
    private static final double A4_HEIGHT_POINTS = 842;
    private static final double MARGIN_HORIZONTAL_POINTS = 90;
    private static final double MARGIN_VERTICAL_POINTS = 72;

    public static void mergeHandler(String targetDir) throws IOException, InvalidFormatException {
        Path dirPath = Paths.get(targetDir);
        List<File> files = getSortedDocxFiles(dirPath);

        try (XWPFDocument mergedDoc = new XWPFDocument()) {
            createTocPage(mergedDoc);

            for (File file : files) {
                mergeDocument(mergedDoc, file);
            }

            saveResult(mergedDoc, dirPath);
        }
    }

    private static void createTocPage(XWPFDocument doc) {
        // 创建目录标题段落
        XWPFParagraph titlePara = doc.createParagraph();
        titlePara.setStyle("Heading1");
        XWPFRun titleRun = titlePara.createRun();
        titleRun.setText("文档目录");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // 创建目录内容段落
        XWPFParagraph tocPara = doc.createParagraph();
        tocPara.setStyle("TOC");

        // 添加目录字段（实际目录将在Word打开时自动生成）
        CTP ctp = tocPara.getCTP();
        CTSimpleField tocField = ctp.addNewFldSimple();
        tocField.setInstr("TOC \\o \"1-3\" \\h \\z \\u");
        tocField.setDirty(STOnOff.ON);

        // 添加分页符
        doc.createParagraph().createRun().addBreak(BreakType.PAGE);
    }


    private static List<File> getSortedDocxFiles(Path dirPath) throws IOException {
        /*
        return Files.list(dirPath)
                .filter(p -> p.toString().endsWith(".docx"))
                .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                .map(Path::toFile)
                .collect(Collectors.toList());
         */
        // 文件排序规则：按照文件编号自然排序
        // 预编译正则表达式，提升性能
        Pattern pattern = Pattern.compile("\\d+");

        return Files.list(dirPath)
                .filter(p -> p.toString().toLowerCase().endsWith(".docx"))  // 忽略大小写
                .sorted(Comparator.comparing(p -> {
                    String name = p.getFileName().toString();
                    // 使用 Matcher 实现数字补零对齐
                    Matcher matcher = pattern.matcher(name);
                    StringBuffer sb = new StringBuffer();
                    while (matcher.find()) {
                        String numStr = matcher.group();
                        String paddedNum = String.format("%010d", Integer.parseInt(numStr));
                        matcher.appendReplacement(sb, paddedNum);
                    }
                    matcher.appendTail(sb);
                    return sb.toString();
                }))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private static void mergeDocument(XWPFDocument mergedDoc, File file)
            throws IOException, InvalidFormatException {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument doc = new XWPFDocument(fis)) {
            copyContent(mergedDoc, doc);
            mergedDoc.createParagraph().createRun().addBreak(BreakType.PAGE);
        }
    }

    private static void copyContent(XWPFDocument dest, XWPFDocument src)
            throws InvalidFormatException, IOException {
        Map<String, String> picMap = transferPictures(dest, src);

        for (IBodyElement element : src.getBodyElements()) {
            if (element instanceof XWPFParagraph) {
                copyParagraph(dest, (XWPFParagraph) element, picMap);
            } else if (element instanceof XWPFTable) {
                copyTable(dest, (XWPFTable) element, picMap);
            }
        }
    }

    private static Map<String, String> transferPictures(XWPFDocument dest, XWPFDocument src) throws InvalidFormatException {
        Map<String, String> mapping = new HashMap<>();
        for (XWPFPictureData pic : src.getAllPictures()) {
            String newId = dest.addPictureData(pic.getData(), pic.getPictureType());
            mapping.put(pic.getFileName(), newId);
        }
        return mapping;
    }

    private static void copyParagraph(XWPFDocument dest, XWPFParagraph srcPara,
                                      Map<String, String> picMap) throws InvalidFormatException, IOException {
        XWPFParagraph newPara = dest.createParagraph();
        newPara.getCTP().setPPr(srcPara.getCTP().getPPr());
        if (srcPara.getStyle() != null) {
            newPara.setStyle(srcPara.getStyle());
        }

        for (XWPFRun srcRun : srcPara.getRuns()) {
            XWPFRun newRun = newPara.createRun();
            newRun.getCTR().setRPr(srcRun.getCTR().getRPr());
            newRun.setText(srcRun.getText(0));
            copyPictures(newRun, srcRun, picMap);
        }
    }

    private static void copyPictures(XWPFRun destRun, XWPFRun srcRun,
                                     Map<String, String> picMap) throws InvalidFormatException, IOException {
        for (XWPFPicture srcPic : srcRun.getEmbeddedPictures()) {
            String newId = picMap.get(srcPic.getPictureData().getFileName());
            if (newId != null) {
                copyPicture(destRun, srcPic, newId);
            }
        }
    }

    private static void copyPicture(XWPFRun destRun, XWPFPicture srcPic, String newId)
            throws InvalidFormatException, IOException {
        long origWidth = srcPic.getCTPicture().getSpPr().getXfrm().getExt().getCx();
        long origHeight = srcPic.getCTPicture().getSpPr().getXfrm().getExt().getCy();

        double[] scaledSize = calculatePictureSize(
                Units.toPoints(origWidth),
                Units.toPoints(origHeight)
        );

        destRun.addPicture(
                new ByteArrayInputStream(srcPic.getPictureData().getData()),
                srcPic.getPictureData().getPictureType(),
                newId,
                Units.toEMU(scaledSize[0]),  // 使用数组索引获取宽度
                Units.toEMU(scaledSize[1])   // 使用数组索引获取高度
        );
    }


    private static double[] calculatePictureSize(double width, double height) {
        double maxWidth = A4_WIDTH_POINTS - 2 * MARGIN_HORIZONTAL_POINTS;
        double maxHeight = A4_HEIGHT_POINTS - 2 * MARGIN_VERTICAL_POINTS;

        double widthRatio = maxWidth / width;
        double heightRatio = maxHeight / height;
        double scale = Math.min(Math.min(widthRatio, heightRatio), 1.0);

        return new double[]{width * scale, height * scale};
    }

    private static void copyTable(XWPFDocument dest, XWPFTable srcTable,
                                  Map<String, String> picMap) throws InvalidFormatException, IOException {
        XWPFTable newTable = dest.createTable();
        newTable.getCTTbl().setTblPr(srcTable.getCTTbl().getTblPr());

        for (XWPFTableRow srcRow : srcTable.getRows()) {
            XWPFTableRow newRow = newTable.createRow();
            newRow.getCtRow().setTrPr(srcRow.getCtRow().getTrPr());

            for (XWPFTableCell srcCell : srcRow.getTableCells()) {
                XWPFTableCell newCell = newRow.addNewTableCell();
                newCell.getCTTc().setTcPr(srcCell.getCTTc().getTcPr());
                copyCellContent(dest, srcCell, picMap);
            }
        }
    }

    private static void copyCellContent(XWPFDocument dest, XWPFTableCell srcCell,
                                        Map<String, String> picMap) throws InvalidFormatException, IOException {
        for (IBodyElement elem : srcCell.getBodyElements()) {
            if (elem instanceof XWPFParagraph) {
                copyParagraph(dest, (XWPFParagraph) elem, picMap);
            } else if (elem instanceof XWPFTable) {
                copyTable(dest, (XWPFTable) elem, picMap);
            }
        }
    }

    private static void saveResult(XWPFDocument doc, Path dirPath) throws IOException {
        Path outputPath = dirPath.resolve("merged_" + System.currentTimeMillis() + ".docx");
        try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
            doc.write(out);
        }
    }
}
