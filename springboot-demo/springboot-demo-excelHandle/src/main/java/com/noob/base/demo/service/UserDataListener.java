package com.noob.base.demo.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.noob.base.demo.model.User;

import java.util.List;

public class UserDataListener implements ReadListener<User> {

    private List<User> cachedDataList = ListUtils.newArrayListWithExpectedSize(100);

    @Override
    public void invoke(User user, AnalysisContext context) {
        cachedDataList.add(user);
        // 每100条数据存储一次
        if (cachedDataList.size() >= 100) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(100);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 最后一批数据存储
        saveData();
    }

    // 模拟数据存储
    private void saveData() {
        // 将数据存储到数据库
        // userRepository.saveAll(cachedDataList);
        System.out.println("存储数据：" + cachedDataList);
    }
}