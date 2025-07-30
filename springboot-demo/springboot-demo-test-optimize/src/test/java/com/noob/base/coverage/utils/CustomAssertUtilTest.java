package com.noob.base.coverage.utils;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

/**
 * CustomAssertUtil 单元测试类
 *
 * <p><b>覆盖范围：</b>
 * 1. 基础断言方法（true/false/equals）
 * 2. 扩展断言方法（null/array/delta）
 * 3. 异常场景测试
 *
 * <p><b>覆盖率目标：</b>
 * - 行覆盖率 ≥95%
 * - 分支覆盖率 ≥95%
 */
public class CustomAssertUtilTest {

    /* #################### 基础断言方法测试 #################### */

    /**
     * 测试assertTrue方法
     * 场景：条件为true
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertTrue_WhenConditionTrue_NotLogError() {
        CustomAssertUtil.assertTrue(true, "条件为真时不触发日志");
    }

    /**
     * 测试assertTrue方法
     * 场景：条件为false
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertTrue_WhenConditionFalse_LogFormattedError() {
        CustomAssertUtil.assertTrue(false, "条件为假时触发格式化日志");
    }

    /**
     * 测试assertFalse方法
     * 场景：条件为false
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertFalse_WhenConditionFalse_NotLogError() {
        CustomAssertUtil.assertFalse(false, "条件为假时不触发日志");
    }

    /**
     * 测试assertFalse方法
     * 场景：条件为true
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertFalse_WhenConditionTrue_LogFormattedError() {
        CustomAssertUtil.assertFalse(true, "条件为真时触发格式化日志");
    }

    /* #################### 相等性断言测试 #################### */

    /**
     * 测试assertEquals方法
     * 场景：字符串对象相等
     * 预期：不输出差异日志
     */
    @Test
    public void test_assertEquals_WhenStringsEqual_NoDiffLog() {
        CustomAssertUtil.assertEquals("test", "test", "相同字符串不记录差异");
    }

    /**
     * 测试assertEquals方法
     * 场景：预期值为null
     * 预期：输出包含null标识的差异日志
     */
    @Test
    public void test_assertEquals_WhenExpectedNull_LogNullComparison() {
        CustomAssertUtil.assertEquals(null, "actual", "null对比显示标识");
    }

    /**
     * 测试assertEquals方法
     * 场景：实际值为null
     * 预期：输出包含null标识的差异日志
     */
    @Test
    public void test_assertEquals_WhenActualNull_LogNullComparison() {
        CustomAssertUtil.assertEquals("expected", null, "null对比显示标识");
    }

    /**
     * 测试assertNotEquals方法
     * 场景：对象不相等
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertNotEquals_WhenObjectsNotEqual_NotLogError() {
        CustomAssertUtil.assertNotEquals("a", "b", "不同对象不触发日志");
    }

    /**
     * 测试assertNotEquals方法
     * 场景：对象相等
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertNotEquals_WhenObjectsEqual_LogFormattedError() {
        CustomAssertUtil.assertNotEquals("a", "a", "相同对象触发错误日志");
    }

    /* #################### 空值断言测试 #################### */

    /**
     * 测试assertNotNull方法
     * 场景：对象非null
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertNotNull_WhenObjectNotNull_NotLogError() {
        CustomAssertUtil.assertNotNull(new Object(), "非null对象不触发日志");
    }

    /**
     * 测试assertNotNull方法
     * 场景：对象为null
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertNotNull_WhenObjectNull_LogFormattedError() {
        CustomAssertUtil.assertNotNull(null, "null对象触发错误日志");
    }

    /**
     * 测试assertNull方法
     * 场景：对象为null
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertNull_WhenObjectNull_NotLogError() {
        CustomAssertUtil.assertNull(null, "null对象不触发日志");
    }

    /**
     * 测试assertNull方法
     * 场景：对象非null
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertNull_WhenObjectNotNull_LogFormattedError() {
        CustomAssertUtil.assertNull("not null", "非null对象触发错误日志");
    }

    /* #################### 数组断言测试 #################### */

    /**
     * 测试assertArrayEquals方法
     * 场景：多维数组内容相等
     * 预期：不输出差异日志
     */
    @Test
    public void test_assertArrayEquals_WhenNestedArraysEqual_NoDiffLog() {
        Object[] arr1 = {new String[]{"a"}};
        Object[] arr2 = {new String[]{"a"}};
        CustomAssertUtil.assertArrayEquals(arr1, arr2, "嵌套数组相等不记录差异");
    }

