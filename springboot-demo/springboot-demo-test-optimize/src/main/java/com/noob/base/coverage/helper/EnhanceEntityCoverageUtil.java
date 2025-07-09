package com.noob.base.coverage.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.noob.base.coverage.helper.CustomAssertUtil.*;
import static com.noob.base.coverage.helper.InvokeHelperUtil.*;


/**
 * todo
 * enhance 增强版实体UT覆盖工具类
 * 功能：完善的实体测试工具类（JDK 8兼容版），支持普通实体和Lombok所有常用注解
 * 1. 支持普通实体和Lombok注解（@Data, @Builder, @SuperBuilder等）
 * 2. 完整的类型安全字段赋值
 * 3. 构造器测试支持
 * 4. Getter/Setter测试
 * 5. equals/hashCode/toString测试
 * 6. Builder模式测试
 * 7. JDK 8 兼容
 */
public class EnhanceEntityCoverageUtil {

    private static final Logger log = LoggerFactory.getLogger(EnhanceEntityCoverageUtil.class);


    /**
     * 测试实体类的所有基本方法
     */
    public static <T> void testEntity(Class<T> clazz) {
        try {
            log.info("开始测试实体类: {}", clazz.getSimpleName());

            // 1. 测试构造器（// 实体默认都要进行构造器校验，如果是Builder模式虽然有默认的private构造器，为了统一实体校验此处注意Builder模式下也要显式加上@NoArgsConstructor、@AllArgsConstructor统一处理）
            testConstructors(clazz);


            // 2. 创建测试实例
            T instance1 = createInstance(clazz);
            T instance2 = createInstance(clazz);
            T diffInstance = createDifferentInstance(clazz);

            // 3. 测试基本方法
            testEqualsAndHashCode(instance1, instance2, diffInstance);
            testToString(instance1);
            testGettersAndSetters(instance1);

            // 4. 测试Builder模式
            testBuilderPattern(clazz);

            log.info("✅ 实体类 {} 测试通过", clazz.getSimpleName());
        } catch (Exception e) {
            log.error("❌ 实体类 {} 测试失败", clazz.getSimpleName(), e);
            throw new RuntimeException("测试失败: " + clazz.getName(), e);
        }
    }


    // ====================== 构造器测试 ======================

    private static <T> void testConstructors(Class<T> clazz) throws Exception {
        log.debug("测试构造器...");
        testNoArgsConstructor(clazz);
        testAllArgsConstructor(clazz);
    }

