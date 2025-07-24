package com.noob.base.batchDataHandler.docHandle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.v1.MockFileService;
import com.noob.base.batchDataHandler.docHandle.v1.ReportGeneratorV1;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReportGeneratorV1Test {

    @Test
    public void test_generateReport_V1() throws Exception {

        List<CheckResult> checkResults = mock_webResult("MockWebResultData_success.json");

        // 获取测试目录
        String testDir = getTestDir();
        String mockImagesDir = testDir + File.separator + "mock_images";
        String outPutDir = testDir + File.separator + "output";

        // 指定本地图片目录
        MockFileService fileService = new MockFileService(mockImagesDir);
        ReportGeneratorV1 generator = new ReportGeneratorV1(fileService);

        // 生成报告
        String fileName = "report_" + System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        generator.generateReport(checkResults, outPutDir + File.separator + fileName + ".docx");
        long endTime = System.currentTimeMillis();
        System.out.println("报告生成完毕！本次生成报告耗时：" + (endTime - startTime) + "毫秒");

    }


    @Test
    public void test_generateReport_V1_batchData() throws Exception {

        // mock 核查结果
        List<CheckResult> checkResultList = mock_webResult_byTemplateData("MockWebResultData_success_templateData.json", 65536);

        // 获取测试目录
        String testDir = getTestDir();
        String mockImagesDir = testDir + File.separator + "mock_images";
        String outPutDir = testDir + File.separator + "output";

        // 指定本地图片目录
        MockFileService fileService = new MockFileService(mockImagesDir);
        ReportGeneratorV1 generator = new ReportGeneratorV1(fileService);

        // 生成报告
        String fileName = "report_" + System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        generator.generateReport(checkResultList, outPutDir + File.separator + fileName + ".docx");
        long endTime = System.currentTimeMillis();
        System.out.println("报告生成完毕！本次生成报告耗时：" + (endTime - startTime) + "毫秒");

    }

    // 获取测试目录（例如此处可以基于当前工程目录路径）
    private String getTestDir() {
        // String baseDir = "E:/workspace/project/noob-demo/springboot-demo/springboot-demo-scene-batchDataHandler";
        String projectDir = System.getProperty("user.dir");
        String testDir = projectDir + File.separator + "test-files";
        System.out.println("当前工程目录路径: " + projectDir);
        return testDir;
    }

    // mock 模拟核查结果数据（json数据，从文件中解析便于调试）
    @SneakyThrows
    private List<CheckResult> mock_webResult(String mockDataFileName) {

        // 假设jsonStr为接口返回的核查结果JSON
        // String jsonStr = "[{...}]"; // 省略，填入实际JSON
        // List<CheckResult> checkResults = JSON.parseArray(jsonStr, CheckResult.class);

        // src/test/resources/mockData/MockWebResultData_test.json
        // 1.从文件中读取数据
        String jsonFilePath = "src/test/resources/mockData/" + mockDataFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        // 2. 创建ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 3. 将JSON字符串转换为实体对象
        // FidelityRPAProcessResultDto webResult = objectMapper.readValue(jsonContent, FidelityRPAProcessResultDto.class);
        List<CheckResult> webResult = objectMapper.readValue(jsonContent, new TypeReference<List<CheckResult>>() {
        });

        // 返回读取的核查结果
        return webResult;
    }


    // mock 模拟核查结果数据（json数据，从文件中解析便于调试）
    @SneakyThrows
    private List<CheckResult> mock_webResult_byTemplateData(String mockDataFileName, int num) {

        // 1.从文件中读取数据
        String jsonFilePath = "src/test/resources/mockData/" + mockDataFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        // 2. 创建ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 3. 将JSON字符串转换为实体对象
        CheckResult checkResult = objectMapper.readValue(jsonContent, new TypeReference<CheckResult>() {
        });

        // mock List<CheckResult> 列表数据
        List<CheckResult> checkResultList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            checkResultList.add(checkResult);
        }
        return checkResultList;
    }

}
