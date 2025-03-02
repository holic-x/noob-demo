package com.noob.base.demo.controller;

import com.noob.base.demo.model.PoiUser;
import com.noob.base.demo.service.PoiExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/poiExcel")
public class PoiExcelController {

    @Autowired
    private PoiExcelService excelService;

    @GetMapping("/read")
    public List<PoiUser> readPoiExcel() {
        return excelService.readExcelFile("users.xlsx");// 基于此处设定会在当前工程目录下读取文件（可自定义目标目录）
    }

    @GetMapping("/write")
    public String writePoiExcel() {
        List<PoiUser> users = new ArrayList<>();
        users.add(new PoiUser("Alice", 30, "alice@example.com"));
        users.add(new PoiUser("Bob", 25, "bob@example.com"));
        excelService.writeExcelFile(users, "users_output.xlsx"); // 基于此处设定会将文件生成在当前工程目录下（可自定义目标目录）
        return "PoiExcel file written successfully!";
    }
}