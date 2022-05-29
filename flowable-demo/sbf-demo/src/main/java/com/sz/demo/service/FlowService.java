package com.sz.demo.service;

import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * @ClassName FlowService
 * @Description TODO
 * @Author
 * @Date 2020/6/13 8:12
 * @Version
 **/
public interface FlowService {

    /**
     * 部署工作流
     */
    Map<String, Object> createFlow(String filePath);


    /**
     * 启动工作流
     */
    ProcessInstance startFlow(String processKey, Map<String,Object> paras);





}
