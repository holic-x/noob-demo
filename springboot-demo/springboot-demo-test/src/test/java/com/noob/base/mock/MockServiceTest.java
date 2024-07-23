package com.noob.base.mock;

import com.noob.base.mock.service.OperatorService;
import com.noob.base.mock.service.ServiceA;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockServiceTest {

    @Autowired
    private ServiceA serviceA;

    @Autowired
    private OperatorService operatorService;

    @Test
    void testServiceA() {
        serviceA.methodA();
    }

    @Test
    void testAutowire(){
        System.out.println(operatorService.add(1,2));
        System.out.println(operatorService.mul(5,10));
    }

}