package com.sz.demo.controller;

import com.sz.demo.util.AjaxResult;
import com.sz.demo.util.AjaxResultUtil;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SingleTaskController
 * @Description TODO
 * @Author
 * @Date 2020/6/17 17:17
 * @Version
 **/
@RestController
@RequestMapping("/singleTask")
public class SingleTaskController {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 启动流程
     * 根据部署流程的key启动一个流程(以最新版本的流程为参考)
     **/
    @RequestMapping("/startFlow")
    public AjaxResult startFlow() {
        // 启动任务实例,并存储临时变量
        Map<String,Object> map = new HashMap<>();
        map.put("userId","common_user");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("singleTask",map);
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }

    /**
     * 获取个人任务列表
     */
    @RequestMapping("/getTask")
    public void getTask(String userId,String defKey) {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userId)
                .processDefinitionKey(defKey)
                .list();
        for(Task t : list){
            System.out.println(t.getId()+"   "+t.getName()+"   "+t.getTaskDefinitionKey());
        }
    }

    /**
     * 完成任务
     **/
    @RequestMapping("/completeTask")
    public AjaxResult completeTask(String taskId) {
        taskService.complete(taskId);
        return AjaxResultUtil.success();
    }


}
