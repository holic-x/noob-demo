package com.noob.base.batchDataHandler.docHandle;

import com.noob.base.batchDataHandler.docHandle.entity.CheckResult;
import com.noob.base.batchDataHandler.docHandle.mock.MockWebResultHelper;
import com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread.FileFetcher;
import com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread.ReportGeneratorV3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ReportGeneratorV3Test {

    @Test
    public void test_generateReport_V3_batchData_3() throws Exception {
        test_generateReport_V3_batchData(3);
    }

    @Test
    public void test_generateReport_V3_batchData_100() throws Exception {
        test_generateReport_V3_batchData(100);
    }

    @Test
    public void test_generateReport_V3_batchData_200() throws Exception {
        test_generateReport_V3_batchData(200);
    }

    @Test
    public void test_generateReport_V3_batchData_400() throws Exception {
        test_generateReport_V3_batchData(400);
    }

    @Test
    public void test_generateReport_V3_batchData_500() throws Exception {
        test_generateReport_V3_batchData(500);
    }

    @Test
    public void test_generateReport_V3_batchData_1000() throws Exception {
        test_generateReport_V3_batchData(1000);
    }

    @Test
    public void test_generateReport_V3_batchData_2000() throws Exception {
        test_generateReport_V3_batchData(2000);
    }

    @Test
    public void test_generateReport_V3_batchData_4000() throws Exception {
        test_generateReport_V3_batchData(4000);
    }

    // @Test
    public void test_generateReport_V3_batchData_10000() throws Exception {
        test_generateReport_V3_batchData(10000);
    }

    // @Test
    public void test_generateReport_V3_batchData_20000() throws Exception {
        test_generateReport_V3_batchData(20000);
    }

    // @Test
    public void test_generateReport_V3_batchData_40000() throws Exception {
        test_generateReport_V3_batchData(40000);
    }

    // @Test
    public void test_generateReport_V3_batchData_100000() throws Exception {
        test_generateReport_V3_batchData(100000);
    }

    public void test_generateReport_V3_batchData(int num) throws Exception {
        System.out.println("本次mock num：" + num);

        // mock 核查结果（自定义核查结果：fileKey UUID（确保唯一性，用作key），fileName 文件名称用于定位文件数据(此处测试可共用fileName)）
        List<CheckResult> checkResultList = MockWebResultHelper.mock_webResult_byCustomTemplateData(num, false); // 此处控制变量使用同一个文件处理

        // 获取测试目录
        String testDir = getTestDir();
        String mockImagesDir = testDir + File.separator + "mock_images";
        String outPutDir = testDir + File.separator + "output";

        // 指定本地图片目录
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU 核心数：" + availableProcessors);
        FileFetcher fileFetcher = new FileFetcher(mockImagesDir, availableProcessors * 2); // IO 密集型任务线程数配置：CPU核数*2
        ReportGeneratorV3 generator = new ReportGeneratorV3(fileFetcher);

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

}
