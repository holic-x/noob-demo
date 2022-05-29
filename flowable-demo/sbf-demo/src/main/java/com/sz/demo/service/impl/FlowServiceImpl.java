package com.sz.demo.service.impl;


import com.sz.demo.service.FlowService;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.flowable.bpmn.model.Process;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlowServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/6/13 8:13
 * @Version
 **/
public class FlowServiceImpl implements FlowService {

    private  final Logger log = LoggerFactory.getLogger(FlowServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;


    /**
     * 1.Flowable的流程部署通过仓库服务来完成部署:仓库服务的接口为RepositoryService,该接口通过创建DeploymentBuilder来完成部署
     * 2.部署前借助BpmnXMLConverter接口把文件转换为BPMNModel进行校验，通过API接口convertToBpmnModel来实现xml到模型的转换
     * 3.加载完成流程后，借助启动接口runtimeService启动流程，运行时服务调用startProcessInstanceByKey启动一个流程，并且返回流程对象ProcessInstance(包含一个流程ID)
     **/

    @Override
    public Map<String, Object> createFlow(String filePath) {

        Map<String,Object> res = new HashMap<>();
        //解析BPMN模型看是否成功
        XMLStreamReader reader = null;
        InputStream inputStream = null;
        try {
            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            inputStream=new FileInputStream(new File(filePath));
            reader = factory.createXMLStreamReader(inputStream);
            BpmnModel model = bpmnXMLConverter.convertToBpmnModel(reader);
            List<Process> processes = model.getProcesses();
            Process curProcess = null;
            if (CollectionUtils.isEmpty(processes)) {
                log.error("BPMN模型没有配置流程");
                return null;
            }
            res.put("processes",processes);
            curProcess = processes.get(0);
            inputStream=new FileInputStream(new File(filePath));
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("TEST_FLOW")
                    .addInputStream(filePath,inputStream);

            Deployment deployment= deploymentBuilder.deploy();
            res.put("deployment",deployment);
            System.out.print("BPMN模型没有配置流程");
            log.warn("部署流程 name:"+curProcess.getName()+" key "+deployment.getKey() + " deploy "+deployment);
            return res;
        }
        catch (Exception e){
            log.error("BPMN模型创建流程异常",e);
            return null;
        }
        finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                log.error("关闭异常",e);
            }
        }
    }

    @Override
    public ProcessInstance startFlow(String processKey, Map<String, Object> paras) {
        if (StringUtils.isEmpty(processKey)){
            return null;
        }

        if (null == paras){
            paras = new HashMap<>();
        }

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey(processKey).singleResult();

        if (deployment == null){
            log.error("没有该流程");
            return  null;
        }

        return runtimeService.startProcessInstanceByKey(processKey,paras);
    }
}
