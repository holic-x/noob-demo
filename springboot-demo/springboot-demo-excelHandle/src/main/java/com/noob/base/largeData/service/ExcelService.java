package com.noob.base.largeData.service;

import com.alibaba.excel.EasyExcel;
import com.noob.base.largeData.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    private final UserService userService;

    public ExcelService(UserService userService) {
        this.userService = userService;
    }

    // 导入 Excel 文件
    public void importLargeExcel(String filePath) {
        EasyExcel.read(filePath, User.class, new UserDataListener(userService))
                .sheet()
                .doRead();
    }

    // 导出 Excel 文件
    public void exportLargeExcel(HttpServletResponse response) throws IOException {
        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        // 流式写入 Excel 文件
        EasyExcel.write(response.getOutputStream(), User.class)
                .sheet("用户列表")
                .doWrite(() -> {
                    // 分页查询数据
                    int pageSize = 1000;
                    int pageNum = 1;
                    List<User> users;
                    do {
                        users = userService.getUsersByPage(pageNum, pageSize);
                        pageNum++;
                        return users;
                    } while (!users.isEmpty());
                });
    }
}