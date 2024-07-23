package com.noob.base.demo.controller;

import com.noob.base.demo.constant.HeaderConstants;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JUnit 5
 */
// @SpringBootTest(classes = DemoApplication.class)
@SpringBootTest
class DemoControllerTest1 {

    private static final Logger log = LoggerFactory.getLogger(DemoControllerTest1.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private HttpHeaders headers;

    // 配置访问根路径
//    private String baseUrl = "/api/demo";  // MvcMock测试运行独立于配置的servlet上下文路径
    private String baseUrl = "/demo";

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
                .contentType(MediaType.APPLICATION_JSON)// content-type 配置
                ).andExpect(MockMvcResultMatchers.status().isOk()) // 预期结果
                .andReturn();
        // 查看响应结果
        String res = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info(res);
    }

}