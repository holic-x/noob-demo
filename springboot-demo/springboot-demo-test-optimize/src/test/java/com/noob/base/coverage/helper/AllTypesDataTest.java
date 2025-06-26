package com.noob.base.coverage.helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 针对 AllTypesData 的全面单元测试，旨在覆盖由 Lombok @Data 生成的所有方法
 */
public class AllTypesDataTest {

    private AllTypesData data1;
    private AllTypesData data2;

    /**
     * 创建一个包含所有字段非默认值的实例，用于测试。
     *
     * @return 一个已填充的 AllTypesData 实例。
     */
    private AllTypesData createPopulatedInstance() {
        AllTypesData data = new AllTypesData();
        data.setPBoolean(true);
        data.setPByte((byte) 1);
        data.setPShort((short) 2);
        data.setPInt(3);
        data.setPLong(4L);
        data.setPFloat(5.5f);
        data.setPDouble(6.6d);
        data.setPChar('A');
        data.setWBoolean(Boolean.TRUE);
        data.setWByte((byte) 7);
        data.setWShort((short) 8);
        data.setWInteger(9);
        data.setWLong(10L);
        data.setWFloat(11.11f);
        data.setWDouble(12.12d);
        data.setWChar('B');
        data.setString("string");
        data.setObject("object");
        return data;
    }

    @Before
    public void setUp() {
        // 准备两个内容完全相同的对象，用于对称性测试
        data1 = createPopulatedInstance();
        data2 = createPopulatedInstance();
    }

    @Test
    public void testNoArgsConstructorAndGettersSetters() {
        // 测试由 @Data 提供的无参构造函数和所有 getter/setter
        AllTypesData data = new AllTypesData();
        assertFalse(data.isPBoolean()); // 默认值
        assertEquals(0, data.getPInt()); // 默认值
        assertNull(data.getWInteger()); // 默认值
        assertNull(data.getString()); // 默认值

        // 使用 setters 填充数据
        data.setPBoolean(true);
        data.setPInt(100);
        data.setWInteger(200);
        data.setString("test");

        // 使用 getters 验证
        assertTrue(data.isPBoolean());
        assertEquals(100, data.getPInt());
        assertEquals(Integer.valueOf(200), data.getWInteger());
        assertEquals("test", data.getString());
    }

    @Test
    public void testToString() {
        String toStringResult = data1.toString();
        // 验证 toString() 包含了关键字段
        assertTrue(toStringResult.contains("pBoolean=true"));
        assertTrue(toStringResult.contains("pLong=4"));
        assertTrue(toStringResult.contains("wDouble=12.12"));
        assertTrue(toStringResult.contains("string=string"));
    }

    @Test
    public void testEqualsAndHashCodeContract() {
        // 1. 自反性: x.equals(x)
        assertEquals("对象应等于其自身", data1, data1);

        // 2. 对称性: x.equals(y) == y.equals(x)
        assertEquals("内容相同的对象应相等", data1, data2);
        assertEquals("相等性必须是对称的", data2, data1);

        // 3. hashCode 协定
        assertEquals("内容相同的对象 hashCode 应相同", data1.hashCode(), data2.hashCode());

        // 4. 与 null 和不同类型的对象比较
        assertNotEquals("对象不应等于 null", data1, null);
        assertNotEquals("对象不应等于不同类型的对象", data1, new Object());
    }

    @Test
    public void testEquals_FieldByFieldInequality() {
        // 逐个字段测试不相等的情况，以触发 equals 方法中的每个 'return false' 分支
        AllTypesData variant;

        // --- 测试基本类型 ---
        variant = createPopulatedInstance();
        variant.setPBoolean(false);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPByte((byte) 99);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPShort((short) 99);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPInt(99);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPLong(99L);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPFloat(99.9f);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPDouble(99.9d);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setPChar('Z');
        assertNotEquals(data1, variant);

        // --- 测试包装类型和对象 ---
        variant = createPopulatedInstance();
        variant.setWBoolean(false);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWByte((byte) 98);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWShort((short) 98);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWInteger(98);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWLong(98L);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWFloat(98.8f);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWDouble(98.8d);
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setWChar('Y');
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setString("different");
        assertNotEquals(data1, variant);
        variant = createPopulatedInstance();
        variant.setObject("differentObject");
        assertNotEquals(data1, variant);
    }