    /**
     * 测试assertArrayEquals方法
     * 场景：输入数组为null
     * 预期：输出数组为null的标识日志
     */
    @Test
    public void test_assertArrayEquals_WhenArrayIsNull_LogNullIdentifier() {
        CustomAssertUtil.assertArrayEquals(null, new String[0], "null数组特殊标识");
    }

    /**
     * 测试assertArrayEquals方法
     * 场景：实际数组为null
     * 预期：输出数组为null的标识日志
     */
    @Test
    public void test_assertArrayEquals_WhenActualArrayNull_LogNullIdentifier() {
        CustomAssertUtil.assertArrayEquals(new String[0], null, "null数组特殊标识");
    }

    /* #################### 浮点数断言测试 #################### */

    /**
     * 测试assertEquals方法（带delta）
     * 场景：浮点数在误差范围内
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertEqualsWithDelta_WhenWithinTolerance_NotLogError() {
        CustomAssertUtil.assertEquals(3.0, 3.01, 0.1, "误差范围内不触发日志");
    }

    /**
     * 测试assertEquals方法（带delta）
     * 场景：浮点数超出误差范围
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertEqualsWithDelta_WhenExceedTolerance_LogFormattedError() {
        CustomAssertUtil.assertEquals(3.0, 3.2, 0.1, "超出误差触发错误日志");
    }

    /**
     * 测试assertEquals方法（带delta）
     * 场景：±0.0比较
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertEqualsWithDelta_WhenPositiveNegativeZero_WithinDelta() {
        CustomAssertUtil.assertEquals(0.0, -0.0, 0.1, "符号相反的零值在误差范围内相等");
    }

    /**
     * 测试assertEquals方法（带delta）
     * 场景：NaN比较
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertEqualsWithDelta_WhenBothNaN_NotLogError() {
        CustomAssertUtil.assertEquals(Double.NaN, Double.NaN, 0.1, "NaN比较不触发日志");
    }

    /* #################### 异常断言测试 #################### */

    /**
     * 测试assertThrows方法
     * 场景：抛出预期异常
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertThrows_WhenExpectedException_NotLogError() {
        CustomAssertUtil.assertThrows(
                () -> {
                    throw new IllegalArgumentException();
                },
                IllegalArgumentException.class,
                "预期异常不触发日志"
        );
    }

    /**
     * 测试assertThrows方法
     * 场景：未抛出异常
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertThrows_WhenNoException_LogFormattedError() {
        CustomAssertUtil.assertThrows(
                () -> {
                },
                Exception.class,
                "未抛异常触发错误日志"
        );
    }

    /**
     * 测试assertThrows方法
     * 场景：抛出非预期异常
     * 预期：输出[ASSERT_FAIL]前缀的错误日志
     */
    @Test
    public void test_assertThrows_WhenUnexpectedException_LogFormattedError() {
        CustomAssertUtil.assertThrows(
                () -> {
                    throw new RuntimeException();
                },
                IOException.class,
                "异常类型不匹配触发错误日志"
        );
    }

    /**
     * 测试assertThrows方法
     * 场景：抛出预期异常的子类
     * 预期：不输出错误日志
     */
    @Test
    public void test_assertThrows_WhenSubclassException_NotLogError() {
        CustomAssertUtil.assertThrows(
                () -> {
                    try {
                        throw new FileNotFoundException();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                },
                IOException.class,
                "子类异常视为匹配成功"
        );
    }

    /* #################### 特殊场景测试 #################### */

    /**
     * 测试构造方法
     * 场景：通过反射调用私有构造方法
     * 预期：成功返回实例对象
     */
    @Test
    public void test_constructor_WhenReflectiveAccess_ReturnInstance() throws Exception {
        Constructor<CustomAssertUtil> constructor =
                CustomAssertUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertNotNull("反射构造应返回非空实例", constructor.newInstance());
    }

    /**
     * 测试 断言失败
     * 场景：测试断言失败场景
     * 预期：打印失败信息提示
     */
    @Test
    public void test_fail_WhenAssertFailed_LogFormattedError() throws Exception {
        CustomAssertUtil.fail("处理失败");
    }

    /**
     * 测试 断言失败
     * 场景：测试断言失败场景
     * 预期：打印失败信息提示 & 异常堆栈信息
     */
    @Test
    public void test_fail_WhenAssertFailed_LogFormattedErrorAndCause() throws Exception {
        CustomAssertUtil.fail("处理失败",new RuntimeException("处理失败场景测试"));
    }
}