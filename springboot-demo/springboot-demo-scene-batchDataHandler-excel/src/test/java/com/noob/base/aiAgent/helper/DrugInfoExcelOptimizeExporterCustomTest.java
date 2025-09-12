package com.noob.base.aiAgent.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import com.noob.base.aiAgent.helper.DrugInfoExcelOptimizeExporter;
import com.noob.base.aiAgent.helper.DrugInfoGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

// @Ignore
@Slf4j
@RunWith(JUnit4ClassRunner.class)
public class DrugInfoExcelOptimizeExporterCustomTest {

    @Test
    public void test_exportExcel_random() throws IOException {
        // 1.指定测试数据条数并导出
        int count = 10;
        String testPath = "D:\\dev-daily\\drugCrawl";

        // 2.模拟生成测试数据
        List<DrugInfoDTO> drugInfoDTOList = DrugInfoGenerator.generateDrugList(count);

        // 3.excel数据处理
//        List<String> fieldNames = Arrays.asList("approveWord", "prdtName", "enName", "itemStatus");

        // 根据字段定义顺序决定数据导出
        List<String> fieldNames = Arrays.asList("enName", "approveWord", "prdtName", "itemStatus");
        SXSSFWorkbook workbook = DrugInfoExcelOptimizeExporter.exportToExcelWithFieldNames(drugInfoDTOList, fieldNames);
        String fileName = testPath + File.separator + "自定义导出字段_药品信息表_" + System.currentTimeMillis() + ".xlsx";

        // 4.保存到文件
        long saveStartTime = System.currentTimeMillis();

        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(fileName)) {
            workbook.write(fos);
            System.out.println(String.format("Excel文件生成成功！文件名称【%s】", fileName));
        } catch (Exception e) {
            System.err.println("生成Excel失败：" + e.getMessage());
            e.printStackTrace();
        } finally {
            // 清理临时文件
            // workbook.dispose(); // 仅删除磁盘上的临时文件，不释放内存资源
            workbook.close();
        }
        long saveEndTime = System.currentTimeMillis();
        log.info("数据保存耗时：【{}】ms", (saveEndTime - saveStartTime));

        // Assert
        assertTrue(Boolean.TRUE);

    }


    @Test
    public void test_mock_generate_data() {
        // 生成1000条测试数据并导出
        int count = 10000;
        String outputPath = "drug_info_list_" + System.currentTimeMillis() + ".json";

        List<DrugInfoDTO> drugList = DrugInfoGenerator.generateDrugList(count);
        boolean success = DrugInfoGenerator.exportToJson(drugList, outputPath);

        if (success) {
            System.out.println("成功生成" + count + "条数据，已保存至：" + new File(outputPath).getAbsolutePath());
        } else {
            System.err.println("数据导出失败");
        }


        // Assert
        assertTrue(Boolean.TRUE);

    }


    // mock 模拟核查结果数据（json数据，从文件中解析便于调试）=>直接获取文档数据（不做转化处理，直接读取源文件为字符串）
    @SneakyThrows
    private String mock_rpaHandleResult_txt(String mockDataFileName) {
        // src/test/resources/mockData/MockWebResultData_test.json
        // 1.从文件中读取数据
        String jsonFilePath = "src/test/resources/mockData/aiAgent/drugInfoCrawl/" + mockDataFileName;
        // String jsonFilePath = "src/test/resources/mockData/" + mockDataFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        // 2. 创建ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 返回读取的数据结果
        return jsonContent;
    }


}
