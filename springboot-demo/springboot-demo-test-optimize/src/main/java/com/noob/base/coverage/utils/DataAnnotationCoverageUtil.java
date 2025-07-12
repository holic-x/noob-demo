package com.noob.base.coverage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static com.noob.base.coverage.utils.CustomAssertUtil.*;

/**
 * todo
 * 覆盖@Data注解的测试工具类（JDK 1.8兼容）
 * 功能覆盖：
 * 1. 所有字段的getter/setter（含继承链）
 * 2. equals/hashCode合约（含callSuper配置）
 * 3. toString内容（含callSuper配置）
 * 4. final/static/transient字段特殊处理
 * 5. 构造器兼容性（无参/全参/@Builder等）
 */
public class DataAnnotationCoverageUtil {

    private static final Logger log = LoggerFactory.getLogger(DataAnnotationCoverageUtil.class);


    // JDK 8兼容的默认值映射
    private static final Map<Class<?>, Object> DEFAULT_VALUES;

    static {
        Map<Class<?>, Object> map = new HashMap<>();
        // 基本类型
        map.put(boolean.class, false);
        map.put(byte.class, (byte) 1);
        map.put(short.class, (short) 1);
        map.put(int.class, 1);
        map.put(long.class, 1L);
        map.put(float.class, 1.0f);
        map.put(double.class, 1.0);
        map.put(char.class, 'a');
        // 包装类型
        map.put(Boolean.class, Boolean.FALSE);
        map.put(Byte.class, (byte) 1);
        map.put(Short.class, (short) 1);
        map.put(Integer.class, 1);
        map.put(Long.class, 1L);
        map.put(Float.class, 1.0f);
        map.put(Double.class, 1.0);
        map.put(Character.class, 'a');
        // 常用引用类型
        map.put(String.class, "test");
        DEFAULT_VALUES = Collections.unmodifiableMap(map);
    }

    /**
     * 测试@Data注解生成的所有方法
     */
    public static <T> void testDataClass(Class<T> clazz) {
        try {
            log.info("Testing @Data class: {}", clazz.getSimpleName());

            // 实例化测试对象
            T instance1 = createInstance(clazz);
            T instance2 = createInstance(clazz);
            T diffInstance = createDifferentInstance(clazz);

            // 分层测试
            testGetters(clazz, instance1);
            testSetters(clazz, instance1);
            testEqualsAndHashCode(clazz, instance1, instance2, diffInstance);
            testToString(clazz, instance1);

            log.info("✅ All @Data tests passed for {}", clazz.getSimpleName());
        } catch (Exception e) {
            throw new RuntimeException("Test failed for " + clazz.getName(), e);
        }
    }

    // ------------------------- 核心测试逻辑 -------------------------

    private static <T> void testGetters(Class<T> clazz, T instance) throws Exception {
        for (Field field : getAllTestableFields(clazz)) {
            Optional<Method> getter = findGetter(clazz, field);
            if (getter.isPresent()) {
                Object value = getter.get().invoke(instance);
                log.debug("Getter验证: {}.{}() = {}", clazz.getSimpleName(), getter.get().getName(), value);
            } else if (!field.isSynthetic()) {
                throw new AssertionError("Missing getter for field: " + field.getName());
            }
        }
    }

    private static <T> void testSetters(Class<T> clazz, T instance) throws Exception {
        for (Field field : getNonFinalFields(clazz)) {  // 只获取非final字段
            Optional<Method> setter = findSetter(clazz, field);
            if (!setter.isPresent()) {
                throw new AssertionError("非final字段缺少setter: " + field.getName());
            }
            // ...测试逻辑...
        }
    }

    private static List<Field> getNonFinalFields(Class<?> clazz) {
        return getAllTestableFields(clazz).stream()
                .filter(f -> !Modifier.isFinal(f.getModifiers()))
                .collect(Collectors.toList());
    }

    private static <T> void testEqualsAndHashCode(Class<T> clazz, T o1, T o2, T diffObj) throws Exception {
        // 验证equals合约

        Object reflexiveReference = o1;
        assertTrue(o1.equals(reflexiveReference), "Equals违反自反性"); // 创建一个新引用指向同一个对象，以避免Sonar警告“相同的表达式在二元运算符的两边”
        // assertTrue(o1.equals(o1), "Equals违反自反性");

        assertTrue(o1.equals(o2) && o2.equals(o1), "Equals违反对称性");

        assertFalse(o1 == null, "Equals违反非空性");

        // 验证hashCode合约
        assertEquals(o1.hashCode(), o2.hashCode(), "相同对象hashCode不一致");

        // 验证差异对象
        assertFalse(o1.equals(diffObj), "Equals无法区分不同对象");
    }

