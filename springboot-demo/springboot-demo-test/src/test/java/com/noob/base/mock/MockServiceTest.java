package com.noob.base.mock;

import com.noob.base.mock.service.ServiceA;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockServiceTest {

//    @Mock // 不会真正执行方法（模拟调用自定义响应值，便于开发调试，实际不会调用方法，无法调整覆盖率）
    @Autowired
    private ServiceA serviceA;

    @Test
    void contextLoads() {
        serviceA.methodA();
    }

}