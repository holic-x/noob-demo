package com.noob.base.batchDataHandler.poiDoc;



import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.apache.xmlbeans.XmlException;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

/**
 * 纯Word操作工具类（POI 4.1.0兼容）
 * 职责：提供基础的Word文档操作API（创建文档、添加标题、插入图片、生成目录、保存文档）
 * 特点：不包含业务逻辑，不依赖其他业务类，保持高度通用性
 */
public class WordImageTool {

    /**
     * 单位转换：像素 → EMU（POI中图片尺寸的单位）
     * 换算关系：1像素 = 9525 EMU
     * @param pixels 像素值
     * @return 转换后的EMU值（int类型，适配POI 4.1.0要求）
     */
    public static int toEMU(int pixels) {
        return pixels * 9525;
    }

    /**
     * 创建一个空的Word文档对象
     * @return 新的XWPFDocument对象
     */
    public static XWPFDocument createEmptyDoc() {
        return new XWPFDocument();
    }

    /**
     * 添加带内置样式的标题（用于生成目录）
     * @param doc 文档对象
     * @param titleText 标题文本内容
     * @param styleName Word内置样式名（如"Heading1"、"Heading2"、"Heading3"，严格区分大小写）
     * @param fontSize 字体大小（磅）
     * @param align 对齐方式（STJc.LEFT左对齐、STJc.CENTER居中、STJc.RIGHT右对齐）
     */
    public static void addTitleWithBuiltinStyle(XWPFDocument doc, String titleText,
                                                String styleName, int fontSize, STJc.Enum align) {
        // 创建段落对象
        XWPFParagraph titlePara = doc.createParagraph();

        // 应用Word内置样式（关键：样式名必须正确，这是目录识别的基础）
        titlePara.setStyle(styleName);

        // 创建文本运行对象（负责文本内容和样式）
        XWPFRun titleRun = titlePara.createRun();
        titleRun.setText(titleText);          // 设置标题文本
        titleRun.setBold(true);               // 标题默认加粗
        titleRun.setFontSize(fontSize);       // 设置字体大小
        titleRun.setFontFamily("微软雅黑");    // 设置字体（确保中文正常显示）

        // 设置段落对齐方式
        CTPPr pPr = titlePara.getCTP().getPPr();  // 获取段落属性对象
        if (pPr == null) {
            pPr = titlePara.getCTP().addNewPPr();  // 若不存在则创建
        }
        CTJc jc = pPr.getJc();                    // 获取对齐属性对象
        if (jc == null) {
            jc = pPr.addNewJc();                  // 若不存在则创建
        }
        jc.setVal(align);                         // 设置对齐方式

        // 设置段后间距（避免标题与后续内容粘连）
        titlePara.setSpacingAfter(200);  // 单位：twips（1磅=20twips，200=10磅）
    }

    /**
     * 添加三级标题下的子项（核查详情）
     * @param doc 文档对象
     * @param checkItems 子项列表
     */
    public static void addCheckItems(XWPFDocument doc, List<String> checkItems) {
        for (String item : checkItems) {
            XWPFParagraph itemPara = doc.createParagraph();
            // 设置段落左缩进（区分于标题）
            CTPPr pPr = itemPara.getCTP().getPPr();
            if (pPr == null) {
                pPr = itemPara.getCTP().addNewPPr();
            }

            // 设置左缩进（720twips = 36磅）
            CTInd ind = pPr.addNewInd(); // 创建缩进属性对象
            ind.setLeft(BigInteger.valueOf(720)); // 直接设置左缩进值（无需通过 addNewLeft()）

            // 添加项目符号
            XWPFRun itemRun = itemPara.createRun();
            itemRun.setText("• " + item);  // 前缀加项目符号
            itemRun.setFontSize(10);
            itemRun.setFontFamily("微软雅黑");
            itemPara.setSpacingAfter(100);  // 子项间留空
        }
    }

    /**
     * 插入居中显示的图片
     * @param doc 文档对象
     * @param imageBytes 图片字节数组（PNG格式）
     * @param blockIndex 块索引（用于生成唯一图片名）
     * @param imgIndex 图片在组内的索引（用于多图片区分）
     * @param widthEMU 图片宽度（EMU单位）
     * @param heightEMU 图片高度（EMU单位）
     * @throws Exception 图片插入失败时抛出异常
     */
    public static void addCenteredImage(XWPFDocument doc, byte[] imageBytes, int blockIndex, int imgIndex,
                                        int widthEMU, int heightEMU) throws Exception {
        // 创建图片所在的段落
        XWPFParagraph imgPara = doc.createParagraph();
        XWPFRun imgRun = imgPara.createRun();  // 创建文本运行对象（用于插入图片）

        // 设置图片段落居中对齐
        CTPPr pPr = imgPara.getCTP().getPPr();
        if (pPr == null) {
            pPr = imgPara.getCTP().addNewPPr();
        }
        CTJc jc = pPr.getJc();
        if (jc == null) {
            jc = pPr.addNewJc();
        }
        jc.setVal(STJc.CENTER);  // 居中对齐

        // 生成唯一图片名（避免重复导致的显示问题）
        String uniqueImgName = String.format("img_block%d_img%d_%s.png",
                blockIndex, imgIndex, UUID.randomUUID().toString().replace("-", "").substring(0, 8));

        // 步骤1：添加图片数据到文档（POI 4.1.0要求先添加数据）
        doc.addPictureData(new ByteArrayInputStream(imageBytes), Document.PICTURE_TYPE_PNG);

        // 步骤2：将图片插入到段落中
        imgRun.addPicture(
                new ByteArrayInputStream(imageBytes),
                Document.PICTURE_TYPE_PNG,  // 图片类型：PNG
                uniqueImgName,              // 图片名（仅内部使用）
                widthEMU,                   // 宽度（EMU）
                heightEMU                   // 高度（EMU）
        );

        // 设置图片下方间距（避免与下一组内容粘连）
        imgPara.setSpacingAfter(200);  // 200twips = 10磅
    }

