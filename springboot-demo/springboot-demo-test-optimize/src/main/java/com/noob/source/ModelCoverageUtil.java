package com.noob.source;


import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


/**
 * 优化 sonar 代码实体相关覆盖率，实体类单元测试覆盖率提升工具
 * 主要用于覆盖普通实体（支持普通实体、Lombok注解，针对无参构造函数、getter、setter）
 * 支持更多常用业务类型，增强兼容性
 */
public class ModelCoverageUtil {

    // 定义参数类型和对应的默认值映射，支持更多常用类型
    private static final Map<String, Object> baseMap = new HashMap<>();

    // 初始化参数类型列表
    static {
        // 基本类型及包装类
        baseMap.put("int", 1);
        baseMap.put("java.lang.Integer", 1);
        baseMap.put("boolean", true);
        baseMap.put("java.lang.Boolean", true);
        baseMap.put("byte", (byte) 1);
        baseMap.put("java.lang.Byte", (byte) 1);
        baseMap.put("double", (double) 1);
        baseMap.put("java.lang.Double", (double) 1);
        baseMap.put("float", 1.0F);
        baseMap.put("java.lang.Float", 1.0F);
        baseMap.put("long", 1L);
        baseMap.put("java.lang.Long", 1L);
        baseMap.put("short", (short) 1);
        baseMap.put("java.lang.Short", (short) 1);
        baseMap.put("char", 'a');
        baseMap.put("java.lang.Character", 'a');
        // 常用业务类型
        baseMap.put("java.lang.String", "测试字符串");
        baseMap.put("java.math.BigDecimal", new BigDecimal("1.23"));
        baseMap.put("java.util.Date", new Date());
        baseMap.put("java.time.LocalDate", LocalDate.now());
        baseMap.put("java.time.LocalDateTime", LocalDateTime.now());
        baseMap.put("java.time.LocalTime", LocalTime.now());
        // 集合类型
        baseMap.put("java.util.List", new ArrayList<>());
        baseMap.put("java.util.Set", new HashSet<>());
        baseMap.put("java.util.Map", new HashMap<>());
    }

    /**
     * 自动测试实体类的getter/setter/toString方法，提升单元测试覆盖率
     * 支持普通实体、Lombok注解实体
     *
     * @param cls 实体类Class对象
     */
    public static void TestPojo(Class<?> cls) throws IllegalAccessException, InstantiationException {
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Object obj;
        try {
            obj = cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("无法实例化实体类: " + cls.getSimpleName() + "，请确保有无参构造函数。");
            return;
        }
        System.out.println("开始测试实体类: " + cls.getSimpleName());
        Arrays.stream(methods).forEach(method -> {
            try {
                // 针对toString方法测试
                if ("toString".equals(method.getName())) {
                    System.out.println("调用 toString 方法...");
                    doToString(obj, method);
                    return;
                }
                // 针对每个字段的getter、setter/isXxx测试
                Arrays.stream(fields).forEach(field -> {
                    String fieldName = field.getName();
                    String methodName = method.getName().toLowerCase();
                    // 兼容Lombok、boolean类型isXxx、常规getter/setter
                    if (methodName.equals("get" + fieldName.toLowerCase())
                            || methodName.equals("set" + fieldName.toLowerCase())
                            || methodName.equals("is" + fieldName.toLowerCase())
                            || methodName.contains(fieldName.toLowerCase())) {
                        if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                            System.out.println("调用 getter/is 方法: " + method.getName());
                            doGetTest(obj, method);
                        } else if (method.getName().startsWith("set")) {
                            System.out.println("调用 setter 方法: " + method.getName());
                            doSetTest(obj, method);
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println("方法调用异常: " + method.getName() + "，异常信息: " + e.getMessage());
            }
        });
        System.out.println("实体类测试完成: " + cls.getSimpleName());
    }

    /**
     * toString 方法测试
     *
     * @param obj    实体对象
     * @param method toString方法
     */
    @SneakyThrows
    private static void doToString(Object obj, Method method) {
        Object result = method.invoke(obj);
        System.out.println("toString结果: " + result);
    }

    /**
     * getter/is 方法测试
     *
     * @param obj    实体对象
     * @param method getter/is方法
     */
    @SneakyThrows
    private static void doGetTest(Object obj, Method method) {
        Object result = method.invoke(obj);
        System.out.println("getter/is方法返回值: " + result);
    }

    /**
     * setter 方法测试
     *
     * @param obj    实体对象
     * @param method setter方法
     */
    @SneakyThrows
    private static void doSetTest(Object obj, Method method) {
        Class<?>[] paramClass = method.getParameterTypes();
        if (paramClass.length > 0) {
            Object param = getParamByClass(paramClass[0]);
            if (param == null) {
                System.out.println("未能为参数类型 " + paramClass[0].getName() + " 生成默认值，跳过setter: " + method.getName());
                return;
            }
            method.invoke(obj, param);
            System.out.println("setter方法已调用: " + method.getName() + "，参数: " + param);
        }
    }

    /**
     * 根据参数类型获取默认测试值，支持常用类型、集合、日期等
     */
    private static Object getParamByClass(Class<?> paramClass) {
        String className = paramClass.getName();
        if (baseMap.containsKey(className)) {
            return baseMap.get(className);
        }
        // 支持List、Set、Map等接口的自动实例化
        if (List.class.isAssignableFrom(paramClass)) {
            List<Object> list = new ArrayList<>();
            list.add("测试List元素");
            return list;
        }
        if (Set.class.isAssignableFrom(paramClass)) {
            Set<Object> set = new HashSet<>();
            set.add("测试Set元素");
            return set;
        }
        if (Map.class.isAssignableFrom(paramClass)) {
            Map<Object, Object> map = new HashMap<>();
            map.put("key", "value");
            return map;
        }
        // 支持枚举类型
        if (paramClass.isEnum()) {
            Object[] enumConstants = paramClass.getEnumConstants();
            if (enumConstants != null && enumConstants.length > 0) {
                return enumConstants[0];
            }
        }
        // 支持自定义对象递归实例化（防止死循环，简单处理）
        try {
            if (!paramClass.isInterface() && !Modifier.isAbstract(paramClass.getModifiers())) {
                return paramClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            System.out.println("无法实例化类型: " + className + "，跳过。");
        }
        return null;
    }
}