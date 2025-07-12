package com.noob.base.coverage.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * todo
 * 覆盖率测试工具类 - 枚举类、常量等覆盖
 */
public class EnumCoverageUtil {

    /**
     * 安全测试枚举类的所有值
     *
     * @param enumClass 枚举类
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> void testEnum(Class<T> enumClass) {
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException(enumClass.getName() + " is not an enum type");
        }

        try {
            // 安全获取values()方法
            Method valuesMethod = enumClass.getDeclaredMethod("values");
            Enum<?>[] values = (Enum<?>[]) valuesMethod.invoke(null);

            // 安全获取valueOf方法
            Method valueOfMethod = enumClass.getDeclaredMethod("valueOf", String.class);

            for (Enum<?> value : values) {
                // 测试valueOf方法
                Enum<?> result = (Enum<?>) valueOfMethod.invoke(null, value.name());
                if (result != value) {
                    throw new AssertionError("Enum valueOf test failed for " + value.name());
                }

                // 调用toString确保覆盖
                String toStringResult = value.toString();
                if (toStringResult == null) {
                    throw new AssertionError("Enum toString() returned null for " + value.name());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to test enum: " + enumClass.getName(), e);
        }
        /*
        catch (NoSuchMethodException e) {
            throw new IllegalStateException("Enum class is missing standard methods", e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to test enum: " + enumClass.getName(), e);
        }
         */
    }

    /**
     * 测试常量类的所有public static final字段
     *
     * @param constantsClass 常量类
     */
    @SneakyThrows
    public static void testConstants(Class<?> constantsClass) {
        Field[] fields = constantsClass.getDeclaredFields();

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                try {
                    // 确保可访问（处理可能的安全管理器限制）
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }

                    // 访问字段以确保覆盖
                    Object value = field.get(null);
                    if (value != null) {
                        String str = value.toString();
                        if (str == null) {
                            throw new AssertionError("toString() returned null for field: " + field.getName());
                        }
                    }
                }
                /*
                catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access constant field: " + field.getName(), e);
                }
                 */ finally {
                    // 恢复访问权限
                    field.setAccessible(false);
                }
            }
        }
    }

}


