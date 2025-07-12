package com.noob.base.coverage.utils;

import com.noob.base.coverage.helper.InvokeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.noob.base.coverage.utils.CustomAssertUtil.*;
import static com.noob.base.coverage.helper.InvokeHelper.*;

/**
 * 增强版实体UT覆盖工具类
 * <p>
 * 功能：完善的实体测试工具类（JDK 8兼容版），支持普通实体和Lombok所有常用注解
 * 1. 支持普通实体和Lombok注解（@Data, @Builder, @SuperBuilder等）
 * 2. 完整的类型安全字段赋值
 * 3. 构造器测试支持
 * 4. Getter/Setter测试
 * 5. equals/hashCode/toString测试
 * 6. Builder模式测试
 * 7. JDK 8 兼容
 * <p>
 * 增强点：
 * - 优化了Builder模式的检测逻辑
 * - 增强了equals/hashCode测试的完整性
 * - 补充了详细的代码注释
 * - 改进了日志输出信息
 * - 增加了对继承结构的支持
 */
public class EnhanceModelCoverageUtil {

    private static final Logger log = LoggerFactory.getLogger(EnhanceModelCoverageUtil.class);

    /**
     * 测试实体类的所有基本方法
     *
     * @param <T>   实体类型
     * @param clazz 要测试的实体类
     * @throws RuntimeException 如果测试失败
     */
    public static <T> void testEntity(Class<T> clazz) {
        try {
            log.info("开始测试实体类: {}", clazz.getSimpleName());

            // 1. 测试构造器（包括无参、全参、部分参数的构造器）
            // 实体默认都要进行构造器校验，如果是Builder模式虽然有默认的private构造器，为了统一实体校验此处注意Builder模式下也要显式加上@NoArgsConstructor、@AllArgsConstructor统一处理）
            System.out.println("------------------------------【构造器覆盖测试】start------------------------------");
            testAllConstructors(clazz);
            System.out.println("------------------------------【构造器覆盖测试】end------------------------------\n");

            // 2. 创建测试实例
            System.out.println("------------------------------【实例创建】start------------------------------");
            T instance = createInstance(clazz);  // 原始实例
            T sameInstance = instance;          // 真正相同的实例引用
            T equalInstance = createEqualInstance(clazz);  // 新创建但字段值相同的实例
            T diffInstance = createDifferentInstance(clazz); // 字段值不同的实例
            System.out.println("------------------------------【实例创建】end------------------------------\n");

            // 3. 测试基本方法
            System.out.println("------------------------------【基础方法覆盖测试】start------------------------------");
            testEqualsAndHashCode(instance, sameInstance, equalInstance, diffInstance); // equals & hashCode 方法覆盖
            testToString(instance); // toString 方法覆盖
            testGettersAndSetters(instance); // getter & setter 方法覆盖
            System.out.println("------------------------------【基础方法覆盖测试】end------------------------------\n");

            // 4. 测试Builder模式（如果支持）
            System.out.println("------------------------------【Builder模式覆盖测试】start------------------------------");
            testBuilderPattern(clazz);
            System.out.println("------------------------------【Builder模式覆盖测试】end------------------------------\n");

            // 5. 测试链式调用（如果支持）
            System.out.println("------------------------------【链式调用测试】start------------------------------");
            testMethodChaining(clazz);
            System.out.println("------------------------------【链式调用测试】end------------------------------\n");

            log.info("✅ 实体类 {} 测试通过", clazz.getSimpleName());

        } catch (Exception e) {
            log.error("❌ 实体类 {} 测试失败", clazz.getSimpleName(), e);
            throw new RuntimeException("测试失败: " + clazz.getName(), e); // 抛出异常：随后可通过特殊实体定义来定位覆盖异常的情况，调整兼容版本
        }
    }

    // ====================== 构造器测试 ======================

