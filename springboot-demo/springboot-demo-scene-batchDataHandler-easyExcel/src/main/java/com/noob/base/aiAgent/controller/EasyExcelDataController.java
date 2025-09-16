package com.noob.base.aiAgent.controller;

import com.noob.base.aiAgent.drugInfoCrawl.entity.vo.DrugInfoVO;
import com.noob.base.aiAgent.helper.DrugInfoVOGenerator;
import com.noob.base.aiAgent.helper.EasyExcelExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/data/easyExcel")
public class EasyExcelDataController {

    // 本地导出文件路径
    private static final String EXPORT_PATH = "D:\\dev-daily\\drugCrawl";

    /**
     * 生成测试数据（实际项目中从数据库查询）
     */
    private List<DrugInfoVO> generateTestData(int count) {
        // 模拟生成count条药品数据,实际项目中替换为数据库查询逻辑
        return DrugInfoVOGenerator.generateDrugList(count);
    }

    /**
     * 导出Excel到本地文件
     */
    @GetMapping("/exportToLocal")
    public String exportToLocal(@RequestParam Integer count) {
        long startTime = System.currentTimeMillis();

        try {
            // 1. 生成测试数据
            List<DrugInfoVO> dataList = generateTestData(count);

            // 2. 构建文件名
            String fileName = EXPORT_PATH + File.separator +
                    "EasyExcel_药品信息表_" + System.currentTimeMillis() + ".xlsx";

            // 3. 调用EasyExcel工具类导出
            EasyExcelExportUtil.exportToLocalFile(dataList, fileName, DrugInfoVO.class, "药品列表");

            long endTime = System.currentTimeMillis();
            log.info("导出完成！文件路径：{}，耗时：【{}ms】，数据量：【{}】条",
                    fileName, endTime - startTime, count);
            return "导出成功，文件路径：" + fileName;
        } catch (Exception e) {
            log.error("导出失败", e);
            return "导出失败：" + e.getMessage();
        }
    }

}
