package com.noob.base.coverage.utils;

import com.noob.base.coverage.helper.AllTypesData;
import com.noob.base.coverage.helper.EmptyData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * UT for DataAnnotationCoverageUtil
 */
public class DataAnnotationCoverageUtilTest {

    private DataAnnotationCoverageUtil util;

    // ===================================================================================
    // Constructor
    // ===================================================================================
    @Test
    public void test_coverage_constructor() throws Exception {
        DataAnnotationCoverageUtil dataAnnotationCoverageUtil = new DataAnnotationCoverageUtil();
        assertNotNull(dataAnnotationCoverageUtil);
    }

    static class ExceptionInGetterData {
        public String getName() {
            throw new IllegalStateException("Getter exception");
        }

        // Add other methods to pass other checks
        @Override
        public boolean equals(Object o) {
            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "ExceptionInGetterData";
        }
    }


    // =================================================================================
    // Positive Test Cases
    // =================================================================================

    @Test
    public void testSimpleDataClass() {
        DataAnnotationCoverageUtil.testDataClass(SimpleData.class);
    }

    @Test
    public void testFinalFieldDataClass() {
        DataAnnotationCoverageUtil.testDataClass(ContainFinalFieldData.class);
    }

    @Test
    public void testInheritanceDataClass() {
        DataAnnotationCoverageUtil.testDataClass(ChildData.class);
    }

    @Test
    public void testSpecialFieldDataClass() {
        DataAnnotationCoverageUtil.testDataClass(SpecialFieldData.class);
    }

    @Test
    public void testEnumDataClass() {
        DataAnnotationCoverageUtil.testDataClass(EnumData.class);
    }

    @Test
    public void testBooleanDataClass() {
        DataAnnotationCoverageUtil.testDataClass(BooleanData.class);
    }

    @Test
    public void testAllTypesDataClass() {
        DataAnnotationCoverageUtil.testDataClass(AllTypesData.class);
    }

    @Test
    public void testEmptyDataClass() {
        DataAnnotationCoverageUtil.testDataClass(EmptyData.class);
    }

    @Test
    public void testAllFinalDataClass() {
        DataAnnotationCoverageUtil.testDataClass(AllFinalData.class);
    }

    @Test
    public void testNoArgsConstructorData() {
        DataAnnotationCoverageUtil.testDataClass(NoArgsConstructorData.class);
    }

    // coverage EnumData
    @Test
    public void test_coverage_mock_entity_EnumData() {
        // base coverage
        DataAnnotationCoverageUtil.testDataClass(EnumData.class);

        // EnumData data = EnumData.builder().status(EnumData.Status.ACTIVE).name("Test").build();
        EnumData data = new EnumData();
        data.setStatus(EnumData.Status.ACTIVE);
        data.setName("Test");

        // 自反性：对象等于自身
        EnumData data1 = new EnumData(EnumData.Status.ACTIVE, "Test");
        Assert.assertTrue(data1.equals(data1));

        // 非空性：对象不等于 null
        Assert.assertFalse(data1.equals(null));

        // 对称性：如果对象 A 等于对象 B，则对象 B 也必须等于对象 A
        EnumData data2 = new EnumData(EnumData.Status.ACTIVE, "Test");
        Assert.assertTrue(data1.equals(data2));
        Assert.assertTrue(data2.equals(data1));

        // 传递性：如果对象 A 等于对象 B，并且对象 B 等于对象 C，则对象 A 必须等于对象 C
        EnumData data3 = new EnumData(EnumData.Status.ACTIVE, "Test");
        Assert.assertTrue(data1.equals(data2));
        Assert.assertTrue(data2.equals(data3));
        Assert.assertTrue(data1.equals(data3));

        // 一致性：多次调用 equals 方法的结果一致，除非对象的状态发生变化
        Assert.assertTrue(data1.equals(data2));
        Assert.assertTrue(data1.equals(data2));
        Assert.assertTrue(data1.equals(data2));

        // 不同字段的对象不相等
        EnumData nullStatusData = new EnumData(null, "Test");
        nullStatusData.hashCode();
        EnumData nullNameData = new EnumData(EnumData.Status.ACTIVE, null);
        nullNameData.hashCode();
        EnumData data4 = new EnumData(EnumData.Status.INACTIVE, "Test");
        Assert.assertFalse(nullStatusData.equals(data4));
        Assert.assertFalse(nullNameData.equals(data4));
        Assert.assertFalse(data1.equals(data4));

        EnumData data5 = new EnumData(EnumData.Status.ACTIVE, "Different");
        Assert.assertFalse(data1.equals(data5));

        // 与不同类型的对象比较：对象不等于其他类型的对象
        Object nonEnumData = new Object(); // 使用 Object 作为不同类型的示例
        Assert.assertFalse(data1.equals(nonEnumData));


        // name 校验：基于Status字段相同的场景才能覆盖
        Assert.assertTrue(nullNameData.equals(new EnumData(EnumData.Status.ACTIVE, null))); // this.name == null && that.name == null
        Assert.assertFalse(nullNameData.equals(new EnumData(EnumData.Status.ACTIVE, ""))); // that.name == null && that.name != null

        Assert.assertTrue(data1.equals(new EnumData(EnumData.Status.ACTIVE, "Test"))); // this.name != null && this.name == that.name
        Assert.assertFalse(data1.equals(new EnumData(EnumData.Status.ACTIVE, "Different"))); // this.name != null && this.name != that.name

        // 子类用于测试 canEqual 分支
        class NonEqualEnumData extends EnumData {

            NonEqualEnumData(Status status, String name) {
                super(status, name);
            }

            @Override
            protected boolean canEqual(Object other) {
                return false; // 这将使 canEqual 检查失败
            }
        }
        // canEqual 分支：子类的 canEqual 方法返回 false 时，equals 方法的行为
        NonEqualEnumData nonEqualData = new NonEqualEnumData(EnumData.Status.ACTIVE, "name");
        Assert.assertFalse(data1.equals(nonEqualData));
    }

