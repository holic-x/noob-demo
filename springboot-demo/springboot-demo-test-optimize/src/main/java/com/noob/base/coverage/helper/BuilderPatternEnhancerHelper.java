package com.noob.base.coverage.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 增强版Builder模式实例构建工具
 * 三级构建策略：
 * 第一级：tryNormalBuild() 使用标准public方法
 * 第二级：tryBreakThroughBuild() 突破方法访问限制
 * 第三级：tryBreakThroughImplClass() 处理BuilderImpl特殊类
 */
public class BuilderPatternEnhancerHelper {

    /**
     * 增强版Builder模式实例构建（三级降级策略）
     *
     * @param clazz 目标类
     * @return 构建成功的实例（Optional）
     * @throws RuntimeException 当所有策略均失败时抛出
     */
    public static <T> Optional<T> tryBuilderPatternEnhance(Class<T> clazz) {
        try {
            // 获取Builder实例（自动降级策略）
            Object builder = getBuilderInstanceWithFallback(clazz);

            // 三级构建策略尝试
            return Optional.of(buildWithAllStrategies(builder, clazz));
        } catch (NoSuchMethodException e) {
            return Optional.empty(); // 确认无Builder特征
        } catch (Exception e) {
            throw new RuntimeException("Builder模式构建失败: " + clazz.getName(), e);
        }
    }

    // ================== 核心构建策略 ==================

    /**
     * 三级构建策略（逐步降级）
     */
    private static <T> T buildWithAllStrategies(Object builder, Class<T> clazz) throws Exception {
        try {
            return tryNormalBuild(builder); // 第一级：标准构建
        } catch (IllegalAccessException e) {
            try {
                return tryBreakThroughBuild(builder); // 第二级：突破方法限制
            } catch (Exception e2) {
                return tryBreakThroughImplClass(builder, clazz); // 第三级：突破Impl类限制
            }
        }
    }

    /**
     * 标准构建路径（public方法）
     */
    private static <T> T tryNormalBuild(Object builder) throws Exception {
        Method buildMethod = builder.getClass().getMethod("build");
        return (T) buildMethod.invoke(builder);
    }

    /**
     * 突破方法访问限制（非public方法）
     */
    private static <T> T tryBreakThroughBuild(Object builder) throws Exception {
        Method buildMethod = builder.getClass().getDeclaredMethod("build");
        buildMethod.setAccessible(true);
        return (T) buildMethod.invoke(builder);
    }

    /**
     * 突破BuilderImpl类限制（兼容历史方案）
     */
    private static <T> T tryBreakThroughImplClass(Object builder, Class<T> clazz) throws Exception {
        try {
            Class<?> builderImplClass = Class.forName(builder.getClass().getName() + "Impl");
            Constructor<?> constructor = builderImplClass.getDeclaredConstructor(builder.getClass());
            constructor.setAccessible(true);
            Object builderImpl = constructor.newInstance(builder);

            Method buildMethod = builderImplClass.getDeclaredMethod("build");
            buildMethod.setAccessible(true);

            @SuppressWarnings("unchecked")
            T instance = (T) buildMethod.invoke(builderImpl);
            return instance;
        } catch (ClassNotFoundException e) {
            throw new NoSuchMethodException("BuilderImpl类不存在");
        }
    }

    // ================== Builder实例获取 ==================

    /**
     * 获取Builder实例（带降级策略）
     */
    private static Object getBuilderInstanceWithFallback(Class<?> clazz) throws Exception {
        // 优先尝试标准builder()方法
        try {
            Method builderMethod = clazz.getMethod("builder");
            return builderMethod.invoke(null);
        } catch (NoSuchMethodException e) {
            // 降级尝试非public方法
            Method builderMethod = clazz.getDeclaredMethod("builder");
            builderMethod.setAccessible(true);
            return builderMethod.invoke(null);
        }
    }
}