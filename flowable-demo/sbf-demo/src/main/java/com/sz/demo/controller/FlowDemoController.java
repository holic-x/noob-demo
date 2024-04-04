package com.sz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sz.demo.util.AjaxResult;
import com.sz.demo.util.AjaxResultUtil;
import org.flowable.bpmn.model.*;
import org.flowable.engine.*;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlowDemoController
 * @Description 流程测试Demo
 * @Author
 * @Date 2020/6/14 14:29
 * @Version
 **/

@RestController
@RequestMapping("/flowDemo")
public class FlowDemoController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;


    private static final Logger logger = LoggerFactory.getLogger(FlowDemoController.class);

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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("sbf-demo-single", params);
        return AjaxResultUtil.success("流程启动成功:" + processInstance.getProcessInstanceId());
    }

    /**
     * 根据流程实例id获取相应的运行实例
     **/
    @RequestMapping("/getProcessInstanceById")
    public AjaxResult getProcessInstanceById(String id) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        return AjaxResultUtil.success("processInstance", processInstance.getName());
    }

    /**
     * 根据流程实例id获取相应的运行实例关联的变量
     **/
    @RequestMapping("/getVariablesById")
    public AjaxResult getVariablesById(String id) {
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        // 流程进行中,获取流程实例的参数
        Map<String, Object> variables = runtimeService.getVariables(id);
        return AjaxResultUtil.success("variables", variables);
    }

    /**
     * 流程执行对象查询操作
     **/
    @RequestMapping("/getExecutionQuery")
    public void getExecutionQuery() {
        Map<String, Object> variables = new HashMap<String, Object>();
        //流程执行对象
        List<Execution> executions = runtimeService.createExecutionQuery().list();
        for (Execution execution : executions) {
            logger.info("execution = {}", execution);
        }
    }

    /**
     * 根据用户信息获取审批管理列表
     */
    @RequestMapping(value = "/getTaskListByUser")
    @ResponseBody
    public AjaxResult getTaskListByUser(String userNum) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userNum).orderByTaskCreateTime().desc().list();
        for (Task task : taskList) {
            System.out.println(task.toString());
        }
        return AjaxResultUtil.success();
    }


    /**
     * 产品经理进行业务审核
     */
    @RequestMapping("/checkInfo")
    public AjaxResult checkInfo(String taskId,String userNum) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("taskProductManager", userNum);
        variables.put("check_flag","0");
        taskService.complete(taskId, variables);
        return AjaxResultUtil.success("产品经理审核数据,跳转下一流程节点");
    }

    /**
     * 产品经理进行业务审核
     */
    @RequestMapping("/pass")
    public AjaxResult pass(String taskId,String userNum) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("taskProductManager", userNum);
        variables.put("check_flag","0");
        taskService.complete(taskId, variables);
        return AjaxResultUtil.success("产品经理审核数据,跳转下一流程节点");
    }




    /**
     * 审核通过
     */
//    @RequestMapping("/pass")
//    public String pass(String taskId) {
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if (task == null) {
//            throw new RuntimeException("流程不存在");
//        }
//        //通过审核
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("outcome", "通过");
//        taskService.complete(taskId, map);
//        return "processed ok!";
//    }

    /**
     * 拒绝
     */
    @ResponseBody
    @RequestMapping("/reject")
    public String reject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        taskService.complete(taskId, map);
        return "reject";
    }









    /**
     * 生成流程图
     *
     * @param processId 任务ID
     */
    @RequestMapping("/processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//
//        //流程走完的不显示图
//        if (pi == null) {
//            return;
//        }
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        String InstanceId = task.getProcessInstanceId();
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(InstanceId)
//                .list();
//
//        //得到正在执行的Activity的Id
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        //获取流程图
////        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
////        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
////        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
////        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in =
//                diagramGenerator
//                        .generateDiagram(bpmnModel, "png", activityIds, flows, "宋体", "宋体", "宋体", engconf.getClassLoader(), 1.0, true);
//
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = httpServletResponse.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
    }





    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @RequestMapping(value = "apply")
    @ResponseBody
    public String apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return "processed ok!";
    }






}
