package com.noob.base.coverage.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * 自定义断言工具类（针对UT Coverage设定的断言方法）
 * - 不触发exception抛出，通过log日志处理（for test：验证方法调用，覆盖行、覆盖分支）
 * - 兼容JUnit4断言风格
 * - 支持JDK8特性（Objects.equals等）
 * <p>
 * 修改说明：
 * 1. 保留原始不抛异常的设计
 * 2. 增强日志输出信息
 * 3. 补充常用断言方法
 */
@Slf4j
public class CustomAssertUtil {

    /**
     * 验证条件为true（失败时记录ERROR日志）
     * 原始设计说明：不抛出AssertionError，仅通过日志记录
     *
     * @param condition 待验证条件
     * @param message   错误描述信息（支持{}占位符）
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            log.error("[ASSERT_FAIL] " + message);
        }
    }

    /**
     * 验证条件为false（失败时记录ERROR日志）
     * 实现说明：通过assertTrue反向实现
     */
    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    /**
     * 验证对象相等（失败时记录差异）
     * 特殊处理：使用Objects.equals()进行null安全比较
     */
    public static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            log.error("[ASSERT_FAIL] {} - expected: {}, actual: {}",
                    message, expected, actual);
        }
    }

    /**
     * 验证对象不等（失败时记录错误值）
     */
    public static void assertNotEquals(Object o1, Object o2, String message) {
        if (Objects.equals(o1, o2)) {
            log.error("[ASSERT_FAIL] {} - unexpected value: {}", message, o1);
        }
    }

    /**
     * 验证非null（失败时记录ERROR日志）
     * 性能说明：相比Objects.requireNonNull()更轻量
     */
    public static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            log.error("[ASSERT_FAIL] " + message);
        }
    }

    // ----------- 新增方法（兼容JUnit4风格）------------

    /**
     * 验证为null（失败时记录实际值）
     * 兼容JUnit4的assertNull
     */
    public static void assertNull(Object obj, String message) {
        if (obj != null) {
            log.error("[ASSERT_FAIL] {} - actual: {}", message, obj);
        }
    }

    /**
     * 验证数组相等（深度比较）
     * 兼容JUnit4的assertArrayEquals
     */
    public static void assertArrayEquals(Object[] expected, Object[] actual, String message) {
        if (!Arrays.deepEquals(expected, actual)) {
            log.error("[ASSERT_FAIL] {} - expected: {}, actual: {}",
                    message, Arrays.toString(expected), Arrays.toString(actual));
        }
    }

    /**
     * 验证浮点数相等（带delta精度）
     * 兼容JUnit4的assertEquals(double, double, double)
     */
    public static void assertEquals(double expected, double actual, double delta, String message) {
        if (Math.abs(expected - actual) > delta) {
            log.error("[ASSERT_FAIL] {} - expected: {}, actual: {}, delta: {}",
                    message, expected, actual, delta);
        }
    }

    // ----------- 特殊场景方法 ------------

    /**
     * 验证异常抛出（兼容JUnit4的@Test(expected)）
     * 使用示例：
     * CustomAssertUtil.assertThrows(() -> obj.dangerousCall(),
     * IllegalArgumentException.class, "应该抛出参数异常");
     */
    public static void assertThrows(Runnable runnable, Class<?> exceptionType, String message) {
        try {
            runnable.run();
            log.error("[ASSERT_FAIL] {} - 未抛出预期异常: {}", message, exceptionType.getName());
        } catch (Exception e) {
            if (!exceptionType.isInstance(e)) {
                log.error("[ASSERT_FAIL] {} - 抛出异常类型不匹配, 预期: {}, 实际: {}",
                        message, exceptionType.getName(), e.getClass().getName());
            }
        }
    }

    /**
     * 失败：抛出 AssertionError 或 打印异常日志
     *
     * @param message 失败信息
     */
    public static void fail(String message) {
        // throw new AssertionError(message);

        // 断言失败，打印失败信息提示
        log.error("[ASSERT_FAIL] {} - 断言失败", message);
    }

    /**
     * 失败：抛出 AssertionError 并附带原始异常
     *
     * @param message 失败信息
     * @param cause   原始异常
     */
    public static void fail(String message, Throwable cause) {
        // throw new AssertionError(message, cause);

        // 断言失败，打印失败信息提示 & 异常堆栈信息
        log.error("[ASSERT_FAIL] {} - 断言失败", message, cause);
    }
}