package com.noob.base.largeData.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.noob.base.largeData.entity.User;

import java.util.List;

public class UserDataListener implements ReadListener<User> {

    private static final int BATCH_COUNT = 1000; // 每 1000 条数据批量插入一次

    private List<User> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final UserService userService;

    public UserDataListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(User user, AnalysisContext context) {
        // 数据校验（示例）
        if (user.getAge() < 0) {
            throw new RuntimeException("年龄不能为负数");
        }

        // 添加到缓存
        cachedDataList.add(user);

        // 达到 BATCH_COUNT 时，批量插入数据库
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 确保最后剩余的数据也插入数据库
        saveData();
        System.out.println("Excel 文件读取完成");
    }

    private void saveData() {
        if (!cachedDataList.isEmpty()) {
            userService.saveBatch(cachedDataList); // 批量插入
            System.out.println("插入 " + cachedDataList.size() + " 条数据");
        }
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        System.err.println("读取 Excel 时发生异常: " + exception.getMessage());
        throw new RuntimeException(exception);
    }
}