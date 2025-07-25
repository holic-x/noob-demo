package com.noob.base.batchDataHandler.docHandle.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.batchDataHandler.docHandle.WebGenerator;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.entity.KeyInfo;
import com.noob.base.batchDataHandler.docHandle.entity.ResultItem;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 核查结果 mock
 */
public class MockWebResultHelper {

    static Random random = new Random();

    // mock 模拟核查结果数据（json数据，从文件中解析便于调试）
    @SneakyThrows
    public static List<CheckResult> mock_webResult(String mockDataFileName) {

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
    public static List<CheckResult> mock_webResult_byTemplateData(String mockDataFileName, int num) {

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


    private static String getActiveFileNameByRandom(int min, int max) {
        /*
        int min = 1;    // 起始编号
        int max = 999;  // 最大编号
         */

        // 获取随机数
        int index = random.nextInt(max - min + 1) + min;

        // 返回有效的文件名称
        return "mock_image_" + String.format("%03d", index) + ".png";

        // 返回默认的文件名称
        // return "mock_image_001.png";
    }

    public static List<CheckResult> mock_webResult_byCustomTemplateData(int num, boolean isRandomFile) {
        // 生成批次数据
        List<CheckResult> checkResultList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            CheckResult checkResult = CheckResult.builder()
                    .webKey("BAIDU")
                    .webName("百度")
                    .status("SUCCESS")
                    .note("执行成功")
                    .results(
                            Arrays.asList(
                                    ResultItem.builder()
                                            .key(KeyInfo.builder()
                                                    .name("滴滴有限公司")
                                                    .idNo("123456789012345678")
                                                    .build())
                                            .createTime(null)
                                            .files(Arrays.asList(
                                                    FileInfo.builder()
                                                            .fileKey(UUID.randomUUID().toString().replace("-", ""))
                                                            .fileName(isRandomFile ? getActiveFileNameByRandom(1, 15) : "mock_image_001.png") // 如果指定了true则从有效列表中随机获取有效的文件名称，否则填充默认的数据
                                                            .fileType("picture")
                                                            .build()
                                            ))
                                            .build()
                            )
                    )
                    .build();
            checkResultList.add(checkResult);
        }

        // 返回mock生成的数据列表
        return checkResultList;

    }


    public static List<CheckResult> mock_webResult_byDifferentTemplateData(int num, boolean isRandomFile) {

        // 生成批次数据
        List<CheckResult> checkResultList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            // 每次都随机生成网站信息
            String[] webInfo = WebGenerator.getRandomWebMapping();
            CheckResult checkResult = CheckResult.builder()
                    .webKey(webInfo[0])
                    .webName(webInfo[1])
                    .status("SUCCESS")
                    .note("执行成功")
                    .results(
                            Arrays.asList(
                                    ResultItem.builder()
                                            .key(KeyInfo.builder()
                                                    .name("滴滴有限公司")
                                                    .idNo("123456789012345678")
                                                    .build())
                                            .createTime(null)
                                            .files(Arrays.asList(
                                                    FileInfo.builder()
                                                            .fileKey(UUID.randomUUID().toString().replace("-", ""))
                                                            .fileName(isRandomFile ? getActiveFileNameByRandom(1, 15) : "mock_image_001.png") // 如果指定了true则从有效列表中随机获取有效的文件名称，否则填充默认的数据
                                                            .fileType("picture")
                                                            .build()
                                            ))
                                            .build()
                            )
                    )
                    .build();
            checkResultList.add(checkResult);
        }

        // 返回mock生成的数据列表
        return checkResultList;

    }

}
