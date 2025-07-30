package com.noob.base.testExam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

/**
 * 基于Mockito + Junit4 + JDK8 的 覆盖场景样例
 */
@RunWith(MockitoJUnitRunner.class) // 限定运行环境
public class MockitoDemoTest {

    // 1.基本方法调用验证
    @Test // JUnit4 @Test 注解
    public void testMethodInvocation() {
        List<String> mockList = Mockito.mock(List.class);
        mockList.add("one");
        mockList.add("two");

        // 验证是否调用了 add("one") 1次
        Mockito.verify(mockList, Mockito.times(1)).add("one");
        // 验证是否调用了 add("two") 至少1次
        Mockito.verify(mockList, Mockito.atLeastOnce()).add("two");
        // 验证是否从未调用 remove()
        Mockito.verify(mockList, Mockito.never()).remove(any());
    }

    // 2.模拟返回值（Stubbing）
    @Test
    public void testMockReturnValue() {
        List<String> mockList = Mockito.mock(List.class);
        // 模拟 get(0) 返回 "first"
        Mockito.when(mockList.get(0)).thenReturn("first");
        // 模拟 get(1) 抛出异常
        Mockito.when(mockList.get(1)).thenThrow(new RuntimeException("Index out of bounds"));

        assertEquals("first", mockList.get(0));
        assertThrows(RuntimeException.class, () -> mockList.get(1));
    }

    // 3.参数匹配（Argument Matchers）
    @Test
    public void testArgumentMatchers() {
        List<String> mockList = Mockito.mock(List.class);
        // 模拟任意 int 参数调用 get() 都返回 "element"
        Mockito.when(mockList.get(Mockito.anyInt())).thenReturn("element");

        assertEquals("element", mockList.get(0));
        assertEquals("element", mockList.get(999));
    }

    // 4.验证调用顺序
    @Test
    public void testInvocationOrder() {
        List<String> mockList = Mockito.mock(List.class);
        mockList.add("first");
        mockList.add("second");

        InOrder inOrder = Mockito.inOrder(mockList);
        inOrder.verify(mockList).add("first");
        inOrder.verify(mockList).add("second");
    }

    // 5.模拟 Void 方法（无返回值方法）
    @Test
    public void testVoidMethod() {
        List<String> mockList = Mockito.mock(List.class);
        // 模拟 clear() 抛出异常
        Mockito.doThrow(new RuntimeException("Clear failed")).when(mockList).clear();

        assertThrows(RuntimeException.class, mockList::clear);
    }

    // 6.部分 Mock（Spy）
    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> spyList = Mockito.spy(realList);

        // 真实调用 add()
        spyList.add("real");
        // 模拟 get(0) 返回 "mocked"
        Mockito.when(spyList.get(0)).thenReturn("mocked");

        assertEquals("mocked", spyList.get(0)); // Mocked
        assertEquals(1, spyList.size());      // 真实调用
    }

    // 7.捕获方法参数（ArgumentCaptor）
    @Test
    public void testArgumentCaptor() {
        List<String> mockList = Mockito.mock(List.class);
        mockList.add("captured");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mockList).add(captor.capture());

        assertEquals("captured", captor.getValue());
    }

}
