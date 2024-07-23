package com.noob.base.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.demo.constant.HeaderConstants;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JUnit 5
 */
//@ActiveProfiles("test") // 激活配置
//@SpringBootTest(classes = DemoApplication.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@WebMvcTest(controllers = DemoController.class)  // 限定测试范围,不会加载整个应用程序上下文（加快测试速度，专注于对web层测试）
class DemoControllerTest2 {

    private static final Logger log = LoggerFactory.getLogger(DemoControllerTest2.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private HttpHeaders headers;

    // 配置访问根路径
    private String baseUrl = "/api/demo";  // MvcMock测试运行独立于配置的servlet上下文路径

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
        headerMap.add(HeaderConstants.COUNTRY,"CN");
        headerMap.add(HeaderConstants.APP_LANG,"Chinese");
        headerMap.add(HeaderConstants.USERNAME,"holic-x");
        headerMap.add(HeaderConstants.TIMESTAMP,String.valueOf(new Date().getTime()));
        headers = new HttpHeaders();
        headers.addAll(headerMap);
        // 权限限定(获取到权限配置，进行鉴权)，模拟拥有权限访问某个资源
    }

    @SneakyThrows
    @Test
    void getName() {
        String url = baseUrl + "/getName";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url) // 接口访问路径
                .headers(headers) // header配置
                .contextPath("/api") // MvcMock测试运行独立于配置的servlet上下文路径
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @SneakyThrows
    @Test
    void showInfo() {
        // 配置访问路径
        String url = baseUrl + "/showInfo";
        // 构建请求参数
        String name = "hahaha";
        // Mock构建请求
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url) // 接口访问路径
                        .contextPath("/api") // MvcMock测试运行独立于配置的servlet上下文路径
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE) // content-type 配置
                        .param("name",name) // 构建 @RequestParam 参数
                        .param("age","18")
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @SneakyThrows
    @Test
    void showJson() {
        // 配置访问路径
        String url = baseUrl + "/showJson";

        // 构建请求参数
        JSONObject requestJson = new JSONObject();
        requestJson.put("id", "1");
        requestJson.put("name", "hahaha");

        // Mock构建请求
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url) // 接口访问路径
                        .contextPath("/api") // MvcMock测试运行独立于配置的servlet上下文路径
                        .contentType(MediaType.APPLICATION_JSON) // content-type 配置
                        .content(requestJson.toJSONString()) // 请求参数内容
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @SneakyThrows
    @Test
    void showNameWithHeader() {
        // 方式1：@PathVariable 参数构建，配置访问路径(参数装配在URL中)
        String requestUrl = baseUrl + "/showNameWithHeader/" + "哈哈哈" ;

        // 方式2
        String url = baseUrl + "/showNameWithHeader/{name}" ;
        // 构建请求参数
        String name = "哈哈哈";
        // Mock构建请求
//         MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(requestUrl) // 接口访问路径
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url,name) // 接口访问路径
                        .headers(headers) // header配置
                        .contextPath("/api") // MvcMock测试运行独立于配置的servlet上下文路径
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE) // content-type 配置
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }
}