    /**
     * 在文档最开头添加目录（支持自动更新，无需手动按F9）
     * 核心原理：通过POI生成目录后，强制设置目录域的"自动更新"属性
     * @param doc 文档对象（需在添加任何标题前调用）
     * @param tocTitle 目录标题文本（如"目录"）
     */
    public static void addTableOfContentsAtFirst(XWPFDocument doc, String tocTitle) {
        // 1. 添加目录标题（无样式，避免被目录自身索引）
        XWPFParagraph tocTitlePara = doc.createParagraph();
        XWPFRun tocTitleRun = tocTitlePara.createRun();
        tocTitleRun.setText(tocTitle);        // 目录标题文本
        tocTitleRun.setBold(true);            // 标题加粗
        tocTitleRun.setFontSize(16);          // 字体大小
        tocTitleRun.setFontFamily("微软雅黑");  // 字体

        // 目录标题居中对齐
        CTPPr tocTitlePPr = tocTitlePara.getCTP().getPPr();
        if (tocTitlePPr == null) {
            tocTitlePPr = tocTitlePara.getCTP().addNewPPr();
        }
        CTJc tocTitleJc = tocTitlePPr.getJc();
        if (tocTitleJc == null) {
            tocTitleJc = tocTitlePPr.addNewJc();
        }
        tocTitleJc.setVal(STJc.CENTER);  // 居中
        tocTitlePara.setSpacingAfter(200);  // 标题下方留白

        // 2. 添加目录域（核心：支持1-3级标题，自动更新）
        XWPFParagraph tocPara = doc.createParagraph();
        // 域代码说明：
        // \o "1-3" → 索引1-3级标题（对应Heading1/Heading2/Heading3）
        // \h → 为目录项添加超链接
        // \z → 隐藏域代码
        // \\u → 目录项与页码右对齐
        String tocFieldCode = "TOC \\o \"1-3\" \\h \\z \\u";

        /*
        // 创建目录域并设置自动更新属性
        CTFldSimple fldSimple = tocPara.getCTP().addNewFldSimple();
        fldSimple.setInstr(tocFieldCode);
        // 关键：设置fldLock为false，允许目录自动更新（默认true需要手动更新）
        fldSimple.setFldLock(STOnOff.FALSE);
         */

        tocPara.getCTP().addNewFldSimple().setInstr(tocFieldCode); // 直接设置域代码


        // 3. 目录与正文之间添加分页符
        XWPFParagraph pageBreakPara = doc.createParagraph();
        pageBreakPara.createRun().addBreak(BreakType.PAGE);  // 分页符
    }

    /**
     * 保存文档到指定路径（自动创建父目录）
     * @param doc 文档对象
     * @param outputPath 输出文件路径（如"E:/test/output.docx"）
     * @throws IOException 路径错误或IO异常时抛出
     */
    // todo 可能导致oom的场景
    /*
    public static void saveDoc(XWPFDocument doc, String outputPath) throws IOException {
        // 参数校验
        if (doc == null) {
            throw new IllegalArgumentException("文档对象不能为空");
        }
        if (outputPath == null || outputPath.trim().isEmpty()) {
            throw new IllegalArgumentException("输出路径不能为空");
        }

        // 创建父目录（POI不会自动创建，需手动处理）
        java.io.File outputFile = new java.io.File(outputPath);
        java.io.File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean mkdirs = parentDir.mkdirs();  // 创建多级目录
            if (!mkdirs) {
                throw new IOException("创建父目录失败：" + parentDir.getAbsolutePath());
            }
        }

        // 保存文档（使用try-with-resources自动关闭流）
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            doc.write(out);  // 写入文件
        } finally {
            doc.close();  // 关闭文档，释放资源（POI 4.1.0需手动关闭）
        }
    }
     */


    public static void saveDoc(XWPFDocument doc, String outputPath) throws IOException {
        // 参数校验
        if (doc == null) {
            throw new IllegalArgumentException("文档对象不能为空");
        }
        if (outputPath == null || outputPath.trim().isEmpty()) {
            throw new IllegalArgumentException("输出路径不能为空");
        }

        // 创建父目录（POI不会自动创建，需手动处理）
        java.io.File outputFile = new java.io.File(outputPath);
        java.io.File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean mkdirs = parentDir.mkdirs();  // 创建多级目录
            if (!mkdirs) {
                throw new IOException("创建父目录失败：" + parentDir.getAbsolutePath());
            }
        }

        // 保存文档（使用try-with-resources自动关闭流）
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            doc.write(out);  // 写入文件
        } finally {
            doc.close();  // 关闭文档，释放资源（POI 4.1.0需手动关闭）
        }
    }

}