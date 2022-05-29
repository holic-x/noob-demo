package com.sz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sz.demo.util.AjaxResult;
import com.sz.demo.util.AjaxResultUtil;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RecieveController
 * @Description 接收任务测试
 * @Author
 * @Date 2020/6/17 16:24
 * @Version
 **/

@RestController
@RequestMapping("/recieve")
public class RecieveController {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ManagementService managementService;

    /**
     * 启动流程
     * 根据部署流程的key启动一个流程(以最新版本的流程为参考)
     **/
    @RequestMapping("/startFlow")
    public AjaxResult startFlow() {
        // 启动任务实例,并存储临时变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("receiveTask");
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }

    /**
     * 根据活动id(activityId)获取相应的执行实例
     */
    @RequestMapping("/getExecution")
    public void getExecution(String activityId) {
        Execution execution = runtimeService.createExecutionQuery().activityId(activityId).singleResult();
        System.out.println(execution);
    }

    /** 触发执行实例继续往下运转 **/
    @RequestMapping("/trigger")
    public void trigger(String executionId) {
        runtimeService.trigger(executionId);
    }



}
