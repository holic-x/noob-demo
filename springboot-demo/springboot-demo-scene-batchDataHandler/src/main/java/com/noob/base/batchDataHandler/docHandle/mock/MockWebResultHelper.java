package com.noob.base.batchDataHandler.docHandle.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 核查结果 mock
 */
public class MockWebResultHelper {

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