    /**
     * 验证构造器参数是否正确设置
     * @param constructor
     * @param instance
     * @param params
     * @throws IllegalAccessException
     */
    private static void validateConstructorParameters(Constructor<?> constructor, Object instance, Object[] params)
            throws IllegalAccessException {
        Parameter[] parameters = constructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            String paramName = parameters[i].getName();
            try {
                Field field = findFieldByName(instance.getClass(), paramName);
                if (field != null) {
                    Object fieldValue = getFieldValue(field, instance);
                    if (!Objects.deepEquals(fieldValue, params[i])) {
                        System.out.println("【构造器参数验证】参数 " + paramName +
                                " 设置不一致 (字段值: " + fieldValue + ", 参数值: " + params[i] + ")");
                    }
                }
            } catch (Exception e) {
                System.out.println("【构造器参数验证】无法验证参数 " + paramName + ": " + e.getMessage());
            }
        }
    }

    /**
     * 校验是否存在@SuperBuilder生成的构造器
     * @param paramTypes
     * @return
     */
    private static boolean isSuperBuilderConstructor(Class<?>[] paramTypes) {
        // @SuperBuilder 生成的构造器通常有一个参数，且类型名包含 "Builder"
        if (paramTypes.length == 1) {
            return paramTypes[0].getSimpleName().contains("Builder");
        }
        return false;
    }

    /**
     * 测试所有构造器（并行概念，无顺序要求）
     */
    protected static void testAllConstructors(Class<?> clazz) throws Exception {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("类没有声明任何构造器: " + clazz.getName());
        }

        for (Constructor<?> constructor : constructors) {
            try {
                constructor.setAccessible(true);
                Class<?>[] paramTypes = constructor.getParameterTypes();

                // 跳过 @SuperBuilder 生成的构建器参数构造器（兼容Lombok注解@SuperBuilder构造器的覆盖场景）
                if (isSuperBuilderConstructor(paramTypes)) {
                    System.out.println("【构造器测试】跳过 @SuperBuilder 构造器: " + constructor);
                    continue;
                }

                // 传统构造器覆盖
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = InvokeHelper.generateNonNullValue(paramTypes[i]);
                }
                Object instance = constructor.newInstance(params);

                // 验证构造器参数是否正确设置
                validateConstructorParameters(constructor, instance, params);

                System.out.println("【构造器测试】成功: " + constructor);
            } catch (Exception e) {
                System.out.println("【构造器测试】失败: " + constructor + "，原因: " + e.getMessage());
            }
        }
    }

    // ====================== 核心测试逻辑 ======================

    /**
     * 增强版的equals和hashCode测试
     *
     * @param instance      原始实例
     * @param sameInstance  相同的实例引用(instance == sameInstance)
     * @param equalInstance 相等但不相同的实例(instance.equals(equalInstance)为true)
     * @param diffInstance  不相等的实例
     */
    public static <T> void testEqualsAndHashCode(T instance, T sameInstance,
                                                 T equalInstance, T diffInstance) {
        // 1. 非空检查
        assertNotNull(instance, "测试实例不应为null");
        assertNotNull(equalInstance, "相等实例不应为null");
        assertNotNull(diffInstance, "不等实例不应为null");

        // 2. 自反性测试
        assertEquals(instance, sameInstance, "自反性测试失败");
        assertEquals(instance.hashCode(), sameInstance.hashCode(), "自反性hashCode测试失败");

        // 3. 对称性测试
        assertEquals(instance, equalInstance, "对称性测试失败(1)");
        assertEquals(equalInstance, instance, "对称性测试失败(2)");
        assertEquals(instance.hashCode(), equalInstance.hashCode(), "对称性hashCode测试失败");

        // 4. 不等性测试
        assertNotEquals(instance, diffInstance, "不等性测试失败");

        // 5. 非null测试
        assertNotEquals(instance, null, "非null性测试失败");

        // 6. 不同类型测试
        assertNotEquals(instance, "其他类型对象", "不同类型比较测试失败");
    }

    /**
     * 测试toString方法
     */
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

    /**
     * 测试getter和setter方法
     */
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

    // ====================== Builder模式测试 ======================

    /**
     * 检测类是否支持Builder模式
     */
    private static boolean hasBuilder(Class<?> clazz) {
        // 1. 检查标准builder()方法
        boolean hasBuilderMethod = Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.getName().equals("builder")
                        && m.getParameterCount() == 0
                        && (m.getReturnType().getName().contains("Builder") ||
                        m.getReturnType().isMemberClass()));

        // 2. 检查Builder类（适用于@SuperBuilder）
        boolean hasBuilderClass = Arrays.stream(clazz.getDeclaredClasses())
                .anyMatch(c -> c.getSimpleName().contains("Builder"));

        // 3. 检查toBuilder支持
        boolean hasToBuilder = Arrays.stream(clazz.getMethods())
                .anyMatch(m -> m.getName().equals("toBuilder")
                        && m.getParameterCount() == 0);

        return hasBuilderMethod || hasBuilderClass || hasToBuilder;
    }

    /**
     * 测试Builder模式
     */
    private static <T> void testBuilderPattern(Class<T> clazz) throws Exception {
        // @Builder 注解只支持源码级别，编译后运行不会保留，因此得到的class类文件必然不存在这个注解，通过这个方法无法覆盖场景
        // if (clazz.isAnnotationPresent(Builder.class) || clazz.isAnnotationPresent(SuperBuilder.class)) {}

        if (!hasBuilder(clazz)) {
            log.debug("类 {} 未检测到Builder支持", clazz.getSimpleName());
            return;
        }

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

    /**
     * 生成Builder测试值
     */
    private static Object generateBuilderTestValue(Class<?> fieldType) {
        // 特殊处理父类字段
        if (fieldType == String.class) return "test";
        if (fieldType == boolean.class || fieldType == Boolean.class) return true;
        if (fieldType.isPrimitive() || Number.class.isAssignableFrom(fieldType)) return 1;
        return null; // 其他类型暂时返回null
    }

    /**
     * 验证Builder创建的实例字段值
     */
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

    // ====================== 新增链式调用测试 ======================

    /**
     * 测试链式调用方法
     */
    private static <T> void testMethodChaining(Class<T> clazz) throws Exception {
        List<Method> chainableMethods = Arrays.stream(clazz.getMethods())
                .filter(m -> m.getReturnType().equals(clazz) &&
                        m.getParameterCount() > 0)
                .collect(Collectors.toList());

        if (chainableMethods.isEmpty()) {
            System.out.println("【链式调用】未检测到链式调用方法");
            return;
        }

        T instance = createInstance(clazz);
        for (Method method : chainableMethods) {
            try {
                Object[] params = Arrays.stream(method.getParameterTypes())
                        .map(InvokeHelper::generateNonNullValue)
                        .toArray();

                Object result = method.invoke(instance, params);

                if (result != instance) {
                    System.out.println("【链式调用】方法 " + method.getName() +
                            " 未返回this，返回类型: " + result.getClass().getSimpleName());
                } else {
                    System.out.println("【链式调用】方法 " + method.getName() + " 测试通过");
                }
            } catch (Exception e) {
                System.out.println("【链式调用】方法 " + method.getName() + " 调用失败: " + e.getMessage());
            }
        }
    }

    // ====================== 辅助方法 ======================
    // 以下方法需要从InvokeHelperUtil等工具类中引入:
    // - findFullArgsConstructor
    // - findFieldByName
    // - getFieldValue/setFieldValue
    // - getAllFields
    // - findGetter/findSetter
    // - generateDifferentValue
    // - invokeBuilderMethod
    // - buildAndVerify
    // - 断言方法(assertNotNull, assertEquals等)

}