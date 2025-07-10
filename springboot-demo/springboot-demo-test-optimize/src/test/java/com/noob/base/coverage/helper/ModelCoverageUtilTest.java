package com.noob.base.coverage.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ModelCoverageUtilTest {

    // ========== 测试用例 ==========

    // 测试场景 - 基础POJO测试
    @Test
    public void test_testPojo_basicCoverage() {
        ModelCoverageUtil.testPojo(TestPojo.class);
    }

    // 测试场景 - 链式Setter的POJO
    @Test
    public void test_testPojo_chainSettersCoverage() {
        ModelCoverageUtil.testPojo(TestPojoWithChain.class);
    }

    // 测试场景 - 无默认构造方法的POJO
    @Test
    public void test_testPojo_noDefaultConstructorCoverage() {
        ModelCoverageUtil.testPojo(TestPojoNoDefaultConstructor.class);
    }

    // 测试场景 - 包含静态/final字段的POJO
    @Test
    public void test_testPojo_staticFinalFieldsCoverage() {
        ModelCoverageUtil.testPojo(TestPojoWithStaticFinal.class);
    }

    // 测试场景 - 自定义equals/hashCode的POJO
    @Test
    public void test_testPojo_customEqualsHashCodeCoverage() {
        ModelCoverageUtil.testPojo(TestPojoWithEqualsHashCode.class);
    }

    // 测试场景 - 基本类型默认值
    @Test
    public void test_getDefaultValue_primitiveTypes() {
        assertEquals(1, ModelCoverageUtil.getDefaultValue(int.class));
        assertEquals(true, ModelCoverageUtil.getDefaultValue(boolean.class));
        assertEquals((byte) 1, ModelCoverageUtil.getDefaultValue(byte.class));
        assertEquals(1.0d, ModelCoverageUtil.getDefaultValue(double.class));
        assertEquals(1.0f, ModelCoverageUtil.getDefaultValue(float.class));
        assertEquals(1L, ModelCoverageUtil.getDefaultValue(long.class));
        assertEquals((short) 1, ModelCoverageUtil.getDefaultValue(short.class));
        assertEquals('a', ModelCoverageUtil.getDefaultValue(char.class));
    }

    // 测试场景 - 常见对象类型默认值
    @Test
    public void test_getDefaultValue_commonObjectTypes() {
        assertEquals("test字符串", ModelCoverageUtil.getDefaultValue(String.class));
        assertEquals(Integer.valueOf(1), ModelCoverageUtil.getDefaultValue(Integer.class));
        assertEquals(Boolean.TRUE, ModelCoverageUtil.getDefaultValue(Boolean.class));
        assertEquals(new BigDecimal("1.23"), ModelCoverageUtil.getDefaultValue(BigDecimal.class));
        assertNotNull(ModelCoverageUtil.getDefaultValue(Date.class));
        assertNotNull(ModelCoverageUtil.getDefaultValue(LocalDate.class));
    }

    // 测试场景 - 集合类型默认值
    @Test
    public void test_getDefaultValue_collectionTypes() {
        assertNotNull(ModelCoverageUtil.getDefaultValue(List.class));
        assertNotNull(ModelCoverageUtil.getDefaultValue(Set.class));
        assertNotNull(ModelCoverageUtil.getDefaultValue(Map.class));
    }

    // 测试场景 - 枚举类型默认值
    @Test
    public void test_getDefaultValue_enumType() {
        assertEquals(TestEnum.VALUE1, ModelCoverageUtil.getDefaultValue(TestEnum.class));
    }

    // 测试场景 - 数组类型默认值
    @Test
    public void test_getDefaultValue_arrayType() {
        Object doubleArray = ModelCoverageUtil.getDefaultValue(double[].class);
        assertNotNull(doubleArray);
        assertEquals(1, ((double[]) doubleArray).length);

        Object pojoArray = ModelCoverageUtil.getDefaultValue(TestPojo[].class);
        assertNotNull(pojoArray);
        assertEquals(1, ((TestPojo[]) pojoArray).length);

        Object strArray = ModelCoverageUtil.getDefaultValue(String[].class);
        assertNotNull(strArray);
        assertEquals(1, ((String[]) strArray).length);
    }


    // 测试场景 - 自定义对象类型默认值
    @Test
    public void test_getDefaultValue_customObjectType() {
        assertNotNull(ModelCoverageUtil.getDefaultValue(TestPojo.class));
    }

    // 测试场景 - 不支持的类型返回null
    @Test
    public void test_getDefaultValue_unsupportedTypeReturnsNull() {
        assertNull(ModelCoverageUtil.getDefaultValue(Class.class)); // 不支持的类型
    }

    // 测试场景 - 字段方法测试(getter/setter)
    @Test
    public void test_testFieldMethods_normalCase() throws Exception {
        TestPojo instance = new TestPojo();
        List<Method> methods = ModelCoverageUtil.getAllMethods(TestPojo.class);
        Field field = TestPojo.class.getDeclaredField("primitiveInt");

        ModelCoverageUtil.testFieldMethods(instance, methods, field, "primitiveInt");
    }

    // 测试场景 - Getter方法检测
    @Test
    public void test_isGetter_detectionCases() throws Exception {
        // 测试标准getter
        Method intGetter = TestPojo.class.getMethod("getPrimitiveInt");
        Field intField = TestPojo.class.getDeclaredField("primitiveInt");

        // 测试boolean类型的getXxx形式
        Method booleanGetter = TestPojo.class.getMethod("getBooleanField");
        Field booleanField = TestPojo.class.getDeclaredField("booleanField");

        // 测试boolean类型的isXxx形式
        Method isBooleanGetter = TestPojo.class.getMethod("isBooleanField");

        // 验证标准getter
        assertTrue(ModelCoverageUtil.isGetter(intGetter, "primitiveInt", intField.getType()));
        assertFalse(ModelCoverageUtil.isGetter(intGetter, "wrongName", intField.getType()));

        // 验证boolean getter (getXxx形式)
        assertTrue(ModelCoverageUtil.isGetter(booleanGetter, "booleanField", booleanField.getType()));

        // 验证boolean getter (isXxx形式)
        assertTrue(ModelCoverageUtil.isGetter(isBooleanGetter, "booleanField", booleanField.getType()));

        // 验证非getter方法
        Method setter = TestPojo.class.getMethod("setPrimitiveInt", int.class);
        assertFalse(ModelCoverageUtil.isGetter(setter, "primitiveInt", intField.getType()));
    }

    // 测试场景 - Setter方法检测
    @Test
    public void test_isSetter_detectionCases() throws Exception {
        Method setter = TestPojo.class.getMethod("setPrimitiveInt", int.class);
        Field field = TestPojo.class.getDeclaredField("primitiveInt");

        assertTrue(ModelCoverageUtil.isSetter(setter, "primitiveInt", field.getType()));
        assertFalse(ModelCoverageUtil.isSetter(setter, "wrongName", field.getType()));
    }

    // 测试场景 - 获取所有字段(包括父类)
    @Test
    public void test_getAllFields_includesSuperclassFields() {
        class Parent {
            private String parentField;
        }
        class Child extends Parent {
            private String childField;
        }

        List<Field> fields = ModelCoverageUtil.getAllFields(Child.class);
        assertTrue(fields.stream().anyMatch(f -> f.getName().equals("parentField")));
        assertTrue(fields.stream().anyMatch(f -> f.getName().equals("childField")));
    }

    // 测试场景 - 获取所有方法(包括父类)
    @Test
    public void test_getAllMethods_includesSuperclassMethods() {
        class Parent {
            public void parentMethod() {
            }
        }
        class Child extends Parent {
            public void childMethod() {
            }
        }

        List<Method> methods = ModelCoverageUtil.getAllMethods(Child.class);
        assertTrue(methods.stream().anyMatch(m -> m.getName().equals("parentMethod")));
        assertTrue(methods.stream().anyMatch(m -> m.getName().equals("childMethod")));
    }

    // 测试场景 - 使用无参构造方法创建实例
    @Test
    public void test_createInstance_noArgConstructorSuccess() throws Exception {
        Object instance = ModelCoverageUtil.createInstance(TestPojo.class);
        assertNotNull(instance);
        assertTrue(instance instanceof TestPojo);
    }

    // 测试场景 - 当无无参构造时使用带参构造创建实例
    @Test
    public void test_createInstance_argConstructorFallback() throws Exception {
        Object instance = ModelCoverageUtil.createInstance(TestPojoNoDefaultConstructor.class);
        assertNotNull(instance);
        assertTrue(instance instanceof TestPojoNoDefaultConstructor);
    }

    // 测试场景 - 测试toString方法
    @Test
    public void test_testToString_validOutput() throws Exception {
        TestPojo instance = new TestPojo();
        ModelCoverageUtil.testToString(instance);
    }

    // 测试场景 - 基础equals/hashCode测试
    @Test
    public void test_testEqualsAndHashCode_basicCoverage() throws Exception {
        TestPojoWithEqualsHashCode instance = new TestPojoWithEqualsHashCode();
        ModelCoverageUtil.testEqualsAndHashCode(instance, TestPojoWithEqualsHashCode.class);
    }

    // 测试场景 - 增强版equals/hashCode测试
    @Test
    public void test_testEqualsAndHashCodeEnhance_fullCoverage() throws Exception {
        TestPojoWithEqualsHashCode instance = new TestPojoWithEqualsHashCode();
        ModelCoverageUtil.testEqualsAndHashCodeEnhance(instance, TestPojoWithEqualsHashCode.class);
    }

    // 测试场景 - 构造方法测试
    @Test
    public void test_testAllConstructors_multipleConstructors() throws Exception {
        ModelCoverageUtil.testAllConstructors(TestPojo.class);
    }

    // 测试场景 - 跳过静态/final字段
    @Test
    public void test_testAllFields_skipsStaticFinalFields() throws Exception {
        TestPojoWithStaticFinal instance = new TestPojoWithStaticFinal();
        ModelCoverageUtil.testAllFields(instance, TestPojoWithStaticFinal.class);
        // 无断言 - 仅验证无异常
    }
}

