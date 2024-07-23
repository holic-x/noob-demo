package com.noob.base.mock;

import com.noob.base.mock.service.ServiceA;
import com.noob.base.mock.service.ServiceB;
import com.noob.base.mock.service.impl.ServiceAImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockServiceDemoTest {

    // 要测试目标
    @InjectMocks
    private ServiceAImpl serviceA; // 此处测试的是实现类，区别于@Autowired

    // mock目标（可以是一个实体或service）
    @Mock
    private ServiceB serviceB;

    @Test
    void contextLoads() {
        // 配置mock行为
        Mockito.when(serviceB.methodB()).thenReturn(888);
        // 调用实际的服务方法
        String res1 = serviceA.methodA();
        // 验证结果是否符合预期
        assert res1.equals("success");

        // 配置mock行为
        Mockito.when(serviceB.methodB()).thenReturn(0);
        // 调用实际的服务方法
        String res2 = serviceA.methodA();
        // 验证结果是否符合预期
        assert res2.equals("fail");
    }

}