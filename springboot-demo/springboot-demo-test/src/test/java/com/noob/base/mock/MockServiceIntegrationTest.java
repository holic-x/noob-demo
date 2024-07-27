package com.noob.base.mock;

import com.noob.base.mock.service.ServiceA;
import com.noob.base.mock.service.ServiceB;
import com.noob.base.mock.service.impl.ServiceAImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

/**
 * MockServiceIntegrationTest todo
 */
class MockServiceIntegrationTest {

    // 要测试目标
    private ServiceA serviceA;

    // mock目标（可以是一个实体或service）
    @Mock
    private ServiceB serviceB;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // serviceA = new ServiceA(serviceB);
        serviceA = new ServiceAImpl();

        // 配置mock行为(此处因为ServiceA调用了ServiceB，因此ServiceB是需要mock的目标，而ServiceA为测试目标)
        Mockito.when(serviceB.methodB()).thenReturn(888);
    }

    @Test
    void testServiceA() {
        // 调用实际的服务方法
        String res1 = serviceA.methodA();
        // 验证结果是否符合预期
        assert res1.equals("success");
    }

}