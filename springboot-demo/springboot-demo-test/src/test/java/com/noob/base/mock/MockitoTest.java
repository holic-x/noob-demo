package com.noob.base.mock;

import com.noob.base.mock.service.OperatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Mockito 普通测试
 */
@SpringBootTest
class MockitoTest {

    // 模拟测试调用 返回自定义响应值（不会真正执行方法，而是按照自定义规则返回信息，便于开发调试，实际不会调用方法，需额外关注覆盖率）
    @Mock
    private OperatorService operatorService;

    @Test
    void testMockito(){
        // 配置Mock行为
        Mockito.when(operatorService.add(1,2)).thenReturn(100);
        System.out.println(operatorService.add(1,2)); // 配置生效1次
        System.out.println(operatorService.add(5,10));
        System.out.println(operatorService.mul(5,10));
    }

}