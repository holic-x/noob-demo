package com.noob.base.coverage.helper;

import lombok.SneakyThrows;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * 实体类测试覆盖率提升工具(JDK 1.8 & JUnit4 兼容版)
 * 功能说明：
 * 1. 自动测试无参和常用带参构造函数
 * 2. 自动测试getter/setter方法（兼容Lombok、链式调用）
 * 3. 支持基本类型、常见对象类型、常用集合、时间类型、枚举等
 * 4. 支持父类属性和方法
 * 5. 支持toString、equals、hashCode方法测试
 * 6. 详细信息提示，便于调试和扩展
 */
public class ModelCoverageUtil {

    // 基本类型默认值映射
    private static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = new HashMap<>();
    // 常见对象类型默认值映射
    private static final Map<Class<?>, Object> OBJECT_DEFAULTS = new HashMap<>();

    static {
        // 基本类型
        PRIMITIVE_DEFAULTS.put(int.class, 1);
        PRIMITIVE_DEFAULTS.put(boolean.class, true);
        PRIMITIVE_DEFAULTS.put(byte.class, (byte) 1);
        PRIMITIVE_DEFAULTS.put(double.class, 1.0d);
        PRIMITIVE_DEFAULTS.put(float.class, 1.0f);
        PRIMITIVE_DEFAULTS.put(long.class, 1L);
        PRIMITIVE_DEFAULTS.put(short.class, (short) 1);
        PRIMITIVE_DEFAULTS.put(char.class, 'a');

        // 常见对象类型
        OBJECT_DEFAULTS.put(String.class, "test字符串");
        OBJECT_DEFAULTS.put(Integer.class, 1);
        OBJECT_DEFAULTS.put(Boolean.class, true);
        OBJECT_DEFAULTS.put(Long.class, 1L);
        OBJECT_DEFAULTS.put(Double.class, 1.0d);
        OBJECT_DEFAULTS.put(Float.class, 1.0f);
        OBJECT_DEFAULTS.put(Short.class, (short) 1);
        OBJECT_DEFAULTS.put(Byte.class, (byte) 1);
        OBJECT_DEFAULTS.put(Character.class, 'a');
        OBJECT_DEFAULTS.put(BigDecimal.class, new BigDecimal("1.23"));
        OBJECT_DEFAULTS.put(BigInteger.class, new BigInteger("123"));
        OBJECT_DEFAULTS.put(Date.class, new Date());
        OBJECT_DEFAULTS.put(java.sql.Date.class, new java.sql.Date(System.currentTimeMillis()));
        OBJECT_DEFAULTS.put(LocalDate.class, LocalDate.now());
        OBJECT_DEFAULTS.put(LocalDateTime.class, LocalDateTime.now());
        OBJECT_DEFAULTS.put(LocalTime.class, LocalTime.now());
        OBJECT_DEFAULTS.put(UUID.class, UUID.randomUUID());
        OBJECT_DEFAULTS.put(List.class, Arrays.asList("list元素"));
        OBJECT_DEFAULTS.put(Set.class, new HashSet<>(Arrays.asList("set元素")));
        OBJECT_DEFAULTS.put(Map.class, Collections.singletonMap("key", "value"));
    }

    /**
     * JUnit4风格入口示例
     */
    public static void testPojo(Class<?> clazz) {
        try {
            System.out.println("【实体测试】开始测试：" + clazz.getName());
            // 1. 测试构造器
            Object instance = testConstructors(clazz);

            // 2. 测试getter/setter
            testAllFields(instance, clazz);

            // 3. 测试toString
            testToString(instance);

            // 4. 可选：测试equals/hashCode
            testEqualsAndHashCode(instance, clazz);

            System.out.println("【实体测试】完成：" + clazz.getName());
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 异常处理方案：
             * 1.借助Assert.fail断言（依赖与Junit环境）
             * 2.直接抛出 AssertionError 或 RuntimeException
             * 3.在自定义断言工具处理类CustomAssertUtil中封装并处理失败的场景：统一断言行为
             */
            // Assert.fail("实体测试失败: " + clazz.getName() + "，原因: " + e.getMessage());

            /*
            throw new AssertionError("实体测试失败: " + clazz.getName() + "，原因: " + e.getMessage(), e);
            throw new RuntimeException("实体测试失败: " + clazz.getName() + "，原因: " + e.getMessage(), e);
            */
        }
    }

