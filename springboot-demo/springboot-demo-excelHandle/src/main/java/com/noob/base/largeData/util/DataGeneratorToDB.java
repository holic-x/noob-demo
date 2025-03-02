package com.noob.base.largeData.util;

import com.noob.base.largeData.entity.User;
import com.noob.base.largeData.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟数据生成到数据库（批量插入到数据库）
 */
@Component
public class DataGeneratorToDB {

    @Autowired
    private UserService userService;

    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank", "Ivy", "Jack"};
    private static final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com"};

    /**
     * 生成 10 万条用户数据并插入数据库
     */
    public void generateAndInsertUsers() {
        List<User> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 100000; i++) {
            User user = new User();
            user.setName(NAMES[random.nextInt(NAMES.length)] + i); // 随机名字 + 序号
            user.setAge(18 + random.nextInt(50)); // 年龄范围：18-67
            user.setEmail(user.getName().toLowerCase() + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)]);

            users.add(user);

            // 每 1000 条数据批量插入一次
            if (users.size() >= 1000) {
                userService.saveBatch(users);
                users.clear();
            }
        }

        // 插入剩余的数据
        if (!users.isEmpty()) {
            userService.saveBatch(users);
        }

        System.out.println("10 万条用户数据生成并插入数据库完成！");
    }
}