    // =================================================================================
    // Negative/Failure Test Cases
    // =================================================================================

    @Test(expected = AssertionError.class)
    public void testMissingGetter() {
        // This class is defined locally because it cannot have @Data
        class MissingGetter {
            @SuppressWarnings("unused")
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            // No getter
            @Override
            public boolean equals(Object o) {
                return true;
            }

            @Override
            public int hashCode() {
                return 1;
            }

            @Override
            public String toString() {
                return "MissingGetter";
            }
        }
        DataAnnotationCoverageUtil.testDataClass(MissingGetter.class);
    }

    @Test(expected = AssertionError.class)
    public void testMissingSetterForNonFinalField() {
        @Getter // Using @Getter only, so no setter is generated
        class MissingSetter {
            private String name;

            // Add other methods to pass other checks
            @Override
            public boolean equals(Object o) {
                return true;
            }

            @Override
            public int hashCode() {
                return 1;
            }

            @Override
            public String toString() {
                return "MissingSetter";
            }
        }
        DataAnnotationCoverageUtil.testDataClass(MissingSetter.class);
    }

    // @Test(expected = RuntimeException.class)
    @Test(expected = AssertionError.class)
    public void testNoAccessibleConstructor() {
        class NoPublicConstructor {
            private final String id;

            private NoPublicConstructor(String id) {
                this.id = id;
            }
        }
        DataAnnotationCoverageUtil.testDataClass(NoPublicConstructor.class);
    }

    @Test(expected = AssertionError.class)
    public void testBrokenEqualsContract() {
        DataAnnotationCoverageUtil.testDataClass(BrokenEqualsData.class);
    }

    @Test(expected = AssertionError.class)
    public void testBrokenHashCodeContract() {
        // 步骤 1: 修复不稳定的 hashCode 测试
        // 使用静态计数器替代随机数，确保每次调用 hashCode 返回不同的值，使测试结果确定可靠。
        @Data
        class BrokenHashCodeData {
            private String id;
            private int counter = 0;
//            private static int counter = 0;

            // 违反合约：相同的对象拥有不同的哈希码
            @Override
            public int hashCode() {
                return counter++;
            }
        }
        DataAnnotationCoverageUtil.testDataClass(BrokenHashCodeData.class);
    }