    @Test
    public void testEqualsAndHashCode_WithNullFields() {
        // 这是确保高分支覆盖率最关键的测试
        AllTypesData allNull1 = new AllTypesData();
        AllTypesData allNull2 = new AllTypesData();
        assertEquals("所有字段都为默认/null值的两个对象应相等", allNull1, allNull2);
        assertEquals("所有字段都为默认/null值的两个对象的hashCode应相等", allNull1.hashCode(), allNull2.hashCode());

        // 逐个测试 nullable 字段的 null vs 非 null 情况
        AllTypesData variant;
        variant = createPopulatedInstance();
        variant.setWBoolean(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWByte(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWShort(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWInteger(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWLong(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWFloat(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWDouble(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setWChar(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setString(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);
        variant = createPopulatedInstance();
        variant.setObject(null);
        assertNotEquals(data1, variant);
        assertNotEquals(variant, data1);

        // 测试一个字段非 null vs 全 null 的情况
        AllTypesData allNullWithOneFieldSet = new AllTypesData();
        allNullWithOneFieldSet.setString("not null");
        assertNotEquals("全null对象不应等于有值的对象", allNull1, allNullWithOneFieldSet);
    }

    // --- canEqual 覆盖测试 ---

    /**
     * 一个专门用于测试 canEqual 协定的子类。
     * 它重写了 canEqual，因此父类的实例不能与该子类的实例相等。
     */
    private static final class SubclassOfAllTypes extends AllTypesData {
        private final String extraField = "sub";

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof SubclassOfAllTypes)) return false;
            SubclassOfAllTypes other = (SubclassOfAllTypes) o;
            if (!other.canEqual(this)) return false;
            if (!super.equals(o)) return false;
            return this.extraField.equals(other.extraField);
        }

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof SubclassOfAllTypes;
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 59 + (this.extraField == null ? 43 : this.extraField.hashCode());
        }
    }

    @Test
    public void testEquals_whenCanEqualIsFalse() {
        // 创建一个子类实例，其父类部分的字段与 data1 完全相同
        SubclassOfAllTypes subData = new SubclassOfAllTypes();
        // 手动设置所有字段以匹配 data1
        subData.setPBoolean(data1.isPBoolean());
        subData.setPByte(data1.getPByte());
        subData.setPShort(data1.getPShort());
        subData.setPInt(data1.getPInt());
        subData.setPLong(data1.getPLong());
        subData.setPFloat(data1.getPFloat());
        subData.setPDouble(data1.getPDouble());
        subData.setPChar(data1.getPChar());
        subData.setWBoolean(data1.getWBoolean());
        subData.setWByte(data1.getWByte());
        subData.setWShort(data1.getWShort());
        subData.setWInteger(data1.getWInteger());
        subData.setWLong(data1.getWLong());
        subData.setWFloat(data1.getWFloat());
        subData.setWDouble(data1.getWDouble());
        subData.setWChar(data1.getWChar());
        subData.setString(data1.getString());
        subData.setObject(data1.getObject());

        // 调用 data1.equals(subData)
        // 内部会执行到 subData.canEqual(data1)，因为 subData 是 SubclassOfAllTypes 类型，
        // 而 data1 是 AllTypesData 类型，所以 subData.canEqual(data1) 返回 false。
        // 这就触发了 data1 的 equals 方法中 `if (!other.canEqual(this)) return false;` 分支。
        assertNotEquals("父类对象不应等于 canEqual 返回 false 的子类对象", data1, subData);

        // 反向比较同样不相等，这是由 instanceof 检查决定的
        assertNotEquals("子类对象不应等于父类对象", subData, data1);
    }
}