package com.noob.base.druid.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.druid.model.entity.Task;
import com.noob.base.druid.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // 新增操作
    @RequestMapping("/create")
    public String create() {
        Task task = new Task();
        task.setTaskId(UUID.randomUUID().toString().replaceAll("-", ""));
        Date now = new Date();
        task.setCreateTime(now);
        task.setUpdateTime(now);
        boolean res = taskService.save(task);
        if(res){
            return "ok";
        }
        return "fail";
    }

    // 检索所有task
    @GetMapping("/selectAll")
    public String selectAll() {
        List<Task> taskList = taskService.list();
        return JSONObject.toJSONString(taskList);
    }



    // 带多个参数
    @GetMapping("/showInfo")
    public String showInfo(@RequestParam String name,@RequestParam int age) {
        return "name : " + name + " age : " + age;
    }

    // 请求参数为实体类型
    @PostMapping("/showJson")
    public String showJson(@RequestBody JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    // 带header校验
    @GetMapping("/getToken")
    public String showNameWithHeader(@RequestHeader(name = "userToken") String userToken) {
        return "userToken: " + userToken;
    }

}
