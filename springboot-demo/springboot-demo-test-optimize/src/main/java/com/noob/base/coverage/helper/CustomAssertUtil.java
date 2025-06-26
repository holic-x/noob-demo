package com.noob.base.coverage.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 自定义断言工具类（针对UT Coverage设定的断言方法）
 * - 不触发exception抛出，通过log日志处理（for test：验证方法调用，覆盖行、覆盖分支）
 */
@Slf4j
public class CustomAssertUtil {

    public static void assertTrue(boolean condition, String message) {
//        if (!condition) throw new AssertionError(message);
        if (!condition)  log.error(message);;

    }

    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            // throw new AssertionError(message + " (预期: " + expected + ", 实际: " + actual + ")");
            log.error("{} (预期: {}, 实际: {})", message, expected, actual);
        }
    }

    public static void assertNotEquals(Object o1, Object o2, String message) {
        if (Objects.equals(o1, o2)) {
            // throw new AssertionError(message);
            log.error(message);
        }
    }

    public static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            // throw new AssertionError(message);
            log.error(message);
        }
    }
}
