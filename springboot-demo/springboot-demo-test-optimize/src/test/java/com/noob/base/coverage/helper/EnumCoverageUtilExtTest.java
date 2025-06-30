package com.noob.base.coverage.helper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.fail;

public class EnumCoverageUtilExtTest {

    // JUnit 4 中用于测试异常的规则
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // --- testEnum 方法的测试用例 ---

    // 1. 正常枚举：用于测试 testEnum 成功执行的路径
    enum NormalEnum {
        VALUE_A,
        VALUE_B;
    }

    // 2. 模拟 toString() 返回 null 的枚举：用于测试 testEnum 中的 AssertionError 分支
    enum BuggyToStringEnum {
        ITEM_X,
        ITEM_Y;

        @Override
        public String toString() {
            return null; // 故意返回 null，以触发 EnumCoverageUtil 中的 AssertionError
        }
    }

    // 3. 非枚举类：用于测试 testEnum 中的 IllegalArgumentException 分支
    static class NotAnEnumClass {
        // 这是一个普通的类，不是枚举
    }

    /**
     * 测试 testEnum 方法在正常枚举情况下的行为。
     * 预期：不抛出任何异常。
     */
    @Test
    public void testEnum_Success() {
        try {
            EnumCoverageUtil.testEnum(NormalEnum.class);
            // 如果没有抛出异常，则测试通过
        } catch (Exception e) {
            fail("正常枚举不应抛出任何异常: " + e.getMessage());
        }
    }

    /**
     * 测试 testEnum 方法在传入非枚举类时的行为。
     * 预期：抛出 IllegalArgumentException。
     */
//
//    @Test
//    public void testEnum_IllegalArgumentException_NotAnEnum() {
//        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage("is not an enum type");
//        EnumCoverageUtil.testEnum(NotAnEnumClass.class);
//    }

    /**
     * 测试 testEnum 方法在枚举的 toString() 返回 null 时的行为。
     * 预期：抛出 AssertionError。
     */
    @Test
    public void testEnum_AssertionError_ToStringReturnsNull() {
        thrown.expect(AssertionError.class);
        thrown.expectMessage("Enum toString() returned null for ITEM_X");
        EnumCoverageUtil.testEnum(BuggyToStringEnum.class);
    }

    // --- testConstants 方法的测试用例 ---

    // 1. 正常常量类：用于测试 testConstants 成功执行的路径
    static class NormalConstants {
        public static final String STRING_CONST = "Hello";
        public static final int INT_CONST = 123;
        public static final boolean BOOLEAN_CONST = true;
        // 非 public static final 字段，应被忽略
        private static final String PRIVATE_CONST = "Private";
        public String nonStaticField = "NonStatic";
        public static String nonFinalField = "NonFinal";
    }

    // 2. 模拟常量 toString() 返回 null 的类：用于测试 testConstants 中的 AssertionError 分支
    static class BuggyConstantToString {
        public static final Object NULL_TO_STRING_OBJ = new Object() {
            @Override
            public String toString() {
                return null; // 故意返回 null，以触发 EnumCoverageUtil 中的 AssertionError
            }
        };
    }

    // 3. 没有 public static final 常量的类：用于测试 testConstants 处理空常量列表的情况
    static class NoPublicStaticFinalConstants {
        public static String FIELD1 = "abc"; // 不是 final
        private static final int FIELD2 = 1; // 不是 public
        protected static final double FIELD3 = 2.0; // 不是 public
    }

    /**
     * 测试 testConstants 方法在正常常量类情况下的行为。
     * 预期：不抛出任何异常，并正确处理各种类型的常量。
     */
    @Test
    public void testConstants_Success() {
        try {
            EnumCoverageUtil.testConstants(NormalConstants.class);
            // 如果没有抛出异常，则测试通过
        } catch (Exception e) {
            fail("正常常量类不应抛出任何异常: " + e.getMessage());
        }
    }

    /**
     * 测试 testConstants 方法在常量值的 toString() 返回 null 时的行为。
     * 预期：抛出 AssertionError。
     */
    @Test
    public void testConstants_AssertionError_ConstantToStringReturnsNull() {
        thrown.expect(AssertionError.class);
        thrown.expectMessage("toString() returned null for field: NULL_TO_STRING_OBJ");
        EnumCoverageUtil.testConstants(BuggyConstantToString.class);
    }

    /**
     * 测试 testConstants 方法在类中没有 public static final 常量时的行为。
     * 预期：不抛出任何异常，方法正常结束。
     */
    @Test
    public void testConstants_NoPublicStaticFinalConstants() {
        try {
            EnumCoverageUtil.testConstants(NoPublicStaticFinalConstants.class);
            EnumCoverageUtil.testConstants(Object.class);
            // 如果没有抛出异常，则测试通过
        } catch (Exception e) {
            fail("没有 public static final 常量的类不应抛出任何异常: " + e.getMessage());
        }
    }
}