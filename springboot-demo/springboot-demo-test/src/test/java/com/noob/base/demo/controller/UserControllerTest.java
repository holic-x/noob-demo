package com.noob.base.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.noob.base.demo.constant.HeaderConstants;
import com.noob.base.demo.model.entity.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private HttpHeaders headers;

    // 配置访问根路径
    private String baseUrl = "/user"; // MvcMock测试运行独立于配置的servlet上下文路径

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
        headerMap.add(HeaderConstants.USER_TOKEN,"holic-x");
        headerMap.add(HeaderConstants.TIMESTAMP,String.valueOf(new Date().getTime()));
        headers = new HttpHeaders();
        headers.addAll(headerMap);
        // 权限限定(获取到权限配置，进行鉴权)，模拟拥有权限访问某个资源
    }

    @Transactional // 默认情况下在每个测试方法结束时回滚事务（但如果使用的是RANDOM_PORT或DEFINED_PORT的提供了一个真正的servlet环境的情况下，回滚失效）
    @SneakyThrows
    @Test
    void add() {
        // 接口访问路径
        String url = baseUrl + "/add";
        // 模拟数据
        User user = new User("noob",18);
        // 模拟接口调用
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url) // 接口访问路径
                        .headers(headers) // header配置
                        .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                        .content(JSONObject.toJSONBytes(user))
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @Transactional
    @SneakyThrows
    @Test
    void delete() {
        // 接口访问路径
        String url = baseUrl + "/delete";
        // 模拟接口调用
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url) // 接口访问路径
                        .headers(headers) // header配置
                        .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                        .param("id","1")
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @Transactional
    @SneakyThrows
    @Test
    void update() {
        // 接口访问路径
        String url = baseUrl + "/update";
        // 模拟数据
        User user = new User();
        user.setId(1);
        user.setName("noob");
        user.setAge(18);

        // 模拟接口调用
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url) // 接口访问路径
                        .headers(headers) // header配置
                        .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                        .content(JSONObject.toJSONBytes(user))
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @SneakyThrows
    @Test
    void get() {
        // 接口访问路径
        String url = baseUrl + "/get";
        // 模拟接口调用
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url) // 接口访问路径
                        .headers(headers) // header配置
                        .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                        .param("id","1")
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

    @SneakyThrows
    @Test
    void getByCond() {
        // 接口访问路径
        String url = baseUrl + "/getByCond";
        // 模拟接口调用
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url) // 接口访问路径
                        .headers(headers) // header配置
                        .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                        .param("searchKey","小")
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }
}