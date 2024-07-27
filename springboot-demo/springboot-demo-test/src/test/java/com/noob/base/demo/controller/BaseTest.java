package com.noob.base.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * JUnit4版本
 */
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
@WebAppConfiguration(value = "src/main/webapp")

/*
// JUnit4
//@RunWith(SpringJUnit4ClassRunner.class)
////指定容器层次，即spring-config.xml是父容器，而spring-mvc.xml是子容器
//@ContextHierarchy({
//        @ContextConfiguration(name = "parent", locations = "classpath:spring-application.xml"),
//        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc-servlet.xml")
//})
*/

//声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
//@Transactional

public class BaseTest {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;

    /*
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //        mockMvc = MockMvcBuilders.standaloneSetup(new GoodNeighborController()).build();

    }
     */
}
