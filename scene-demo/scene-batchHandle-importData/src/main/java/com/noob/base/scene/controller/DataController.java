package com.noob.base.scene.controller;

import com.noob.base.demo.dao.UserRepository;
import com.noob.base.demo.model.entity.User;
import com.noob.base.scene.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/data")
public class DataController {

    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @Autowired
    private UserRepository userRepository;

    // 初始化测试环境
    @GetMapping("/initEnv")
    public String initEnv() {

        List<User> allUserList = userRepository.findAll();

        // 删除所有用户（重置）
        userRepository.deleteAll();

        List<User> findUserList = userRepository.findAll();

        log.info("当前数据库用户信息条数：【{}】", allUserList.size());
        log.info("数据清理完成，当前数据库用户信息条数：【{}】", findUserList.size());

        return "success";
    }

    @GetMapping("/insert")
    public String insert(@RequestParam String type, @RequestParam int cnt) {
        // 模拟生成数据
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            userList.add(new User(Long.valueOf(i), "myname-" + i, UUID.randomUUID().toString().substring(0, 6) + "@126.com"));
        }

        // 根据不同类型导入数据
        long startTime = System.currentTimeMillis();
        switch (type) {
            case "insertOnByOne": {
                int successNum = dataService.insertOnByOne(userList);
                log.info("成功记录条数：{}", successNum);
                break;
            }
            case "insertAllByOnce": {
                dataService.insertAllByOnce(userList);
                break;
            }
            case "insertAllByBatch": {
                dataService.insertAllByBatch(userList);
                break;
            }
            default: {
                log.info("无匹配type");
            }
        }

        long endTime = System.currentTimeMillis();

        log.info("当前指定type=【{}】,执行耗时=【{}】ms", type, (endTime - startTime));

        return "success";
    }
}