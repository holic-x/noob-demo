package com.noob.base.scene.service;

import com.noob.base.demo.dao.UserRepository;
import com.noob.base.demo.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DataService {

    @Autowired
    private UserRepository userRepository;

    // 数据处理：单条处理
    public int insertOnByOne(List<User> userList) {
        AtomicInteger successNum = new AtomicInteger(); // 统计成功次数
        userList.forEach((item) -> {
            try {
                userRepository.save(item);
                successNum.getAndIncrement();
            } catch (Exception e) {
                log.error("用户添加失败", e);
            }
        });
        return successNum.get();
    }

    // 数据处理：直接大批导入(一次性导入)
    public void insertAllByOnce(List<User> userList) {
        userRepository.saveAll(userList);
    }

    // 数据处理：分批导入（多线程）
    public void insertAllByBatch(List<User> userList) {
        // 按照1w一个批次处理
        ExecutorService executor = Executors.newFixedThreadPool(10);

        int batchSize = 10000;
        int batch = userList.size() / batchSize;
        CountDownLatch countDownLatch = new CountDownLatch(batch);
        for (int i = 0; i < batch; i++) {
            // 执行
            int finalI = i;
            executor.execute(() -> {
                try {
                    userRepository.saveAll(userList.subList(finalI * batchSize, Math.min((finalI + 1) * batchSize, userList.size())));
                } finally {
                    countDownLatch.countDown(); // 线程执行完成不管是否异常，释放资源
                }
            });
        }
        // 阻塞等待所有任务执行完成
        try {
            countDownLatch.await();
        } catch (Exception e) {
            log.error("等待任务执行出现异常", e);
        }

    }

}
