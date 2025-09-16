package com.noob.base.aiAgent.helper;

import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 将DrugInfo列表导出为Excel的工具类
 * - 使用SXSSFWorkbook处理，适合大数据量导出
 * - 默认导出实体所有字段，根据实体属性定义排序
 */
public class DrugInfoExcelExporter {

    private static final Logger logger = LoggerFactory.getLogger(DrugInfoExcelExporter.class);

    // 临时行缓存大小，超过此数量将写入临时文件
    private static final int TEMP_ROW_ACCESS_WINDOW = 5000;

    // 默认工作表名称
    private static final String DEFAULT_SHEET_NAME = "药品信息表";

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook
     *
     * @param drugInfoDTOList 药品信息列表
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcel(List<DrugInfoDTO> drugInfoDTOList) {
        return exportToExcel(drugInfoDTOList, DEFAULT_SHEET_NAME);
    }

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook（指定工作表名称）
     *
     * @param drugInfoDTOList 药品信息列表
     * @param sheetName       工作表名称
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcel(List<DrugInfoDTO> drugInfoDTOList, String sheetName) {
        // 开始时间
        Long startTime = System.currentTimeMillis();

        // 创建SXSSFWorkbook，设置临时行缓存
        SXSSFWorkbook workbook = new SXSSFWorkbook(TEMP_ROW_ACCESS_WINDOW);
        // 禁用临时文件压缩以提高性能
        workbook.setCompressTempFiles(false);

        // 创建工作表
        SXSSFSheet sheet = workbook.createSheet(sheetName);

        // 关键修复：跟踪所有需要自动调整的列
        // 先获取所有字段，确定需要跟踪的列数
        List<Field> fields = getDrugInfoFields();
        for (int i = 0; i < fields.size(); i++) {
            sheet.trackColumnForAutoSizing(i);
        }

        // 创建单元格样式
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle contentStyle = createContentStyle(workbook);

        // 创建表头
        createHeaderRow(sheet, fields, headerStyle);

        // 填充数据行
        fillDataRows(sheet, drugInfoDTOList, fields, contentStyle);

        // 自动调整列宽
        adjustColumnWidths(sheet, fields.size());

        // 结束时间
        Long endTime = System.currentTimeMillis();

        logger.info(String.format("【exportToExcel】执行耗时【%s】毫秒，共处理【%s】条记录", (endTime - startTime), CollectionUtils.isEmpty(drugInfoDTOList) ? 0 : drugInfoDTOList.size()));

        // 返回结果
        return workbook;
    }

    /**
     * 获取DrugInfo类的所有字段
     *
     * @return 字段列表
     */
    private static List<Field> getDrugInfoFields() {
        List<Field> fields = new ArrayList<>();
        // 获取DrugInfo类的所有声明字段
        Field[] declaredFields = DrugInfoDTO.class.getDeclaredFields();
        for (Field field : declaredFields) {
            // 设置可访问私有字段
            field.setAccessible(true);
            fields.add(field);
        }
        return fields;
    }

