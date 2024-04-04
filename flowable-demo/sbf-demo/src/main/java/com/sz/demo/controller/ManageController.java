package com.sz.demo.controller;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.sz.demo.util.AjaxResult;
import com.sz.demo.util.AjaxResultUtil;
import org.apache.commons.io.FileUtils;
import org.flowable.bpmn.model.*;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ManageController
 * @Description 流程管理相关
 * @Author
 * @Date 2020/6/12 1:00
 * @Version
 **/

@RestController
@RequestMapping("/manage")
public class ManageController {


    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

//    @RequestMapping("/create")
//    @ResponseBody
//    public Map<String,Object> createFlow(){
//        Map<String,Object> res =new HashMap<>();
//        Map<String,Object> data = new HashMap<>();
//
//        String flowPath ="E:\\flowablestudy\\flowablech5\\src\\main\\resources\\flows\\测试BPMN模型2.bpmn20.xml";
//        // 发布流程指定bpmn流程文件名称(例如dqzm-test.bpmn20.xml)
//        Map<String,Object> createRes = flowService.createFlow(flowPath);
//
//        if (null == createRes){
//            res.put("msg","创建流程失败");
//            res.put("res","0");
//            res.put("data",data);
//            return res;
//        }
//        List<Process> processes =(List<Process>)createRes.get("processes");
//
//        ArrayList<String> ids = new ArrayList<>();
//        for (Process process :processes){
//            ids.add(process.getId());
//        }
//        data.put("processKeys",ids);
//        data.put("deployId",((Deployment)createRes.get("deployment")).getId());
//        res.put("data",data);
//        res.put("msg","创建流程成功");
//        res.put("res","1");
//        return res;
//    }

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
        return AjaxResultUtil.success("BPMN文件部署成功:"+processDefinition.getName());
        //个体所有员工添加权限
//        List<MyUser> list = userMapper.findAll();
//        for (MyUser u : list ) {
//            authUserMappingMapper.insertMapping(u.getUserId(), processDefinition.getId());
//        }
//        return Msg.success();
    }

    /**
     * 获取指定部署id的流程图
     **/
    @RequestMapping("/getImage")
    public void getImage(String deployId) throws IOException {
        List<String> deploymentResource = repositoryService.getDeploymentResourceNames(deployId);
        System.out.println(deploymentResource);
        String imageName = null;
        for(String name : deploymentResource){
            if(name.indexOf(".png")>0){
                imageName = name;
            }
        }
        // 加载文件
        if(imageName!=null){
            File file = new File("E:\\"+imageName);
            InputStream resourceAsStream = repositoryService.getResourceAsStream(deployId,imageName);
            FileUtils.copyInputStreamToFile(resourceAsStream,file);
        }
    }


    /**
     * 流程定义-流程激活/挂起(根据id)
     **/
    @RequestMapping("/changeDefStatusById")
    public AjaxResult changeDefStatusById(String defId) {
        // 如果当前流程定义被挂起则执行激活操作
        if (repositoryService.isProcessDefinitionSuspended(defId)) {
            repositoryService.activateProcessDefinitionById(defId);
            return AjaxResultUtil.success("激活流程定义成功");
        } else {
            repositoryService.suspendProcessDefinitionById(defId);
            return AjaxResultUtil.success("挂起流程定义成功");
        }
    }

    /**
     * 流程定义-流程激活(根据key值)
     **/
    @RequestMapping("/activeDefByKey")
    public AjaxResult activeDefByKey(String defKey) {
        // 实际项目应用中要考虑‘重复操作’触发的异常(一个key指向的流程定义可能存在多个版本)
        repositoryService.activateProcessDefinitionByKey(defKey);
        return AjaxResultUtil.success("激活流程定义成功");
    }

    /**
     * 流程定义-流程挂起(根据key值)
     **/
    @RequestMapping("/suspendDefByKey")
    public AjaxResult suspendDefByKey(String defKey) {
        // 实际项目应用中要考虑‘重复操作’触发的异常(一个key指向的流程定义可能存在多个版本)
        repositoryService.suspendProcessDefinitionByKey(defKey);
        return AjaxResultUtil.success("挂起流程定义成功");
    }

    /**
     * 流程定义-根据发布id查询相关资源（流程定义相关）
     **/
    @RequestMapping("/getDefByDeployId")
    public AjaxResult getDefByDeployId(String deployId) {
        // 获取指定发布id的发布数据
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
        // 获取指定发布id对应发布的流程定义(可结合业务需求进一步指定其余字段筛选条件)
        List<ProcessDefinition> defList = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).list();
        Map<String,Object> responseResult = new HashMap<>();
        // 封装部署信息
        responseResult.put("deployInfo",deployment.getId()+":"+deployment.getName());
        // 封装对应部署关联的流程定义信息
        for(ProcessDefinition def : defList){
            responseResult.put(def.getId(),def.getKey()+":"+def.getName());
        }
        return AjaxResultUtil.success(responseResult);
    }

    /**
     * 根据部署id删除部署的信息(指定级联操作标识)
     **/
    @RequestMapping("/deleteDeploy")
    public AjaxResult deleteDeploy(String deployId,Boolean cascade) {
        repositoryService.deleteDeployment(deployId,cascade);
        if(cascade){
            // 级联删除操作,相当于调用deleteDeploymentCascade方法
            return AjaxResultUtil.success("级联操作-删除部署信息成功");
        }else{
            return AjaxResultUtil.success("非级联操作-部署信息删除成功");
        }
    }


    /** 获取指定流程定义当前所有的实例运行情况 **/
    @RequestMapping("/getProcessInstanceByCond")
    public AjaxResult getProcessInstanceByCond() {
        List<JSONObject> dataList = new ArrayList<>();
        // 获取运行实例
        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
                .orderByProcessInstanceStartTime().asc()
                .list();
        for (HistoricProcessInstance hpi : historicProcessInstanceList) {
            JSONObject jsonObject = new JSONObject();
            //根据流程定义id找流程名
            String defId = hpi.getProcessDefinitionId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(defId).singleResult();
            jsonObject.put("def_Name",processDefinition.getName());
            jsonObject.put("def_key",processDefinition.getKey());
            jsonObject.put("hpi_id",hpi.getId());
            jsonObject.put("hpi_processName",hpi.getName());
            // 根据流程id从运行实例表中获取相应的运行实例(运行实例存在则说明流程还没有结束,如果不存在则说明流程已结束)
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(hpi.getId()).singleResult();
            if (pi == null) {
                jsonObject.put("currentStatus","已结束");
            } else {
                jsonObject.put("currentStatus","进行中");
                // 流程进行中,获取流程实例的参数
                Map<String, Object> variables = runtimeService.getVariables(pi.getId());
                jsonObject.put("variables",variables);
            }
            dataList.add(jsonObject);
        }
        Map<String,Object> responseResult = new HashMap<>();
        responseResult.put("dataList",dataList);
        return AjaxResultUtil.success(responseResult);
    }


    /**
     * 获取指定的任务节点信息
     * @param node   查询节点选择
     * @param taskId 任务id
     */
    @RequestMapping("/getFlowNode")
    public void getFlowNode(String node, String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ExecutionEntity ee = (ExecutionEntity) runtimeService.createExecutionQuery()
                .executionId(task.getExecutionId()).singleResult();
        // 当前审批节点
        String crruentActivityId = ee.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(crruentActivityId);
        // 输出连线
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();
        for (SequenceFlow sequenceFlow : outFlows) {
            // 当前审批节点
            if ("now".equals(node)) {
                FlowElement sourceFlowElement = sequenceFlow.getSourceFlowElement();
                System.out.println("当前节点: id=" + sourceFlowElement.getId() + ",name=" + sourceFlowElement.getName());
            } else if ("next".equals(node)) {
                // 下一个审批节点
                FlowElement targetFlow = sequenceFlow.getTargetFlowElement();
                if (targetFlow instanceof UserTask) {
                    System.out.println("下一节点: id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                }
                // 如果下个审批节点为结束节点
                if (targetFlow instanceof EndEvent) {
                    System.out.println("下一节点为结束节点：id=" + targetFlow.getId() + ",name=" + targetFlow.getName());
                }
            }
        }
    }

    /** 获取指定流程定义当前所有的运行实例 **/
//    @RequestMapping("/getProcessInstanceByCond")
//    public AjaxResult getProcessInstanceByCond() {
//        // 获取运行实例
//        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
//                .orderByProcessInstanceStartTime().asc()
//                .list();
//        for (HistoricProcessInstance hpi : historicProcessInstanceList) {
//            Common common = new Common();
//            //根据流程定义id找流程名
//            String prodefId = hpi.getProcessDefinitionId();
//            ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(prodefId).singleResult();
//            common.setProdefName(processDefinition.getName());
//            common.setId(hp.getId());
//            common.setProcessName(hp.getName());
//            common.setProdefKey(processDefinition.getKey());
//            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(hp.getId()).singleResult();
//            if (pi == null) {
//                //System.out.println("HelloController.findhadDoProcess");
//                common.setCurrentStatus("已结束");
//            } else {
//                //System.out.println("HelloControllerdsadsadsadsa");
//                common.setCurrentStatus("进行中");
//            }
//            lists.add(common);
//        }
//
//        return msg.add("lists", lists);
//    }

}
