package com.noob.base.largeData.controller;

import com.noob.base.largeData.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/excel/largeData")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    // 导入 Excel 文件
    @PostMapping("/import")
    public String importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        long start = System.currentTimeMillis();

        File tempFile = File.createTempFile("temp", ".xlsx");
        file.transferTo(tempFile);
        excelService.importLargeExcel(tempFile.getAbsolutePath());
        tempFile.delete();

        long end = System.currentTimeMillis();


        return "Excel 文件导入成功！耗时：" + (end - start) / 1000 + "s";
    }

    // 导出 Excel 文件
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();

        // 导出文件
        excelService.exportLargeExcel(response);

        long end = System.currentTimeMillis();
        System.out.println("Excel 文件导出成功！耗时：" + (end - start) / 1000 + "s");
    }
}