package com.noob.base.coverage.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 实例创建工具类 - 提供多种方式创建类实例
 * <p>
 * 功能特点：
 * 1. 支持无参构造器创建
 * 2. 支持Builder模式创建
 * 3. 支持全参/部分参数构造器创建
 * 4. 支持使用Unsafe API作为最后手段
 * 5. 自动处理基本类型和对象类型的默认值
 * 6. 防止循环依赖导致的无限递归
 * <p>
 * 适用环境：JDK8+
 */
public class InstanceGenerateHelper {

    // 默认值映射数据集：从数据辅助生成工具类中统一维护处理
    public static final Map<Class<?>, Object> DEFAULT_VALUES = DataGenerateHelper.initDefaultValues();

    /**
     * 创建指定类的实例
     *
     * @param clazz 要实例化的类
     * @param <T>   类型参数
     * @return 创建的实例
     * @throws Exception 当所有尝试都失败时抛出异常
     */
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        // 前置检查类是否可实例化
        checkInstantiable(clazz);

        // 1. 首先尝试无参构造器
        Optional<T> instance = tryNoArgConstructor(clazz);
        if (instance.isPresent()) {
            return instance.get();
        }

        // 2. 尝试Builder模式
        instance = tryBuilderPattern(clazz);
        if (instance.isPresent()) {
            return instance.get();
        }

        // 3. 尝试所有可能的构造器
        instance = tryAllConstructors(clazz);
        if (instance.isPresent()) {
            return instance.get();
        }

        // 4. 最后手段 - 使用Unsafe API
        instance = tryUnsafe(clazz);
        if (instance.isPresent()) {
            return instance.get();
        }

        throw new RuntimeException("无法创建 " + clazz.getName() + " 的实例，所有方法都尝试失败了");
    }

    /**
     * 检查类是否可实例化
     *
     * @param clazz 待检查的类
     * @throws IllegalArgumentException 如果类不可实例化
     */
    private static <T> void checkInstantiable(Class<T> clazz) {
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("不能实例化接口: " + clazz.getName());
        }
        if (Modifier.isAbstract(clazz.getModifiers())) {
            throw new IllegalArgumentException("不能实例化抽象类: " + clazz.getName());
        }
        if (clazz.isArray()) {
            throw new IllegalArgumentException("不能实例化数组类型: " + clazz.getName());
        }
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("不能实例化基本类型: " + clazz.getName());
        }
    }

    /**
     * 尝试使用无参构造器创建实例
     *
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
     */
    private static <T> Optional<T> tryNoArgConstructor(Class<T> clazz) {
        try {
            // 获取无参构造器并设置可访问
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return Optional.of(constructor.newInstance());
        } catch (NoSuchMethodException e) {
            // 无无参构造器是正常情况，返回empty
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("无参构造器调用失败", e);
        }
    }

    /**
     * 尝试使用Builder模式创建实例（兼容Lombok正常生成的Builder模式以及一些可能存在非公共Builder方法的特殊场景）
     * 直线型流程，没有错误恢复机制，但可满足基本的场景类型，无法兼容一些特例的非标准Builder类的场景
     *
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
     * <p>
     * 增强版builder模式覆盖：BuilderPatternEnhancerHelper.tryBuilderPatternEnhance(clazz),可额外支持方法名后缀的版本覆盖
     */
    private static <T> Optional<T> tryBuilderPattern(Class<T> clazz) {
        try {
            // 1. 获取builder()方法并确保可访问
            Method builderMethod = clazz.getMethod("builder");
            builderMethod.setAccessible(true);  // 确保即使是非公共方法也能调用

            // 2. 调用静态builder()方法获取Builder实例
            Object builder = builderMethod.invoke(null);

            // 3. 获取build()方法并确保可访问
            Method buildMethod = builder.getClass().getMethod("build");
            buildMethod.setAccessible(true);  // 确保即使是非公共方法也能调用

            // 4. 调用build()方法创建目标实例
            return Optional.of((T) buildMethod.invoke(builder));
        } catch (NoSuchMethodException e) {
            // 没有builder()方法是正常情况，返回empty
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Builder模式调用失败", e);
        }
    }

    /**
     * 尝试使用所有可能的构造器创建实例
     *
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
     */
    private static <T> Optional<T> tryAllConstructors(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
                // 按参数数量降序排序，优先尝试参数多的构造器
                .sorted((c1, c2) -> Integer.compare(c2.getParameterCount(), c1.getParameterCount()))
                .map(constructor -> {
                    try {
                        constructor.setAccessible(true);
                        // 为构造器参数生成默认值
                        Object[] params = generateParameters(constructor.getParameterTypes());
                        return Optional.of((T) constructor.newInstance(params));
                    } catch (Exception e) {
                        // 当前构造器失败，返回empty继续尝试下一个
                        return Optional.<T>empty();
                    }
                })
                // 过滤掉失败的尝试
                .filter(Optional::isPresent)
                // 获取第一个成功的实例
                .findFirst()
                // 如果没有成功的，返回empty
                .orElse(Optional.empty());
    }

    /**
     * 尝试使用Unsafe API创建实例(绕过构造器)
     *
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
     */
    private static <T> Optional<T> tryUnsafe(Class<T> clazz) {
        try {
            // 获取Unsafe实例
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Object unsafe = unsafeClass.getDeclaredField("theUnsafe").get(null);
            // 使用allocateInstance方法分配实例(不调用构造器)
            return Optional.of((T) unsafeClass.getMethod("allocateInstance", Class.class)
                    .invoke(unsafe, clazz));
        } catch (Exception e) {
            // Unsafe不可用是正常情况，返回empty
            return Optional.empty();
        }
    }

    /**
     * 为参数类型数组生成默认值数组
     *
     * @param paramTypes 参数类型数组
     * @return 默认值数组
     */
    private static Object[] generateParameters(Class<?>[] paramTypes) {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = DEFAULT_VALUES.getOrDefault(paramTypes[i], null);
        }
        return params;
    }

    // -----------------------------------------------------------------------------------------
    // 不同场景的实例创建
    // -----------------------------------------------------------------------------------------

    /**
     * 深拷贝值数据
     *
     * @param value
     * @return
     */
    private static Object deepCopyValue(Object value) {
        if (value == null) {
            return null;
        }

        // 简单类型直接返回
        if (value.getClass().isPrimitive() ||
                value instanceof String ||
                value instanceof Number ||
                value instanceof Boolean) {
            return value;
        }

        // 其他情况返回原值（复杂对象需要特殊处理）
        return value;
    }

    /**
     * 创建一个与标准实例字段值相同的新实例
     */
    public static <T> T createEqualInstance(Class<T> clazz) throws Exception {
        T instance = createInstance(clazz);
        T equalInstance = clazz.newInstance();

        // 复制所有字段值
        List<Field> fields = InvokeHelper.getAllFields(clazz);
        for (Field field : fields) {
            if (!Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                Object value = field.get(instance);
                field.set(equalInstance, deepCopyValue(value));
            }
        }

        return equalInstance;
    }


    public static <T> T createDifferentInstance(Class<T> clazz) throws Exception {
        T instance = createInstance(clazz);

        // todo
        List<Field> fields = InvokeHelper.getAllFields(clazz);
        if (!fields.isEmpty()) {
            Field fieldToModify = fields.get(0);
            Object originalValue = InvokeHelper.getFieldValue(fieldToModify, instance);
            Object newValue = InvokeHelper.generateDifferentValue(originalValue, fieldToModify.getType());
            InvokeHelper.setFieldValue(fieldToModify, instance, newValue);
        }
        return instance;
    }


}