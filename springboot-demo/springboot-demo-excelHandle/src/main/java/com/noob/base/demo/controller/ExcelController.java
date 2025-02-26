package com.noob.base.demo.controller;

import com.noob.base.demo.model.User;
import com.noob.base.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    /**
     * 导出用户数据
     */
    @GetMapping("/export")
    public void exportUsers(HttpServletResponse response) throws IOException {
        List<User> userList = new ArrayList<>();
        // 模拟数据
        userList.add(new User(1L, "张三", 25));
        userList.add(new User(2L, "李四", 30));
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