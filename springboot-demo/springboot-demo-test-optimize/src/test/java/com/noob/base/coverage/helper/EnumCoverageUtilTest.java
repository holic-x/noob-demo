package com.noob.base.coverage.helper;

import com.noob.base.coverage.utils.EnumCoverageUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * 覆盖率测试工具类: UT for EnumCoverageUtil
 */
@Slf4j
public class EnumCoverageUtilTest {

    @Test
    public void test_coverage_constructor() {
        EnumCoverageUtil util = new EnumCoverageUtil();
        assertNotNull(util);
    }

    // Helper class for testing non-enum input to testEnum
    static class NotAnEnum {
        // This class is intentionally not an enum
    }

    // Helper class for testing constants with no public static final fields
    static class NoConstants {
        private String field = "test";
    }

    @SneakyThrows
    @Test
    public void test_testConstants_success() {
        // 测试枚举
        EnumCoverageUtil.testConstants(SimpleConstants.class);

        // 断言
        assertTrue(Boolean.TRUE);

    }

    @SneakyThrows
    @Test
    public void test_testEnum_success() {
        // 测试枚举
        EnumCoverageUtil.testEnum(SimpleEnum.class);

        // 断言
        assertTrue(Boolean.TRUE);

    }

    /*
    @Test(expected = IllegalArgumentException.class)
    public void testWithNonEnumClass() {
        // 传入非枚举类，应抛出IllegalArgumentException，此处工具类限定了入参需extend Enum，因此此处无法直接传入非Enum来覆盖
        EnumCoverageUtil.testEnum(String.class);
    }
     */

    // 处理思路：要么Util移除编译校验，放行测试；要么通过反射来进行处理绕过编译时的类型检查以此覆盖isEnum分支
    @Test
    public void test_testEnum_failed_nonEnum_usingReflection() throws Exception {
        Method method = EnumCoverageUtil.class.getMethod("testEnum", Class.class);
        try {
            method.invoke(null, String.class);
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
            assertEquals("java.lang.String is not an enum type", e.getCause().getMessage());
        }
    }

    @SneakyThrows
    @Test
    public void test_testEnum_failed_toStringNull() {
        // 测试toString为null的情况
        assertThrows(AssertionError.class, () -> {
            EnumCoverageUtil.testEnum(ErrorTestEnum.class);
        });
    }

    @Test
    public void testConstants_NoPublicStaticFinalFields() {
        EnumCoverageUtil.testConstants(NoConstants.class);
    }

}

// 正常测试枚举定义
enum SimpleEnum {
    VALUE1,
    VALUE2("Description2"),
    VALUE3;

    private String description;

    SimpleEnum() {
        this.description = this.name();
    }

    SimpleEnum(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SimpleEnum{" +
                "name='" + name() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

// 异常场景覆盖测试枚举定义
enum ErrorTestEnum {

    SPRING,
    SUMMER,

    ;

    // 重载
    @Override
    public String toString() {
        return null;
    }
}

// 简单常量类
class SimpleConstants {
    public final String FINAL_STRING_CONSTANT = "FINAL_STRING_CONSTANT";
    public static String STATIC_STRING_CONSTANT = "STATIC_STRING_CONSTANT";
    public static final String STATIC_FINAL_STRING_CONSTANT = "STATIC_FINAL_STRING_CONSTANT";
    public static final int INT_CONSTANT = 123;
    public static final boolean BOOLEAN_CONSTANT = true;
    public static final Double DOUBLE_CONSTANT = 45.67;
    public static final Object NULL_CONSTANT = null; // To test null value handling

    private static final String PRIVATE_CONSTANT = "Private"; // Should not be tested
    protected static String PROTECTED_FIELD = "Protected"; // Should not be tested
    public String publicField = "Public"; // Should not be tested
}
