package com.noob.base.largeData.util;

import com.alibaba.excel.EasyExcel;
import com.noob.base.largeData.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 模拟生成数据并导出到excel文件
 */
public class DataGeneratorToExcel {

    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank", "Ivy", "Jack"};
    private static final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com"};

    /**
     * 生成 10 万条用户数据并导出为 Excel 文件
     */
    public void generateAndExportUsersToExcel(String filePath) {
        List<User> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 100000; i++) {
            User user = new User();
            user.setName(NAMES[random.nextInt(NAMES.length)] + i); // 随机名字 + 序号
            user.setAge(18 + random.nextInt(50)); // 年龄范围：18-67
            user.setEmail(user.getName().toLowerCase() + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)]);

            users.add(user);
        }

        // 导出为 Excel 文件
        EasyExcel.write(filePath, User.class)
                .sheet("用户列表")
                .doWrite(users);

        System.out.println("10 万条用户数据生成并导出为 Excel 文件完成！");
    }

    public static void main(String[] args) {
        System.out.println("----------start----------");
        long start = System.currentTimeMillis();
        String filePath = "D:\\Desktop\\test\\excel\\users.xlsx";
        DataGeneratorToExcel dataGenerator = new DataGeneratorToExcel();
        dataGenerator.generateAndExportUsersToExcel(filePath);
        long end = System.currentTimeMillis();
        System.out.println("----------end----------");
        System.out.println("数据导出耗时：" + (end-start)/1000 + "s");
    }
}