    private static <T> void testNoArgsConstructor(Class<T> clazz) throws Exception {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
            assertNotNull(instance, "无参构造器创建的对象为null");
            log.debug("无参构造器测试通过");
        } catch (NoSuchMethodException e) {
            log.debug("类 {} 没有无参构造器", clazz.getSimpleName());
        }
    }

    private static <T> void testAllArgsConstructor(Class<T> clazz) throws Exception {
        Constructor<T> constructor = findFullArgsConstructor(clazz);
        if (constructor != null) {
            Object[] params = InvokeHelperUtil.generateParameters(constructor.getParameterTypes());
            constructor.setAccessible(true);
            T instance = constructor.newInstance(params);

            // 验证字段赋值
            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                String paramName = parameters[i].getName();
                Field field = findFieldByName(clazz, paramName);
                if (field != null) {
                    Object fieldValue = getFieldValue(field, instance);
                    Object paramValue = params[i];
                    if (!Objects.equals(fieldValue, paramValue)) {
                        log.warn("构造器参数 {} 可能未正确赋值 (字段值: {}, 参数值: {})", paramName, fieldValue, paramValue);
                    }
                }
            }
            log.debug("全参构造器测试通过");
        }
    }

    // ====================== 核心测试逻辑 ======================
    /*
    private static <T> void testEqualsAndHashCode(T obj1, T obj2, T diffObj) throws IllegalAccessException {
        // equals 测试
        assertTrue(obj1.equals(obj1), "equals违反自反性");
        assertTrue(obj1.equals(obj2) && obj2.equals(obj1), "equals违反对称性");

        // assertFalse(obj1.equals(null), "equals违反非空性");
        // fix sonar Remove this call to "equals"; comparisons against null always return false; consider using '== null' to check for nullity.
        assertFalse(obj1 == null, "equals违反非空性");

        assertFalse(obj1.equals(diffObj), "equals无法区分不同对象");

        // hashCode 测试
        assertEquals(obj1.hashCode(), obj2.hashCode(), "相同对象hashCode不一致");
        if (obj1.hashCode() == diffObj.hashCode()) {
            log.warn("不同对象返回了相同的hashCode值，可能影响哈希表性能");
        }
    }
     */
    public static <T> void testEqualsAndHashCode(T obj1, T obj2, T obj3) {
        // 测试非空对象
        assertNotNull(obj1, "测试对象1不应为null");
        assertNotNull(obj2, "测试对象2不应为null");
        assertNotNull(obj3, "测试对象3不应为null");

        // 自反性
        assertEquals(obj1, obj1, "自反性测试失败");

        // 对称性
        assertEquals(obj1, obj2, "对称性测试失败(1)");
        assertEquals(obj2, obj1, "对称性测试失败(2)");

        // 传递性
        assertEquals(obj2, obj1, "传递性测试失败(1)");
        assertEquals(obj1, obj3, "传递性测试失败(2)");
        assertNotEquals(obj2, obj3, "传递性测试失败(3)");

        // 非null性
        assertNotEquals(obj1, null, "非null性测试失败");

        // hashCode一致性
        assertEquals(obj1.hashCode(), obj2.hashCode(), "hashCode一致性测试失败");
        assertNotEquals(obj1.hashCode(), obj3.hashCode(), "hashCode差异性测试失败");

        // 测试与不同类比较
        assertNotEquals(obj1, "随机字符串", "不同类型比较测试失败");
    }

    private static <T> void testToString(T obj) throws IllegalAccessException {
        String str = obj.toString();
        assertNotNull(str, "toString返回null");
        assertFalse(str.isEmpty(), "toString返回空字符串");

        // 检查是否包含类名和字段值
        boolean hasClass = str.contains(obj.getClass().getSimpleName());
        boolean hasFieldValue = false;

        for (Field field : getAllFields(obj.getClass())) {
            Object value = getFieldValue(field, obj);
            if (str.contains(String.valueOf(value))) {
                hasFieldValue = true;
                break;
            }
        }

        if (!hasClass) log.warn("toString结果中不包含类名");
        if (!hasFieldValue) log.warn("toString结果中不包含字段值");
    }

    private static <T> void testGettersAndSetters(T obj) throws Exception {
        for (Field field : getAllFields(obj.getClass())) {
            Object originalValue = getFieldValue(field, obj);

            // Getter测试
            Optional<Method> getter = findGetter(obj.getClass(), field);
            if (getter.isPresent()) {
                Object getterValue = getter.get().invoke(obj);
                assertEquals(originalValue, getterValue, "Getter返回值与字段值不匹配: " + field.getName());
            } else {
                log.warn("字段 {} 没有对应的Getter方法", field.getName());
            }

            // Setter测试（仅当字段不是final时）
            if (!Modifier.isFinal(field.getModifiers())) {
                Optional<Method> setter = findSetter(obj.getClass(), field);
                if (setter.isPresent()) {
                    Object newValue = generateDifferentValue(originalValue, field.getType());
                    setter.get().invoke(obj, newValue);
                    assertEquals(newValue, getFieldValue(field, obj), "Setter设置值失败: " + field.getName());
                    // 恢复原始值
                    setFieldValue(field, obj, originalValue);
                } else {
                    log.warn("字段 {} 没有对应的Setter方法", field.getName());
                }
            }
        }
    }

    // 增强的检测方法
    private static boolean hasBuilder(Class<?> clazz) {
        // 1. 检查标准builder()方法
        boolean hasBuilderMethod = Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.getName().equals("builder")
                        && m.getParameterCount() == 0);

        // 2. 检查Builder类（适用于@SuperBuilder）
        boolean hasBuilderClass = Arrays.stream(clazz.getDeclaredClasses())
                .anyMatch(c -> c.getSimpleName().contains("Builder"));

        return hasBuilderMethod || hasBuilderClass;
    }

    private static <T> void testBuilderPattern(Class<T> clazz) throws Exception {
        // @Builder 注解只支持源码级别，编译后运行不会保留，因此得到的class类文件必然不存在这个注解，通过这个方法无法覆盖场景
        // if (clazz.isAnnotationPresent(Builder.class) || clazz.isAnnotationPresent(SuperBuilder.class)) {

        // 非Builder模式
        if (!hasBuilder(clazz)) {
            log.warn("类 {} 未检测到Builder支持", clazz.getSimpleName());
            return;
        }

        // Builder模式 验证
        log.debug("测试Builder模式...");

        // 1. 创建Builder实例
        Object builder = invokeBuilderMethod(clazz);

        // 2. 为所有字段设置测试值
        for (Field field : getAllFields(clazz)) {
            try {
                Optional<Method> setter = findBuilderSetter(builder.getClass(), field);
                if (setter.isPresent()) {
                    Object testValue = generateBuilderTestValue(field.getType());
                    setter.get().invoke(builder, testValue);
                }
            } catch (Exception e) {
                log.warn("设置Builder字段 {} 失败: {}", field.getName(), e.getMessage());
            }
        }

        // 3. 构建实例并验证
        T instance = buildAndVerify(builder, clazz);
        assertNotNull(instance, "Builder构建的对象为null");

        // 4. 验证字段值
        verifyBuilderFieldValues(instance, clazz);
    }

    private static Object generateBuilderTestValue(Class<?> fieldType) {
        // 特殊处理父类字段
        if (fieldType == String.class) return "test";
        if (fieldType == boolean.class || fieldType == Boolean.class) return true;
        if (fieldType.isPrimitive() || Number.class.isAssignableFrom(fieldType)) return 1;
        return null; // 其他类型暂时返回null
    }

    private static <T> void verifyBuilderFieldValues(T instance, Class<T> clazz)
            throws IllegalAccessException {

        for (Field field : getAllFields(clazz)) {
            field.setAccessible(true);
            Object value = field.get(instance);
            if (value == null && field.getType().isPrimitive()) {
                log.warn("基本类型字段 {} 值为null", field.getName());
            }
        }
    }

    // ====================== 核心工具方法 ======================
    // 参考 InvokeHelperUtil


    // ====================== 反射辅助方法 ======================
    // 参考 InvokeHelperUtil


    // ====================== 断言方法 ======================
    // 参考 CustomAssertUtil

}

