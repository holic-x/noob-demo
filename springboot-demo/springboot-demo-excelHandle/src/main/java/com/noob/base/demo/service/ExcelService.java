package com.noob.base.demo.service;

import com.alibaba.excel.EasyExcel;
import com.noob.base.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class ExcelService {

    /**
     * 导出用户数据到Excel
     */
    public void exportUsers(HttpServletResponse response, List<User> userList) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户列表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 写入Excel
        EasyExcel.write(response.getOutputStream(), User.class)
                .sheet("用户列表")
                .doWrite(userList);
    }

    /**
     * 从Excel导入用户数据
     */
    public void importUsers(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new UserDataListener())
                .sheet()
                .doRead();
    }
}