package com.noob.base.mock;

import com.noob.base.mock.service.OperatorService;
import com.noob.base.mock.service.ServiceA;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockitoTest {

    @Mock
    private OperatorService operatorService;

    @Test
    void testMockito(){
        Mockito.when(operatorService.add(1,2)).thenReturn(10);
    }

}