// ============================== 测试数据类 ==============================
class TestPojo {
    private int primitiveInt;       // 基本类型字段
    private String stringField;     // 字符串字段
    private List<String> listField; // 集合字段
    private Date dateField;        // 日期字段
    private TestEnum enumField;     // 枚举字段
    private boolean booleanField; // boolean字段

    // 构造方法
    public TestPojo() {
    }  // 无参构造

    public TestPojo(int primitiveInt, String stringField) {  // 带参构造
        this.primitiveInt = primitiveInt;
        this.stringField = stringField;
    }

    // Getter/Setter方法
    public int getPrimitiveInt() {
        return primitiveInt;
    }

    public void setPrimitiveInt(int primitiveInt) {
        this.primitiveInt = primitiveInt;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public List<String> getListField() {
        return listField;
    }

    public void setListField(List<String> listField) {
        this.listField = listField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public TestEnum getEnumField() {
        return enumField;
    }

    public void setEnumField(TestEnum enumField) {
        this.enumField = enumField;
    }

    public boolean getBooleanField() {
        return booleanField;
    }

    // 也可以添加isXxx形式的getter
    public boolean isBooleanField() {
        return booleanField;
    }

    // toString方法
    @Override
    public String toString() {
        return "TestPojo{" +
                "primitiveInt=" + primitiveInt +
                ", stringField='" + stringField + '\'' +
                '}';
    }
}

// 链式调用POJO
class TestPojoWithChain {
    private String name;
    private int value;

    // 链式Setter方法
    public TestPojoWithChain setName(String name) {
        this.name = name;
        return this;
    }

    public TestPojoWithChain setValue(int value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}

// 无默认构造方法的POJO
class TestPojoNoDefaultConstructor {
    private final String requiredField;  // final字段

    public TestPojoNoDefaultConstructor(String requiredField) {
        this.requiredField = requiredField;
    }

    public String getRequiredField() {
        return requiredField;
    }
}

// 包含静态和final字段的POJO
class TestPojoWithStaticFinal {
    private static final String CONSTANT = "CONST";  // 静态常量
    private static int staticField;      // 静态字段
    private final String finalField = "final";  // final字段
}

// 自定义equals/hashCode的POJO
class TestPojoWithEqualsHashCode {
    private String key;
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPojoWithEqualsHashCode that = (TestPojoWithEqualsHashCode) o;
        return value == that.value && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}

// 测试用枚举
enum TestEnum {VALUE1, VALUE2}
