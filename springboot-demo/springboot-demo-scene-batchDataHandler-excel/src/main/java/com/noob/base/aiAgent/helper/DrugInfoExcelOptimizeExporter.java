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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按需导出：自定义导出（根据指定列名按需导出）
 * - 基于DrugInfoExcelExporter优化，可根据自定义字段属性决定导出列（基于字段属性定义顺序控制列的导出顺序和显隐）
 */
public class DrugInfoExcelOptimizeExporter {

    private static final Logger logger = LoggerFactory.getLogger(DrugInfoExcelExporter.class);

    // 临时行缓存大小，超过此数量将写入临时文件
    private static final int TEMP_ROW_ACCESS_WINDOW = 5000;

    // 默认工作表名称
    private static final String DEFAULT_SHEET_NAME = "药品信息表";

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook（导出全部字段）
     *
     * @param drugInfoDTOList 药品信息列表
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcelWithAllFields(List<DrugInfoDTO> drugInfoDTOList) {
        return exportToExcelWithAllFieldsAndSheetName(drugInfoDTOList, DEFAULT_SHEET_NAME);
    }

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook（导出全部字段，指定工作表名称）
     *
     * @param drugInfoDTOList 药品信息列表
     * @param sheetName       工作表名称
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcelWithAllFieldsAndSheetName(List<DrugInfoDTO> drugInfoDTOList, String sheetName) {
        List<Field> fields = getDrugInfoFields();
        return exportToExcel(drugInfoDTOList, fields, sheetName);
    }

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook（自定义导出字段）
     *
     * @param drugInfoDTOList 药品信息列表
     * @param fieldNames      需要导出的字段名列表
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcelWithFieldNames(List<DrugInfoDTO> drugInfoDTOList, List<String> fieldNames) {
        return exportToExcelWithFieldNamesAndSheetName(drugInfoDTOList, fieldNames, DEFAULT_SHEET_NAME);
    }

    /**
     * 将DrugInfo列表转换为SXSSFWorkbook（自定义导出字段，指定工作表名称）
     *
     * @param drugInfoDTOList 药品信息列表
     * @param fieldNames      需要导出的字段名列表
     * @param sheetName       工作表名称
     * @return SXSSFWorkbook对象
     */
    public static SXSSFWorkbook exportToExcelWithFieldNamesAndSheetName(List<DrugInfoDTO> drugInfoDTOList, List<String> fieldNames, String sheetName) {
        List<Field> fields = getDrugInfoFields(fieldNames);
        return exportToExcel(drugInfoDTOList, fields, sheetName);
    }

    /**
     * 核心导出方法（根据字段列表导出）
     */
    protected static SXSSFWorkbook exportToExcel(List<DrugInfoDTO> drugInfoDTOList, List<Field> fields, String sheetName) {
        Long startTime = System.currentTimeMillis();

        SXSSFWorkbook workbook = new SXSSFWorkbook(TEMP_ROW_ACCESS_WINDOW);
        workbook.setCompressTempFiles(false);

        SXSSFSheet sheet = workbook.createSheet(sheetName);

        for (int i = 0; i < fields.size(); i++) {
            sheet.trackColumnForAutoSizing(i);
        }

        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle contentStyle = createContentStyle(workbook);

        createHeaderRow(sheet, fields, headerStyle);
        fillDataRows(sheet, drugInfoDTOList, fields, contentStyle);
        adjustColumnWidths(sheet, fields.size());

        Long endTime = System.currentTimeMillis();

        logger.info(String.format("【exportToExcel】执行耗时【%s】毫秒，共处理【%s】条记录，导出字段【%s】",
                (endTime - startTime),
                CollectionUtils.isEmpty(drugInfoDTOList) ? 0 : drugInfoDTOList.size(),
                fields.size()));

        return workbook;
    }

    /**
     * 获取DrugInfo类的所有字段
     *
     * @return 字段列表
     */
    private static List<Field> getDrugInfoFields() {
        Field[] declaredFields = DrugInfoDTO.class.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            fields.add(field);
        }
        return fields;
    }

    /**
     * 根据字段名获取DrugInfo类的部分字段
     *
     * @param fieldNames 字段名列表
     * @return 字段列表
     */
    private static List<Field> getDrugInfoFields(List<String> fieldNames) {
        Map<String, Field> allFieldMap = new HashMap<>();
        for (Field field : DrugInfoDTO.class.getDeclaredFields()) {
            field.setAccessible(true);
            allFieldMap.put(field.getName(), field);
        }
        List<Field> result = new ArrayList<>();
        if (fieldNames != null) {
            for (String name : fieldNames) {
                Field field = allFieldMap.get(name);
                if (field != null) {
                    result.add(field);
                }
            }
        }
        return result;
    }

    /**
     * 创建表头行
     */
    private static void createHeaderRow(SXSSFSheet sheet, List<Field> fields, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(22);

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(getFieldDisplayName(field.getName()));
            cell.setCellStyle(headerStyle);
        }
    }

    /**
     * 填充数据行
     */
    protected static void fillDataRows(SXSSFSheet sheet, List<DrugInfoDTO> drugInfoDTOList, List<Field> fields, CellStyle contentStyle) {
        if (drugInfoDTOList == null || drugInfoDTOList.isEmpty()) {
            return;
        }

        int rowIndex = 1;
        for (DrugInfoDTO drugInfoDTO : drugInfoDTOList) {
            Row dataRow = sheet.createRow(rowIndex++);
            dataRow.setHeightInPoints(18);

            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                Cell cell = dataRow.createCell(i);
                try {
                    Object value = field.get(drugInfoDTO);
                    cell.setCellValue(value != null ? value.toString() : "");
                } catch (Exception e) {
                    cell.setCellValue("");
                }
                cell.setCellStyle(contentStyle);
            }
        }
    }

    /**
     * 自动调整列宽
     */
    protected static void adjustColumnWidths(SXSSFSheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            try {
                sheet.autoSizeColumn(i, true);
                int currentWidth = sheet.getColumnWidth(i);
                sheet.setColumnWidth(i, Math.min(currentWidth + 2048, 65535));
            } catch (Exception e) {
                System.err.println("调整列宽时发生异常，列索引: " + i + ", 异常信息: " + e.getMessage());
            }
        }
    }

    /**
     * 创建表头样式
     */
    protected static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setCellBorders(style);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    /**
     * 创建内容单元格样式
     */
    protected static CellStyle createContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setCellBorders(style);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    /**
     * 为单元格样式设置边框
     */
    protected static void setCellBorders(CellStyle style) {
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
     */
    protected static String getFieldDisplayName(String fieldName) {
        switch (fieldName) {
            case "itemStatus":
                return "条目处理状态";
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
                return fieldName;
        }
    }
}