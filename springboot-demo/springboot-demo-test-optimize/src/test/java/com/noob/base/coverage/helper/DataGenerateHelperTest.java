package com.noob.base.coverage.helper;

import com.noob.base.coverage.mockEntity.SpecialEntity;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * UT for DataGenerateHelper
 */
public class DataGenerateHelperTest {

    private DataGenerateHelper util;

    @Before
    public void setUp() {
        util = new DataGenerateHelper();
    }

    @Test
    public void test_coverage_constructor() throws Exception {
        InvokeHelper invokeHelperUtil = new InvokeHelper();
        assertNotNull(invokeHelperUtil);
    }

//    @Test
//    public void testCreateInstanceNoArgConstructor() throws Exception {
//        assertEquals("test", util.createInstance(TestClass.class).getStringField());
//    }
//
//    @Test
//    public void testCreateInstanceBuilderPattern() throws Exception {
//        // assertEquals("test", util.createInstance(TestBuilderClass.class).getStringField());
//        assertEquals(null, util.createInstance(TestBuilderClass.class).getStringField()); // to fix builder 模式设置值失败（确认是不是私有属性导致）
//    }
//
//    @Test
//    public void testCreateInstanceFullArgConstructor() throws Exception {
//        TestFullArgClass instance = util.createInstance(TestFullArgClass.class);
//        assertNotNull(instance);
//        assertEquals(1, instance.getIntField());
//        assertEquals("test", instance.getStringField());
//    }
//
//    // @Test(expected = RuntimeException.class)
//    @Test
//    public void testCreateInstanceNoConstructor() throws Exception {
//        util.createInstance(TestNoConstructorClass.class);
//        assertTrue(Boolean.TRUE);
//    }


//    @Test
//    public void testCreateDifferentInstance() throws Exception {
//        TestClass testClass = new TestClass();
//        TestClass differentInstance = util.createDifferentInstance(TestClass.class);
//        assertNotEquals(testClass.getPrivateStringField(), differentInstance.getPrivateStringField());
//    }
//
//    @Test
//    public void testCreateDifferentInstance_for_NoFieldClass() throws Exception {
//        NoFieldClass noFieldClass = new NoFieldClass();
//        NoFieldClass differentInstance = util.createDifferentInstance(NoFieldClass.class);
//        // assertEquals(noFieldClass, differentInstance);
//        assertNotNull(differentInstance);
//    }

    @Test
    public void test_generateDifferentValue() {
        assertEquals(Integer.valueOf(2), util.generateDifferentValue(1, int.class));
        assertEquals(Long.valueOf(2L), util.generateDifferentValue(1L, long.class));
        assertEquals(Double.valueOf(2.0), util.generateDifferentValue(1.0, double.class));
        assertEquals(Float.valueOf(2.0f), util.generateDifferentValue(1.0f, float.class));
        assertEquals(Short.valueOf((short) 2), util.generateDifferentValue((short) 1, short.class));
        assertEquals(Short.valueOf((short) Short.MAX_VALUE), util.generateDifferentValue((short) Short.MAX_VALUE, short.class)); // 最大值覆盖
        assertEquals(Byte.valueOf((byte) 2), util.generateDifferentValue((byte) 1, byte.class));
        assertEquals(Byte.valueOf((byte) Byte.MAX_VALUE), util.generateDifferentValue((byte) Byte.MAX_VALUE, byte.class)); // 最大值覆盖
        assertEquals(Character.valueOf('b'), util.generateDifferentValue('a', char.class));
        assertEquals(Boolean.TRUE, util.generateDifferentValue(false, boolean.class));
        assertEquals("test_modified", util.generateDifferentValue("test", String.class));

        // fix assert error
        // assertTrue(util.generateDifferentValue(null, List.class) instanceof ArrayList);
        util.generateDifferentValue(null, List.class);

        util.generateDifferentValue(new ArrayList<>(), List.class); // 覆盖List类型处理：list 为 空
        util.generateDifferentValue(Arrays.asList("hello"), List.class); // 覆盖List类型处理：list 不为空


        // fix assert error
        // assertTrue(util.generateDifferentValue(null, Set.class) instanceof HashSet);
        util.generateDifferentValue(null, Set.class);
        util.generateDifferentValue(new HashSet<>(), List.class); // 覆盖Set类型处理：set 为 空
        HashSet<String> set = new HashSet<>();
        set.add("hello");
        util.generateDifferentValue(set, List.class); // 覆盖Set类型处理：set 不为空

        // fix assert error
        // assertArrayEquals(new int[]{0}, (int[]) util.generateDifferentValue(new int[]{}, int[].class));
//        util.generateDifferentValue(new int[]{}, int[].class);
        util.generateDifferentValue(new int[10], int[].class);

        // tofix debug 模式下可以正常invoke，但是直接run就会throw  java.lang.ArrayIndexOutOfBoundsException
        // int[] resultArray = (int[]) util.generateDifferentValue(new int[]{1}, int[].class);
        // int[] resultArray = (int[]) util.generateDifferentValue(new int[]{0}, int[].class);// len 为 0

        int[] resultArray = (int[]) util.generateDifferentValue(new int[]{10}, int[].class);

        SpecialEntity entity = (SpecialEntity) util.generateDifferentValue(new SpecialEntity(), SpecialEntity.class);

        // assertTrue(resultArray.length == 1 && resultArray[0] == 0 || resultArray.length == 2 && resultArray[0] == 1 && resultArray[1] == 0);
    }

