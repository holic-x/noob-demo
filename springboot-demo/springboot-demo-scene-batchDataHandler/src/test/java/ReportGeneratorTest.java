import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.batchDataHandler.docHandle.MockFileService;
import com.noob.base.batchDataHandler.docHandle.ReportGenerator;
import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReportGeneratorTest {

    @Test
    public void test_generateReport() throws Exception {

        List<CheckResult> checkResults = mock_webResult("MockWebResultData_success.json");

        // 获取测试目录（此处可以基于当前工程目录路径）
        String baseDir = getBaseDir();

        // 指定本地图片目录
        MockFileService fileService = new MockFileService(baseDir + File.separator + "test-files" + File.separator + "mock_images");
        ReportGenerator generator = new ReportGenerator(fileService);

        // 生成报告
        String fileName = "report_" + System.currentTimeMillis();
        generator.generateReport(checkResults, baseDir + File.separator + "test-files" + File.separator + "output" + File.separator + fileName + ".docx");

        System.out.println("报告生成完毕！");
    }

    // 获取测试目录（例如此处可以基于当前工程目录路径）
    private String getBaseDir() {
        // String baseDir = "E:/workspace/project/noob-demo/springboot-demo/springboot-demo-scene-batchDataHandler";
        String projectDir = System.getProperty("user.dir");
        System.out.println("当前工程目录路径: " + projectDir);
        return projectDir;
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
        List<CheckResult> webResult = objectMapper.readValue(jsonContent,
                new TypeReference<List<CheckResult>>() {
                });

        // 返回读取的核查结果
        return webResult;
    }


}