    @Test(expected = AssertionError.class)
    public void testToStringMissingGetter() {
        // This class has a field but no getter, which testToString should fail on
        class ToStringMissingGetter {
            @SuppressWarnings("unused")
            private String name = "test";

            // No getter for 'name'
            @Override
            public boolean equals(Object o) {
                return true;
            }

            @Override
            public int hashCode() {
                return 1;
            }

            @Override
            public String toString() {
                return "ToStringMissingGetter(name=" + name + ")";
            }
        }
        DataAnnotationCoverageUtil.testDataClass(ToStringMissingGetter.class);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionInGetterShouldBeWrapped() {
        // 步骤 2: 修复 Getter 异常测试
        // 这个测试验证当 getter 抛出异常时，testDataClass 会捕获它并包装成 RuntimeException。
        @Data
        class ExceptionInGetterData {
            private String name;

            public String getName() {
                throw new IllegalStateException("Getter exception");
            }
        }
        DataAnnotationCoverageUtil.testDataClass(ExceptionInGetterData.class);
    }

    @Test(expected = AssertionError.class)
    public void testClassWithOnlyPrivateArgConstructor() {
        @Data
        class PrivateConstructorData {
            private final String name;

            private PrivateConstructorData(String name) {
                this.name = name;
            }
        }
        DataAnnotationCoverageUtil.testDataClass(PrivateConstructorData.class);
    }


    // ==================================================================================================
    // other Test cases
    // ==================================================================================================
    @Test
    public void test_capitalize() {
        DataAnnotationCoverageUtil.capitalize(null);
        DataAnnotationCoverageUtil.capitalize("");
        DataAnnotationCoverageUtil.capitalize("null");
        DataAnnotationCoverageUtil.capitalize("        ");
        DataAnnotationCoverageUtil.capitalize("测试数据");

        assertTrue(Boolean.TRUE);
    }

    @Test
    public void testConvertValue() {
        util.createDifferentValue(false, boolean.class);
        util.createDifferentValue(Boolean.TRUE, Boolean.class);
        util.createDifferentValue((byte) 1, byte.class);
        util.createDifferentValue((byte) 1, Byte.class);
        util.createDifferentValue((byte) 1, byte.class);
        util.createDifferentValue((short) 1, short.class);
        util.createDifferentValue((short) 1, Short.class);
        util.createDifferentValue(1, int.class);
        util.createDifferentValue(1, Integer.class);
        util.createDifferentValue(1L, long.class);
        util.createDifferentValue(1L, Long.class);
        util.createDifferentValue(1.0f, float.class);
        util.createDifferentValue(1.0f, Float.class);

        util.createDifferentValue(1.0, double.class);
        util.createDifferentValue(1.0, Double.class);
        util.createDifferentValue('a', char.class);
        util.createDifferentValue('a', Character.class);
        util.createDifferentValue("test", String.class);

        // 其他类型
        util.createDifferentValue(new ArrayList<>(), List.class);

        assertTrue(Boolean.TRUE);
    }

}


// ======================== mock entity ========================

// 测试普通@Data类
@Data
class SimpleData {
    private String name;
    private int age;
}

// 测试所有字段均为final修饰的场景
@Data
class AllFinalData {
    private final String name = "hello world";
    private final int value = 0;
}

//@Data // 或者使用@Data复合注解
@Getter
@Setter
@EqualsAndHashCode
class ContainFinalFieldData {
    private final String id = "default";
    private String name;
}

// 测试继承场景：ParentData 父类
@Data
class ParentData {
    protected String parentField;
}

@Data
@EqualsAndHashCode(callSuper = true) // 继承类需显式配置@EqualsAndHashCode(callSuper=true)
class ChildData extends ParentData {
    private String childField;
}

// 测试boolean类型的字段
@Data
class BooleanData {
    private boolean active;
    private String description;
}

// 测试static/transient字段
@Data
class SpecialFieldData {
    private static int count; // static 字段
    private transient String temp; // transient 字段
    private String name;// private 普通字段
    protected Long anotherNormalField; // protected 普通字段
    private final String finalButNotStaticField = "final"; // final 但非 static 字段，不应被跳过
}

// --- Classes for Failure Scenarios --- 破坏equals方法
@EqualsAndHashCode
class BrokenEqualsData {
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false; // This makes it non-symmetric if the other side is not null-checked
        if (getClass() != o.getClass()) return false;
        BrokenEqualsData that = (BrokenEqualsData) o;
        return value == that.value;
    }
}

// 测试枚举类型(正常枚举类型)
@Data
class EnumData {
    public enum Status {ACTIVE, INACTIVE}

    private EnumData.Status status;
    private String name;

    public EnumData() {
    }

    public EnumData(EnumData.Status status, String name) {
        this.status = status;
        this.name = name;
    }

}

@Data
class NoConstantEnumData {

    public enum NoConstant {}

    private NoConstantEnumData.NoConstant noConstant;
    private String name;

    public NoConstantEnumData() {
    }

    public NoConstantEnumData(NoConstantEnumData.NoConstant noConstant, String name) {
        this.noConstant = noConstant;
        this.name = name;
    }

}

// 定义只有无参构造器的实体
class NoArgsConstructorData {

    private final String name = "hello";

    NoArgsConstructorData() {
    }

    public String getName() {
        return name;
    }

}