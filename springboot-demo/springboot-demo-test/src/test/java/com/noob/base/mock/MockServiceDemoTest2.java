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
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * MockService 测试
 */
@SpringBootTest
class MockServiceDemoTest2 {

    // 要测试目标
    @Autowired
    private ServiceA serviceA;

    // mock目标（可以是一个实体或service）
    @MockBean
    private ServiceB serviceB;

    @Test
    void testServiceA() {
        // 配置mock行为(此处因为ServiceA调用了ServiceB，因此ServiceB是需要mock的目标，而ServiceA为测试目标)
        Mockito.when(serviceB.methodB()).thenReturn(888);
        // 调用实际的服务方法
        String res1 = serviceA.methodA();
        // 验证结果是否符合预期
        assert res1.equals("success");
    }

}