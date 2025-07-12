package com.noob.base.coverage.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

/**
 * UT for EqualsHashCodeFullCoverageUtil
 * 补充方法覆盖验证
 */
public class EqualsHashCodeFullCoverageUtilExtTest {

    //================================================================================
    // Test cases for getDefaultValue
    //================================================================================
    @Test
    public void test_getDefaultValue() {

        // 创建一个任意类型的工具类用于覆盖protected方法
        EqualsHashCodeFullCoverageUtil<Object> util = new EqualsHashCodeFullCoverageUtil<>(Object::new);

        // Test primitive types and their wrappers
        Assert.assertEquals(0, util.getDefaultValue(int.class));
        Assert.assertEquals(0, util.getDefaultValue(Integer.class));
        Assert.assertEquals(0L, util.getDefaultValue(long.class));
        Assert.assertEquals(0L, util.getDefaultValue(Long.class));
        Assert.assertEquals(0.0, util.getDefaultValue(double.class));
        Assert.assertEquals(0.0, util.getDefaultValue(Double.class));
        Assert.assertEquals(0.0f, util.getDefaultValue(float.class));
        Assert.assertEquals(0.0f, util.getDefaultValue(Float.class));
        Assert.assertEquals(false, util.getDefaultValue(boolean.class));
        Assert.assertEquals(false, util.getDefaultValue(Boolean.class));
        Assert.assertEquals('\0', util.getDefaultValue(char.class));
        Assert.assertEquals('\0', util.getDefaultValue(Character.class));
        Assert.assertEquals((byte) 0, util.getDefaultValue(byte.class));
        Assert.assertEquals((byte) 0, util.getDefaultValue(Byte.class));
        Assert.assertEquals((short) 0, util.getDefaultValue(short.class));
        Assert.assertEquals((short) 0, util.getDefaultValue(Short.class));

        // Test String
        Assert.assertEquals("", util.getDefaultValue(String.class));

        // Test Enum
        Assert.assertEquals(UnEmptyEnum.FIRST, util.getDefaultValue(UnEmptyEnum.class));
        Assert.assertNull(util.getDefaultValue(EmptyEnum.class));

        // Test null case
        Assert.assertNull(util.getDefaultValue(Object.class));

    }

    @Test
    public void test_cover() {
        // 创建一个任意类型的工具类用于覆盖protected方法
        EqualsHashCodeFullCoverageUtil<Object> util = new EqualsHashCodeFullCoverageUtil<>(Object::new);
        Assert.assertNull(util.getDefaultValue(EmptyEnum.class));
    }


    //================================================================================
    // Test cases for isFieldIncludedInHashCode
    //================================================================================
    @Test
    public void test_isFieldIncludedInHashCode() {
        EqualsHashCodeFullCoverageUtil<BaseEntity> util1 = new EqualsHashCodeFullCoverageUtil<>(BaseEntity::new);
        assertTrue(util1.isFieldIncludedInHashCode("")); // 覆盖null
        assertTrue(util1.isFieldIncludedInHashCode("str")); // 覆盖包括的场景

        EqualsHashCodeFullCoverageUtil<BaseEntity> util2 = new EqualsHashCodeFullCoverageUtil<>(
                BaseEntity::new,
                Arrays.asList("str"),
                new ArrayList<>()
        );
        assertFalse(util2.isFieldIncludedInHashCode("")); // 覆盖null
        assertTrue(util2.isFieldIncludedInHashCode("str")); // 覆盖包括的场景
    }