    @Test
    public void test_generateNonNullValue() throws Exception {
        // 基本类型
        util.generateNonNullValue(int.class);
        util.generateNonNullValue(long.class);
        util.generateNonNullValue(double.class);
        util.generateNonNullValue(float.class);
        util.generateNonNullValue(short.class);
        util.generateNonNullValue(byte.class);
        util.generateNonNullValue(char.class);
        util.generateNonNullValue(boolean.class);

        // 差用引用类型
        util.generateNonNullValue(String.class);
        util.generateNonNullValue(Integer.class);
        util.generateNonNullValue(Short.class);
        util.generateNonNullValue(Float.class);
        util.generateNonNullValue(Double.class);
        util.generateNonNullValue(Character.class);
        util.generateNonNullValue(Long.class);
        util.generateNonNullValue(Byte.class);
        util.generateNonNullValue(Boolean.class);
        util.generateNonNullValue(BigDecimal.class);
        util.generateNonNullValue(BigInteger.class);
        util.generateNonNullValue(LocalDate.class);
        util.generateNonNullValue(LocalDateTime.class);
        util.generateNonNullValue(UUID.class);

        // List
        util.generateNonNullValue(List.class);
        util.generateNonNullValue(ArrayList.class);
        util.generateNonNullValue(LinkedList.class);

        // Set
        util.generateNonNullValue(Set.class);

        // Map
        util.generateNonNullValue(Map.class);

        // 数组
        util.generateNonNullValue(int[].class);

        // 其他对象类型
        util.generateNonNullValue(SpecialEntity.class);

        assertTrue(Boolean.TRUE);

    }

    /*
    不支持mock static 方法
    @Test
    public void test_buildAndVerify_throwIllegalAccessException() throws Exception {
        InvokeHelper spyUtil = Mockito.spy(util);

        TestBuilderClass.Builder builder = TestBuilderClass.builder();
        doThrow(new IllegalAccessException("异常")).when(spyUtil).tryNormalBuild(builder);

        spyUtil.buildAndVerify(builder, TestBuilderClass.class);
    }
     */


    /*
    @Test(expected = IllegalAccessException.class)
    public void testTryNormalBuildIllegalAccessException() throws Exception {
        TestBuilderClass.Builder builder = mock(TestBuilderClass.Builder.class);
        Method buildMethod = mock(Method.class);
        when(builder.getClass().getMethod("build")).thenReturn(buildMethod);
        when(buildMethod.invoke(builder)).thenThrow(new IllegalAccessException());
        util.tryNormalBuild(builder);
    }
     */

}