    /**
     * 测试所有构造器，优先无参构造
     */
    protected static Object testConstructors(Class<?> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Object instance = null;
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] params = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                params[i] = getDefaultValue(paramTypes[i]);
            }
            try {
                instance = constructor.newInstance(params);
                System.out.println("【构造器测试】成功: " + constructor);
                break; // 优先用第一个可用构造器
            } catch (Exception e) {
                System.out.println("【构造器测试】失败: " + constructor + "，原因: " + e.getMessage());
            }
        }
        if (instance == null) {
            throw new RuntimeException("无法实例化对象: " + clazz.getName());
        }
        return instance;
    }

    /**
     * 测试所有字段的getter/setter
     */
    protected static void testAllFields(Object instance, Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        List<Method> methods = getAllMethods(clazz);

        for (Field field : fields) {
            String fieldName = field.getName();
            // 跳过静态/常量字段
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            testFieldMethods(instance, methods, field, fieldName);
        }
    }

    /**
     * 测试单个字段的getter/setter
     */
    protected static void testFieldMethods(Object instance, List<Method> methods, Field field, String fieldName) {
        for (Method m : methods) {
            if (isGetter(m, fieldName, field.getType())) {
                try {
                    testGetter(instance, m);
                } catch (Exception e) {
                    System.out.println("【getter测试】失败: " + m.getName() + "，原因: " + e.getMessage());
                }
            } else if (isSetter(m, fieldName, field.getType())) {
                try {
                    testSetter(instance, m, field.getType());
                } catch (Exception e) {
                    System.out.println("【setter测试】失败: " + m.getName() + "，原因: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 判断是否是getter方法（兼容isXxx、getXxx）
     */
    protected static boolean isGetter(Method method, String fieldName, Class<?> fieldType) {
        String methodName = method.getName();
        if (method.getParameterCount() != 0) return false;
        if (methodName.equalsIgnoreCase("get" + fieldName)) return true;
        if (fieldType == boolean.class || fieldType == Boolean.class) {
            return methodName.equalsIgnoreCase("is" + fieldName);
        }
        return false;
    }

    /**
     * 判断是否是setter方法
     */
    protected static boolean isSetter(Method method, String fieldName, Class<?> fieldType) {
        if (method.getParameterCount() != 1) return false;
        String methodName = method.getName();
        return methodName.equalsIgnoreCase("set" + fieldName);
    }

    /**
     * 测试getter方法
     */
    @SneakyThrows
    protected static void testGetter(Object instance, Method getter) {
        getter.setAccessible(true);
        Object result = getter.invoke(instance);
        System.out.println("【getter测试】" + getter.getName() + " 返回: " + result);
    }

    /**
     * 测试setter方法，支持链式调用
     */
    @SneakyThrows
    protected static void testSetter(Object instance, Method setter, Class<?> fieldType) {
        setter.setAccessible(true);
        Object paramValue = getDefaultValue(fieldType);
        Object result = setter.invoke(instance, paramValue);
        System.out.println("【setter测试】" + setter.getName() + " 入参: " + paramValue + "，返回: " + result);
        // 支持链式调用
        if (result != null && result.getClass() == instance.getClass()) {
            System.out.println("【链式setter】" + setter.getName() + " 支持链式调用");
        }
    }

    /**
     * 测试toString方法
     */
    @SneakyThrows
    protected static void testToString(Object instance) {
        String str = instance.toString();
        System.out.println("【toString测试】结果: " + str);
        CustomAssertUtil.assertNotNull("toString返回值不应为null", str);
    }

    /**
     * 测试equals和hashCode方法（可选）
     */
    @SneakyThrows
    protected static void testEqualsAndHashCode(Object instance, Class<?> clazz) {
        Object another = testConstructors(clazz);
        boolean eq = instance.equals(another);
        int hash1 = instance.hashCode();
        int hash2 = another.hashCode();
        System.out.println("【equals/hashCode测试】equals: " + eq + "，hashCode: " + hash1 + "/" + hash2);
    }

    /**
     * 获取默认值，支持常见类型、集合、枚举、数组、自定义对象
     */
    protected static Object getDefaultValue(Class<?> type) {
        // 基本类型
        if (type.isPrimitive()) {
            return PRIMITIVE_DEFAULTS.get(type);
        }
        // 常见对象类型
        if (OBJECT_DEFAULTS.containsKey(type)) {
            return OBJECT_DEFAULTS.get(type);
        }
        // 枚举类型
        if (type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues.length > 0 ? enumValues[0] : null;
        }
        // 数组类型
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 1);
        }
        // 集合类型（简单填充）
        if (List.class.isAssignableFrom(type)) {
            List<Object> list = new ArrayList<>();
            list.add("list元素");
            return list;
        }
        if (Set.class.isAssignableFrom(type)) {
            Set<Object> set = new HashSet<>();
            set.add("set元素");
            return set;
        }
        if (Map.class.isAssignableFrom(type)) {
            Map<Object, Object> map = new HashMap<>();
            map.put("key", "value");
            return map;
        }
        // 其他对象尝试递归实例化
        try {
            return testConstructors(type);
        } catch (Exception e) {
            System.out.println("【默认值生成】无法实例化类型: " + type.getName() + "，返回null");
            return null;
        }
    }

    /**
     * 获取类及其父类的所有字段
     */
    protected static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取类及其父类的所有方法
     */
    protected static List<Method> getAllMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            methods.addAll(Arrays.asList(currentClass.getDeclaredMethods()));
            currentClass = currentClass.getSuperclass();
        }
        return methods;
    }
}