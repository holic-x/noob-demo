package com.sz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sz.demo.test.CustomCmd;
import com.sz.demo.util.AjaxResult;
import com.sz.demo.util.AjaxResultUtil;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.DataObject;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApplyController
 * @Description TODO
 * @Author
 * @Date 2020/6/16 18:18
 * @Version
 **/
@RestController
@RequestMapping("/apply")
public class ApplyController {

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
    private ProcessEngine processEngine;

    @Autowired
    private ManagementService managementService;

    /**
     * 流程部署(暂定指定classpath下指定的单个流程文件)
     **/
    @RequestMapping("/deployDef")
    public AjaxResult deployDef(String xmlName) {
        // RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(xmlName)
                .deploy();
        // 一个流程定义对应多个流程实例
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        return AjaxResultUtil.success("BPMN文件部署成功:" + processDefinition.getName());
        //个体所有员工添加权限
//        List<MyUser> list = userMapper.findAll();
//        for (MyUser u : list ) {
//            authUserMappingMapper.insertMapping(u.getUserId(), processDefinition.getId());
//        }
//        return Msg.success();
    }


    /**
     * 启动流程
     * 根据部署流程的key启动一个流程(以最新版本的流程为参考)
     **/
    @RequestMapping("/startFlow")
    public AjaxResult startFlow(@RequestBody JSONObject jsonObject) {
        // 将JSONObject对象转化为Map<String,Object>,其中taskCustomManager为客户经理信息
        Map<String, Object> params = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String, Object>>() {
        });
        // 启动任务实例,并存储临时变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("apply", params);
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }


    /**
     * 获取指定用户对应的任务
     */
    @RequestMapping("/getTask")
    public void getTask(String userId, String defKey) {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userId)
                .processDefinitionKey(defKey)
                .list();
        for (Task t : list) {
            System.out.println(t.getId() + "   " + t.getName() + "   " + t.getTaskDefinitionKey());
        }
    }

    /**
     * 获取指定用户发起的流程实例列表(历史记录)
     *
     * @param userId
     * @return 流程实例列表
     */
    @RequestMapping("/getStartProcByHistory")
    public AjaxResult getStartProcByHistory(String userId) {
        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .startedBy(userId)
                .orderByProcessInstanceStartTime()
                .asc()
                .list();
        return AjaxResultUtil.success("list", list);
    }

    /**
     * 完成任务
     **/
    @RequestMapping("/completeTask")
    public AjaxResult completeTask(String taskId) {
        taskService.complete(taskId);
        return AjaxResultUtil.success();
    }

    /**
     * 获取指定流程实例状态
     **/
    @RequestMapping("/getProcessInstanceState")
    public AjaxResult getProcessInstanceState(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance != null) {
            return AjaxResultUtil.success("当前流程实例正在运行");
        } else {
            return AjaxResultUtil.success("当前流程实例已经结束");
        }
    }

    /**
     * 查询执行实例
     **/
    @RequestMapping("/getExecution")
    public void getExecution() {
        List<Execution> executionList = runtimeService.createExecutionQuery().list();
        for (Execution execution : executionList) {
            System.out.println(execution.getId());
        }
    }

    /**
     * 历史数据查询:历史流程实例数据查询
     **/
    @RequestMapping("getHistoryProcessInstance")
    public AjaxResult getHistoryProcessInstance(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (historicProcessInstance != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("流程定义ID", historicProcessInstance.getProcessDefinitionId());
            map.put("流程实例ID", historicProcessInstance.getId());
            map.put("流程开始节点", historicProcessInstance.getStartActivityId());
            map.put("流程结束节点", historicProcessInstance.getEndActivityId());
            map.put("流程开始时间", historicProcessInstance.getStartTime());
            map.put("流程结束时间", historicProcessInstance.getEndTime());
            return AjaxResultUtil.success(map);
        } else {
            return AjaxResultUtil.success("指定历史流程实例不存在");
        }
    }

    /**
     * 历史数据查询:历史活动数据查询
     **/
    @RequestMapping("getHistoryActivityInstance")
    public AjaxResult getHistoryActivityInstance(String processInstanceId) {
        List<HistoricActivityInstance> historyActivityInstanceList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        List<Map<String, Object>> list = new ArrayList<>();
        for (HistoricActivityInstance historyActivityInstance : historyActivityInstanceList) {
            Map<String, Object> map = new HashMap<>();
            map.put("流程定义ID", historyActivityInstance.getProcessDefinitionId());
            map.put("活动ID", historyActivityInstance.getId());
            map.put("活动开始时间", historyActivityInstance.getStartTime());
            map.put("活动结束时间", historyActivityInstance.getEndTime());
            map.put("间隔时间", historyActivityInstance.getDurationInMillis());
            list.add(map);
        }
        return AjaxResultUtil.success("list", list);
    }

    /**
     * 历史数据查询:历史任务数据查询
     **/
    @RequestMapping("getHistoryTaskInstance")
    public void getHistoryTaskInstance() {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().list();
        System.out.println(historicTaskInstanceList);
    }

    /**
     * 启动流程:设定流程实例的启动人
     **/
    @RequestMapping("/startFlowByUser")
    public AjaxResult startFlowByUser(String userId) {
        // 方式1：通过IdentityService指定流程启动人
        identityService.setAuthenticatedUserId(userId);
        // 方式2：通过Authentication指定启动人
        // Authentication.setAuthenticatedUserId(userId);
        // 启动任务实例,并存储临时变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("apply");
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }

    /**
     * 启动流程:initiator结合流程实例启动人使用
     **/
    @RequestMapping("/startFlowWithInitiator")
    public AjaxResult startFlowWithInitiator(String userId) {
        // 方式1：通过IdentityService指定流程启动人
        identityService.setAuthenticatedUserId(userId);
        // 方式2：通过Authentication指定启动人
        // Authentication.setAuthenticatedUserId(userId);
        // 启动任务实例,并存储临时变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("apply");
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }

    /**
     * 获取dataObject
     **/
    @RequestMapping("/getDataObject")
    public void getDataObject(String executionId,String dataObject) {
        // 根据实例id和变量值获取dataObject
        DataObject object = runtimeService.getDataObject(executionId,dataObject);
        // 获取指定流程实例所有dataObject:Map<String, DataObject> dataObjectMap = runtimeService.getDataObjects(executionId);
        if(object!=null){
            System.out.println(object.getDataObjectDefinitionKey());
            System.out.println(object.getDescription());
            System.out.println(object.getExecutionId());
            System.out.println(object.getName());
            System.out.println(object.getValue());
            System.out.println(object.getType());
        }
    }

    /** 删除流程实例 **/
    @RequestMapping("/delProcessInstance")
    public void delProcessInstance(String processInstanceId) {
        runtimeService.deleteProcessInstance(processInstanceId,"删除流程实例");
    }

    /** 级联删除流程实例 **/
    @RequestMapping("/delProcessInstanceCascade")
    public void delProcessInstanceCascade(String processInstanceId) {
        // 级联删除流程实例没有提供封装好的API调用,可通过ProcressEngine
        // ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration();
        // config.getExecutionEntityManager().deleteProcessInstance(processInstanceId,"级联删除流程实例",true);
        managementService.executeCommand(new CustomCmd(processInstanceId,"通过自定义命令执行级联删除流程实例操作"));
    }


    /** 流程实例获取运行的活动节点 **/
    @RequestMapping("/getActivityInstance")
    public void getActivityInstance(String processInstanceId) {
        List<ActivityInstance> activityInstanceList = runtimeService.createActivityInstanceQuery().processInstanceId(processInstanceId).list();
        System.out.println(activityInstanceList);
    }

}
