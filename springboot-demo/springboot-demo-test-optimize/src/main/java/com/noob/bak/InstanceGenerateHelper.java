package com.noob.bak;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

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
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
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


    // --------------------------------------------------------------------------------------------
    // 增强版本 tryBuilderPattern 方法设计：用于兼容一些非标准Builder类的场景，此处是做补充增强
    // --------------------------------------------------------------------------------------------

    /**
     * 尝试使用Builder模式创建实例（带双重尝试机制）
     * 1. 先尝试常规构建路径（标准public方法）
     * 2. 失败后尝试突破访问限制（处理非public方法场景）
     * 解耦构建、执行，提供回退机制，兼容非标准Builder场景，覆盖维度更加完善
     *
     * @param clazz 目标类
     * @return 包含实例的Optional，如果失败返回empty
     */
    private static <T> Optional<T> tryBuilderPatternEnhance(Class<T> clazz) {
        try {
            // 阶段1：获取Builder实例
            Object builder = getBuilderInstanceWithFallback(clazz);

            // 阶段2：双重尝试构建
            return Optional.of(buildWithRetry(builder, clazz));
        } catch (NoSuchMethodException e) {
            return Optional.empty(); // 没有Builder特征是正常情况
        } catch (Exception e) {
            throw new RuntimeException("Builder模式调用失败: " + clazz.getName(), e);
        }
    }

    /**
     * 获取Builder实例（带回退机制）
     */
    private static Object getBuilderInstanceWithFallback(Class<?> clazz) throws Exception {
        try {
            // 优先尝试getMethod()获取public方法
            Method builderMethod = clazz.getMethod("builder");
            return builderMethod.invoke(null);
        } catch (NoSuchMethodException e) {
            // 回退到getDeclaredMethod()获取任意可见性方法
            Method builderMethod = clazz.getDeclaredMethod("builder");
            builderMethod.setAccessible(true);
            return builderMethod.invoke(null);
        }
    }

    /**
     * 双重尝试构建机制
     */
    private static <T> T buildWithRetry(Object builder, Class<T> clazz) throws Exception {
        try {
            // 第一次尝试：常规构建（public方法）
            return tryNormalBuild(builder);
        } catch (IllegalAccessException e) {
            // 第二次尝试：突破访问限制
            return tryBreakThroughBuild(builder);
        }
    }

    /**
     * 常规构建路径
     */
    private static <T> T tryNormalBuild(Object builder) throws Exception {
        Method buildMethod = builder.getClass().getMethod("build");
        return (T) buildMethod.invoke(builder);
    }

    /**
     * 突破限制构建路径
     */
    private static <T> T tryBreakThroughBuild(Object builder) throws Exception {
        Method buildMethod = builder.getClass().getDeclaredMethod("build");
        buildMethod.setAccessible(true);
        return (T) buildMethod.invoke(builder);
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
     * @param parameterTypes 参数类型数组
     * @return 默认值数组
     */
    private static Object[] generateParameters(Class<?>[] parameterTypes) {
        return Stream.of(parameterTypes)
                .map(InstanceGenerateHelper::generateParameter)
                .toArray();
    }

    /**
     * 为单个参数类型生成默认值
     *
     * @param type 参数类型
     * @return 对应的默认值
     */
    private static Object generateParameter(Class<?> type) {
        // 基本类型和常用包装类型的默认值
        if (type == boolean.class || type == Boolean.class) {
            return false;
        } else if (type == byte.class || type == Byte.class) {
            return (byte) 0;
        } else if (type == short.class || type == Short.class) {
            return (short) 0;
        } else if (type == int.class || type == Integer.class) {
            return 0;
        } else if (type == long.class || type == Long.class) {
            return 0L;
        } else if (type == float.class || type == Float.class) {
            return 0.0f;
        } else if (type == double.class || type == Double.class) {
            return 0.0;
        } else if (type == char.class || type == Character.class) {
            return '\0';
        } else if (type == String.class) {
            return "";
        } else {
            // 对于对象类型，尝试递归创建实例
            try {
                return createInstance(type);
            } catch (Exception e) {
                // 递归创建失败返回null
                return null;
            }
        }
    }
}