    /**
     * 创建表头行
     *
     * @param sheet       工作表
     * @param fields      字段列表
     * @param headerStyle 表头样式
     */
    private static void createHeaderRow(SXSSFSheet sheet, List<Field> fields, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(22); // 表头行高

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            Cell cell = headerRow.createCell(i);
            // 设置表头名称（使用字段名，可根据需要改为中文名称）
            cell.setCellValue(getFieldDisplayName(field.getName()));
            cell.setCellStyle(headerStyle);
        }
    }

    /**
     * 填充数据行
     *
     * @param sheet           工作表
     * @param drugInfoDTOList 药品信息列表
     * @param fields          字段列表
     * @param contentStyle    内容样式
     */
    protected static void fillDataRows(SXSSFSheet sheet, List<DrugInfoDTO> drugInfoDTOList, List<Field> fields, CellStyle contentStyle) {
        if (drugInfoDTOList == null || drugInfoDTOList.isEmpty()) {
            return;
        }

        int rowIndex = 1; // 从第二行开始（第一行为表头）
        for (DrugInfoDTO drugInfoDTO : drugInfoDTOList) {
            Row dataRow = sheet.createRow(rowIndex++);
            dataRow.setHeightInPoints(18); // 数据行高

            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                Cell cell = dataRow.createCell(i);
                try {
                    // 获取字段值并设置到单元格
                    Object value = field.get(drugInfoDTO);
                    cell.setCellValue(value != null ? value.toString() : "");
                } catch (Exception e) {
                    cell.setCellValue(""); // 发生异常时设置为空
                }
                cell.setCellStyle(contentStyle);
            }
        }
    }

    /**
     * 自动调整列宽
     *
     * @param sheet       工作表
     * @param columnCount 列数
     */
    protected static void adjustColumnWidths(SXSSFSheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            try {
                // 自动调整列宽，预留一定空间
                sheet.autoSizeColumn(i, true);
                int currentWidth = sheet.getColumnWidth(i);
                // 限制最大宽度，防止过宽
                sheet.setColumnWidth(i, Math.min(currentWidth + 2048, 65535));
            } catch (Exception e) {
                // 记录异常但不中断整个流程
                System.err.println("调整列宽时发生异常，列索引: " + i + ", 异常信息: " + e.getMessage());
            }
        }
    }

    /**
     * 创建表头样式
     *
     * @param workbook 工作簿
     * @return 表头单元格样式
     */
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置背景色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 设置边框
        setCellBorders(style);

        // 设置字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);

        // 设置对齐方式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 自动换行
        style.setWrapText(true);

        return style;
    }

    /**
     * 创建内容单元格样式
     *
     * @param workbook 工作簿
     * @return 内容单元格样式
     */
    private static CellStyle createContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置边框
        setCellBorders(style);

        // 设置字体
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);

        // 设置对齐方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 自动换行
        style.setWrapText(true);

        return style;
    }

    /**
     * 为单元格样式设置边框
     *
     * @param style 单元格样式
     */
    private static void setCellBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
    }

    /**
     * 将字段名转换为中文显示名（构建 字段名 & 中文显示名映射，用作表头列名）
     *
     * @param fieldName 字段名
     * @return 中文显示名
     */
    protected static String getFieldDisplayName(String fieldName) {
        // 映射字段名到中文显示名
        switch (fieldName) {
            case "approveWord":
                return "批准文号";
            case "prdtName":
                return "产品名称";
            case "enName":
                return "英文名称";
            case "commodityName":
                return "商品名";
            case "dosageForm":
                return "剂型";
            case "size":
                return "规格";
            case "owner":
                return "上市许可持有人";
            case "ownerLocation":
                return "上市许可持有人地址";
            case "produceCom":
                return "生产单位";
            case "approveDate":
                return "批准日期";
            case "produceLocation":
                return "生产地址";
            case "category":
                return "产品类别";
            case "approveNum":
                return "原批准文号";
            case "code":
                return "药品本位码";
            case "note":
                return "药品本位码备注";
            default:
                return fieldName; // 无限定映射返回字段名
        }
    }

    /**
     * 示例用法
     */
    /*
    public static void main(String[] args) {
        // 模拟数据
        List<DrugInfoVO> drugList = new ArrayList<>();

        DrugInfoVO drug1 = new DrugInfoVO();
        drug1.setApproveNum("国药准字H20230001");
        drug1.setPrdtName("测试药品1");
        drug1.setEnName("Test Drug 1");
        drug1.setCommodityName("商品名1");
        drug1.setDosageForm("片剂");
        drug1.setSize("0.5g");
        drugList.add(drug1);

        DrugInfoVO drug2 = new DrugInfoVO();
        drug2.setApproveNum("国药准字H20230002");
        drug2.setPrdtName("测试药品2");
        drug2.setEnName("Test Drug 2");
        drug2.setCommodityName("商品名2");
        drug2.setDosageForm("注射剂");
        drug2.setSize("10ml");
        drugList.add(drug2);

        // 导出为Excel
        SXSSFWorkbook workbook = DrugInfoExcelExporter.exportToExcel(drugList);

        String fileName = "药品信息表_" + System.currentTimeMillis() + ".xlsx";

        // 保存到文件
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(fileName)) {
            workbook.write(fos);
            System.out.println(String.format("Excel文件生成成功！文件名称【%s】", fileName));
        } catch (Exception e) {
            System.err.println("生成Excel失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            // 清理临时文件
            workbook.dispose();
        }
    }

     */
}