    //================================================================================
    // Test cases for getDefaultValue
    //================================================================================
    @Test
    public void test_getModifiedValue() throws Exception {
        EqualsHashCodeFullCoverageUtil<Object> util = new EqualsHashCodeFullCoverageUtil<>(Object::new);

        // Test collections

        // List
        Assert.assertEquals(Collections.singletonList("modified"), util.getModifiedValue(null, List.class)); // null
        Assert.assertTrue(((List<?>) util.getModifiedValue(new ArrayList<>(), List.class)).contains("modified")); // empty
        Assert.assertTrue(((List<?>) util.getModifiedValue(Arrays.asList("testList"), List.class)).size() == 2); // non-empty

        // Set
        Set<String> originalSet = new HashSet<>(Arrays.asList("testSet"));
        util.getModifiedValue(null, Set.class); // null
        util.getModifiedValue(new HashSet<>(), Set.class);// empty
        Assert.assertTrue(((Set<?>) util.getModifiedValue(originalSet, Set.class)).size() == 2); // non-empty

        // Map
        // Assert.assertTrue(((Map<?, ?>) util.getModifiedValue(new HashMap<>(), Map.class)).containsKey("modified_key"));
        Map<String, String> originalMap = new HashMap<String, String>() {
            {
                put("testKey", "testValue");
            }
        };
        util.getModifiedValue(null, Map.class); // null
        util.getModifiedValue(new HashMap<>(), Map.class);// empty
        Assert.assertTrue(((Map<?, ?>) util.getModifiedValue(originalMap, Map.class)).size() == 2); // non-empty

        // Test arrays
        Assert.assertEquals(1, Array.getLength(util.getModifiedValue(null, String[].class)));
        String[] strArray = {"test"};
        Assert.assertEquals(2, Array.getLength(util.getModifiedValue(strArray, String[].class)));

        // Test primitive types and wrappers
        Assert.assertEquals(1, util.getModifiedValue(null, int.class));
        Assert.assertEquals(2, util.getModifiedValue(1, int.class));
        Assert.assertEquals(1L, util.getModifiedValue(null, long.class));
        Assert.assertEquals(2L, util.getModifiedValue(1L, long.class));
        Assert.assertEquals(1.0, util.getModifiedValue(null, double.class));
        Assert.assertEquals(2.0, util.getModifiedValue(1.0, double.class));
        Assert.assertEquals(1.0f, util.getModifiedValue(null, float.class));
        Assert.assertEquals(2.0f, util.getModifiedValue(1.0f, float.class));
        Assert.assertEquals(false, util.getModifiedValue(null, boolean.class));
        Assert.assertEquals(true, util.getModifiedValue(false, boolean.class));
        Assert.assertEquals((byte) 0, util.getModifiedValue(null, byte.class));
        Assert.assertEquals((byte) 1, util.getModifiedValue((byte) 0, byte.class));
        Assert.assertEquals((short) 0, util.getModifiedValue(null, short.class));
        Assert.assertEquals((short) 1, util.getModifiedValue((short) 0, short.class));
        Assert.assertEquals('\0', util.getModifiedValue(null, char.class));
        Assert.assertEquals('b', util.getModifiedValue('a', char.class));
        Assert.assertEquals('2', util.getModifiedValue('1', char.class));

        // Test wrapper types
        Assert.assertEquals(Integer.valueOf(1), util.getModifiedValue(null, Integer.class));
        Assert.assertEquals(Integer.valueOf(2), util.getModifiedValue(Integer.valueOf(1), Integer.class));
        Assert.assertEquals(Long.valueOf(1L), util.getModifiedValue(null, Long.class));
        Assert.assertEquals(Long.valueOf(2L), util.getModifiedValue(Long.valueOf(1L), Long.class));
        Assert.assertEquals(Double.valueOf(1.0), util.getModifiedValue(null, Double.class));
        Assert.assertEquals(Double.valueOf(2.0), util.getModifiedValue(Double.valueOf(1.0), Double.class));
        Assert.assertEquals(Float.valueOf(1.0f), util.getModifiedValue(null, Float.class));
        Assert.assertEquals(Float.valueOf(2.0f), util.getModifiedValue(Float.valueOf(1.0f), Float.class));
        Assert.assertEquals(Boolean.TRUE, util.getModifiedValue(null, Boolean.class));
        Assert.assertEquals(Boolean.TRUE, util.getModifiedValue(Boolean.FALSE, Boolean.class));
        Assert.assertEquals(Boolean.FALSE, util.getModifiedValue(Boolean.TRUE, Boolean.class));
        Assert.assertEquals(Byte.valueOf((byte) 0), util.getModifiedValue(null, Byte.class));
        Assert.assertEquals(Byte.valueOf((byte) 1), util.getModifiedValue(Byte.valueOf((byte) 0), Byte.class));
        Assert.assertEquals(Short.valueOf((short) 0), util.getModifiedValue(null, Short.class));
        Assert.assertEquals(Short.valueOf((short) 1), util.getModifiedValue(Short.valueOf((short) 0), Short.class));
        Assert.assertEquals(Character.valueOf('\0'), util.getModifiedValue(null, Character.class));
        Assert.assertEquals(Character.valueOf('b'), util.getModifiedValue(Character.valueOf('a'), Character.class));

        // Character 类型，但是指定的源数据为Number
        // Assert.assertEquals(2, util.getModifiedValue(1, Character.class));
        util.getModifiedValue((Number) 1, Character.class);
        util.getModifiedValue((Number) 65, Character.class); // 处理数字类型（如Integer 65对应'A'）

        // Character 类型，但是指定的源数据为String
        util.getModifiedValue(null, Character.class);
        util.getModifiedValue("h", Character.class);
        // util.getModifiedValue(new String(""), Character.class);

        // Character 类型，但是指定的源数据为其他類型
        assertThrows(IllegalArgumentException.class, () -> {
            util.getModifiedValue(new BaseEntity(), Character.class);
        });

        // Test String and Enum
        Assert.assertEquals("modified", util.getModifiedValue(null, String.class));
        Assert.assertEquals("test_modified", util.getModifiedValue("test", String.class));
        Assert.assertEquals(UnEmptyEnum.SECOND, util.getModifiedValue(UnEmptyEnum.FIRST, UnEmptyEnum.class));

        // Test Date and time types
        Assert.assertNotNull(util.getModifiedValue(null, java.util.Date.class));
        Assert.assertNotNull(util.getModifiedValue(null, java.sql.Date.class));
        Assert.assertNotNull(util.getModifiedValue(null, java.util.Calendar.class));
        Assert.assertNotNull(util.getModifiedValue(null, java.time.LocalDate.class));
        Assert.assertNotNull(util.getModifiedValue(null, java.time.LocalDateTime.class));
        Assert.assertNotNull(util.getModifiedValue(null, java.time.LocalTime.class));

        // Test other types
        Assert.assertNotNull(util.getModifiedValue(null, BigDecimal.class));
        Assert.assertNotNull(util.getModifiedValue(null, UUID.class));

        // 其他未兼容的类型（例如自定义实体等）
        Assert.assertNotNull(util.getModifiedValue(null, BaseEntity.class));// 普通实体
        Assert.assertNotNull(util.getModifiedValue(null, PrivateData.class)); // 构造函数私有化，不允许访问，会触发异常的场景

        // Test edge cases
        try {
            util.getModifiedValue("invalid", boolean.class);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        util.getModifiedValue("invalid", char.class);
//        try {
//            util.getModifiedValue("invalid", char.class);
//            Assert.fail("Expected IllegalArgumentException");
//        } catch (IllegalArgumentException e) {
//            // Expected
//        }

    }
}


// 创建辅助测试实体
enum UnEmptyEnum {
    FIRST, SECOND
}

enum EmptyEnum {
}


class BaseEntity {
    private String str;
}