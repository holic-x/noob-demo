package com.noob.base.aiAgent.controller;

import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import com.noob.base.aiAgent.helper.DrugInfoExcelExporter;
import com.noob.base.aiAgent.helper.DrugInfoGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    // 内存监控Bean
    private static final MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    /**
     * 计算字符串占用的内存大小（UTF-8编码估算）
     */
    private long calculateStringSize(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // String对象头(24字节) + 字符数组引用(4字节) + 字符数组大小(2字节*长度 + 12字节数组头)
        return 24 + 4 + (12 + str.length() * 2);
    }

    /**
     * 计算DTO列表占用的内存大小（估算）
     */
    private long calculateDataSize(List<DrugInfoDTO> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return 0;
        }

        // 1. 估算单个对象基础大小（对象头+引用字段）
        int baseSize = 40; // 约40字节（对象头+6个字符串引用）

        // 2. 计算所有字符串内容的大小
        long stringTotalSize = 0;
        for (DrugInfoDTO dto : dataList) {
            stringTotalSize += calculateStringSize(dto.getApproveNum());
            stringTotalSize += calculateStringSize(dto.getPrdtName());
            stringTotalSize += calculateStringSize(dto.getEnName());
            stringTotalSize += calculateStringSize(dto.getCommodityName());
            stringTotalSize += calculateStringSize(dto.getDosageForm());
            stringTotalSize += calculateStringSize(dto.getSize());
        }

        // 3. 总大小 = 列表对象大小 + 所有元素大小
        long listOverhead = 40 + (dataList.size() * 4); // ArrayList基础大小+元素引用
        return listOverhead + (baseSize * dataList.size()) + stringTotalSize;
    }

    /**
     * 记录当前内存状态
     */
    private void logMemoryStatus(String stage) {
        MemoryUsage heapMemory = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemory = memoryBean.getNonHeapMemoryUsage();

        log.info("【{}】堆内存：已用=【{} MB】，总分配=【{} MB】，最大=【{} MB】；非堆内存：已用=【{} MB】",
                stage,
                heapMemory.getUsed() / (1024 * 1024),
                heapMemory.getCommitted() / (1024 * 1024),
                heapMemory.getMax() / (1024 * 1024),
                nonHeapMemory.getUsed() / (1024 * 1024)
        );
    }

    @GetMapping("/exportExcelByMonitor")
    public String exportExcelByMonitor(@RequestParam Integer count) throws Exception {
        // 记录初始内存状态
        logMemoryStatus("接口开始执行");

        // 1. 模拟生成测试数据并计算大小
        long dataGenerateStart = System.currentTimeMillis();
        List<DrugInfoDTO> drugInfoDTOList = DrugInfoGenerator.generateDrugList(count);
        long dataSize = calculateDataSize(drugInfoDTOList); // 计算数据大小
        long dataGenerateEnd = System.currentTimeMillis();
        log.info("生成{}条数据，数据大小约为：【{} KB】，耗时：【{} ms】",
                count, dataSize / 1024, dataGenerateEnd - dataGenerateStart);
        logMemoryStatus("数据生成完成后");

        // 2. 创建Excel并计算内存占用
        long excelCreateStart = System.currentTimeMillis();
        SXSSFWorkbook workbook = DrugInfoExcelExporter.exportToExcel(drugInfoDTOList);
        long excelCreateEnd = System.currentTimeMillis();
        log.info("1.创建Excel对象耗时：【{} ms】", excelCreateEnd - excelCreateStart);
        logMemoryStatus("Excel对象创建完成后");

        // 3. 保存文件
        String TEST_PATH = "D:\\dev-daily\\drugCrawl";
        String fileName = TEST_PATH + File.separator +
                "大数据量测试_药品信息表_" + System.currentTimeMillis() + ".xlsx";
        long saveStartTime = System.currentTimeMillis();

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            workbook.write(fos);
            log.info("Excel文件生成成功！文件名称【{}】，文件大小：【{} KB】",
                    fileName, new File(fileName).length() / 1024);
        } catch (Exception e) {
            log.error("生成Excel失败", e);
            return "error";
        } finally {
            workbook.dispose(); // 关键：清理SXSSF的临时文件
            workbook.close();
        }

        long saveEndTime = System.currentTimeMillis();
        log.info("2.数据保存耗时：【{} ms】", saveEndTime - saveStartTime);
        logMemoryStatus("文件保存完成后");

        // 强制GC后再次记录内存（用于观察是否有内存泄漏）
        System.gc();
        Thread.sleep(1000); // 等待GC完成
        logMemoryStatus("强制GC后");

        return "success: 导出" + count + "条数据，数据大小约" + dataSize / 1024 + "KB";
    }


    // 数据导出测试验证
    @GetMapping("/exportExcel")
    public String getName(@RequestParam Integer count) throws IOException {

        // 1.指定测试数据条数并导出
        String testPath = "D:\\dev-daily\\drugCrawl";

        // 2.模拟生成测试数据
        List<DrugInfoDTO> drugInfoDTOList = DrugInfoGenerator.generateDrugList(count);
        // System.out.println(drugInfoDTOList);

        // 3.excel数据处理
        SXSSFWorkbook workbook = DrugInfoExcelExporter.exportToExcel(drugInfoDTOList);
        String fileName = testPath + File.separator + "大数据量测试_药品信息表_" + System.currentTimeMillis() + ".xlsx";

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

        // 操作完成
        return "success";

    }


}