    private static <T> void testToString(Class<T> clazz, T instance) throws Exception {
        String str = instance.toString();
        assertNotNull(str, "toString返回null");
        assertFalse(str.isEmpty(), "toString返回空字符串");

        // 验证字段内容
        for (Field field : getAllTestableFields(clazz)) {
            // fix 短路效应导致路径cover统计不完整，无法完全cover（拆分if分支校验 分别处理不同的场景）
            /*
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                continue;
            }
             */
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }

            // sonar scanner error:Call "Optional#isPresent()" before accessing the value.
            // Object value = findGetter(clazz, field).get().invoke(instance);
            // assertTrue(str.contains(String.valueOf(value)), "toString未包含字段: " + field.getName());

            // fix sonar warning 安全地获取getter并调用
            Optional<Method> getterOptional = findGetter(clazz, field);
            if (!getterOptional.isPresent()) {
                // 如果一个字段应该被测试，那么它必须有一个getter方法
                throw new AssertionError("找不到字段 " + field.getName() + " 的getter方法，无法验证toString内容");
//                throw new RuntimeException("找不到字段 " + field.getName() + " 的getter方法，无法验证toString内容");

                // 此处也可设定如果字段getter不存在则跳过
                // continue;
            }

            Object value = getterOptional.get().invoke(instance);
            assertTrue(str.contains(String.valueOf(value)), "toString未包含字段: " + field.getName());

        }
    }

    // ------------------------- 工具方法 -------------------------

    private static List<Field> getAllTestableFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Field field : current.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    fields.add(field);
                }
            }
            current = current.getSuperclass(); // 关键：遍历父类
        }
        return fields;
    }

    private static <T> T createInstance(Class<T> clazz) throws Exception {
        // 优先尝试全参构造器（处理final字段）
        Constructor<T> fullArgCtor = findFullArgsConstructor(clazz);
        if (fullArgCtor != null) {
            fullArgCtor.setAccessible(true); // 突破private限制
            Object[] params = generateParameters(fullArgCtor.getParameterTypes());
            return fullArgCtor.newInstance(params);
        }

        // 次尝试无参构造器
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("请添加@NoArgsConstructor或全参构造器", e);
        }
    }

    private static <T> Constructor<T> findFullArgsConstructor(Class<T> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<T> result = null;
        int maxParams = -1;
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > maxParams) {
                maxParams = constructor.getParameterCount();
                @SuppressWarnings("unchecked")
                Constructor<T> castConstructor = (Constructor<T>) constructor;
                result = castConstructor;
            }
        }
        return result;
    }

    private static Object[] generateParameters(Class<?>[] paramTypes) {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = DEFAULT_VALUES.getOrDefault(paramTypes[i], null);
        }
        return params;
    }

    private static <T> T createDifferentInstance(Class<T> clazz) throws Exception {
        T instance = createInstance(clazz);
        List<Field> fields = getAllTestableFields(clazz);
        if (!fields.isEmpty()) {
            Field fieldToModify = fields.get(0);
            if (!Modifier.isFinal(fieldToModify.getModifiers())) {
                fieldToModify.setAccessible(true);
                Object originalValue = fieldToModify.get(instance);
                Object newValue = createDifferentValue(originalValue, fieldToModify.getType());
                fieldToModify.set(instance, newValue);
            }
        }
        return instance;
    }

    protected static Object createDifferentValue(Object original, Class<?> type) {
        if (type == boolean.class || type == Boolean.class) return !(Boolean) original;
        if (type == byte.class || type == Byte.class) return ((Byte) original + 1);
        if (type == short.class || type == Short.class) return ((Short) original + 1);
        if (type == int.class || type == Integer.class) return ((Integer) original + 1);
        if (type == long.class || type == Long.class) return ((Long) original + 1);
        if (type == float.class || type == Float.class) return ((Float) original + 1);
        if (type == double.class || type == Double.class) return ((Double) original + 1);
        if (type == char.class || type == Character.class) return ((Character) original + 1);
        if (type == String.class) return original + "_modified";
        return original;
    }

    private static Optional<Method> findGetter(Class<?> clazz, Field field) {
        String getterName = "get" + capitalize(field.getName());
        if (field.getType() == boolean.class) {
            String booleanGetterName = "is" + capitalize(field.getName());
            try {
                return Optional.of(clazz.getMethod(booleanGetterName));
            } catch (NoSuchMethodException e) {
                // 继续尝试普通getter
            }
        }
        try {
            return Optional.of(clazz.getMethod(getterName));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    private static Optional<Method> findSetter(Class<?> clazz, Field field) {
        String setterName = "set" + capitalize(field.getName());
        try {
            return Optional.of(clazz.getMethod(setterName, field.getType()));
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    protected static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}