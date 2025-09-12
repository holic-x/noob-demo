package com.noob.base.aiAgent.helper;

import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DrugInfoExcelOptimizeExporterTest {

    private DrugInfoDTO buildSampleDTO(String suffix) {
        DrugInfoDTO dto = new DrugInfoDTO();
        dto.setItemStatus("状态" + suffix);
        dto.setApproveWord("批准文号" + suffix);
        dto.setPrdtName("产品" + suffix);
        dto.setEnName("EN" + suffix);
        dto.setCommodityName("商品" + suffix);
        dto.setDosageForm("剂型" + suffix);
        dto.setSize("规格" + suffix);
        dto.setOwner("持有人" + suffix);
        dto.setOwnerLocation("持有人地址" + suffix);
        dto.setProduceCom("生产单位" + suffix);
        dto.setApproveDate("2023-01-0" + suffix);
        dto.setProduceLocation("生产地址" + suffix);
        dto.setCategory("类别" + suffix);
        dto.setApproveNum("原批准文号" + suffix);
        dto.setCode("本位码" + suffix);
        dto.setNote("备注" + suffix);
        return dto;
    }

    @Test
    public void testExportToExcelWithAllFieldsAndSheetName() throws Exception {
        List<DrugInfoDTO> list = Arrays.asList(buildSampleDTO("1"), buildSampleDTO("2"));
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithAllFieldsAndSheetName(list, "测试表");
        Assert.assertNotNull(workbook);
        Sheet sheet = workbook.getSheet("测试表");
        Assert.assertNotNull(sheet);
        Assert.assertEquals(3, sheet.getPhysicalNumberOfRows()); // 1 header + 2 data
        workbook.dispose();
    }

    @Test
    public void testExportToExcelWithAllFields_emptyList() {
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithAllFields(Collections.emptyList());
        Assert.assertNotNull(workbook);
        Sheet sheet = workbook.getSheetAt(0);
        Assert.assertEquals(1, sheet.getPhysicalNumberOfRows()); // only header
        workbook.dispose();
    }

    @Test
    public void testExportToExcelWithFieldNamesAndSheetName() throws Exception {
        List<DrugInfoDTO> list = Arrays.asList(buildSampleDTO("A"));
        List<String> fields = Arrays.asList("prdtName", "approveWord", "note");
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithFieldNamesAndSheetName(list, fields, "自定义");
        Sheet sheet = workbook.getSheet("自定义");
        Assert.assertEquals(2, sheet.getPhysicalNumberOfRows());
        Row header = sheet.getRow(0);
        Assert.assertEquals("产品名称", header.getCell(0).getStringCellValue());
        Assert.assertEquals("批准文号", header.getCell(1).getStringCellValue());
        Assert.assertEquals("药品本位码备注", header.getCell(2).getStringCellValue());
        Row data = sheet.getRow(1);
        Assert.assertEquals("产品A", data.getCell(0).getStringCellValue());
        Assert.assertEquals("批准文号A", data.getCell(1).getStringCellValue());
        Assert.assertEquals("备注A", data.getCell(2).getStringCellValue());
        workbook.dispose();
    }

    @Test
    public void testExportToExcelWithFieldNames_nullOrEmpty() {
        List<DrugInfoDTO> list = Arrays.asList(buildSampleDTO("X"));
        // null fieldNames
        SXSSFWorkbook wb1 = DrugInfoExcelOptimizeExporter.exportToExcelWithFieldNames(list, null);
        Assert.assertNotNull(wb1);
        wb1.dispose();
        // empty fieldNames
        SXSSFWorkbook wb2 = DrugInfoExcelOptimizeExporter.exportToExcelWithFieldNames(list, new ArrayList<>());
        Assert.assertNotNull(wb2);
        wb2.dispose();
    }

    @Test
    public void testExportToExcelWithFieldNames_invalidField() {
        List<DrugInfoDTO> list = Arrays.asList(buildSampleDTO("Y"));
        List<String> fields = Arrays.asList("prdtName", "notExistField", "approveWord");
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithFieldNames(list, fields);
        Sheet sheet = workbook.getSheetAt(0);
        Row header = sheet.getRow(0);
        Assert.assertEquals("产品名称", header.getCell(0).getStringCellValue());
        Assert.assertEquals("批准文号", header.getCell(1).getStringCellValue());
        workbook.dispose();
    }

    @Test
    public void testExportToExcelWithAllFieldsAndSheetName_nullList() {
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithAllFieldsAndSheetName(null, "空表");
        Assert.assertNotNull(workbook);
        Sheet sheet = workbook.getSheet("空表");
        Assert.assertEquals(1, sheet.getPhysicalNumberOfRows());
        workbook.dispose();
    }

    @Test
    public void testGetFieldDisplayName_allCases() throws Exception {
        // 覆盖所有switch分支
        String[] fields = {
                "itemStatus", "approveWord", "prdtName", "enName", "commodityName", "dosageForm", "size",
                "owner", "ownerLocation", "produceCom", "approveDate", "produceLocation", "category",
                "approveNum", "code", "note", "unknownField"
        };
        for (String f : fields) {
            String display = DrugInfoExcelOptimizeExporter.getFieldDisplayName(f);
            Assert.assertNotNull(display);
        }
    }

    @Test
    public void testAdjustColumnWidths_exceptionBranch() {
        // 使用mock sheet，触发catch分支
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("异常测试");
        // 不创建任何列，autoSizeColumn会抛异常
        DrugInfoExcelOptimizeExporter.adjustColumnWidths(sheet, 1);
        workbook.dispose();
    }

    @Test
    public void testExportToExcel_fileWrite() throws Exception {
        List<DrugInfoDTO> list = Arrays.asList(buildSampleDTO("Z"));
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithAllFields(list);
        File file = File.createTempFile("druginfo_test", ".xlsx");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }
        Assert.assertTrue(file.exists() && file.length() > 0);
        workbook.dispose();
        file.delete();
    }
}