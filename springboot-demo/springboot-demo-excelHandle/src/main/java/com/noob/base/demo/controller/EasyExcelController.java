package com.noob.base.demo.controller;

import com.noob.base.demo.model.EasyUser;
import com.noob.base.demo.service.EasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @Autowired
    private EasyExcelService excelService;

    /**
     * 导出用户数据
     */
    @GetMapping("/export")
    public void exportUsers(HttpServletResponse response) throws IOException {
        List<EasyUser> userList = new ArrayList<>();
        // 模拟数据
        userList.add(new EasyUser(1L, "张三", 25));
        userList.add(new EasyUser(2L, "李四", 30));
        excelService.exportUsers(response, userList);
    }

    /**
     * 导入用户数据
     */
    @PostMapping("/import")
    public String importUsers(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.importUsers(file);
        return "导入成功";
    }
}