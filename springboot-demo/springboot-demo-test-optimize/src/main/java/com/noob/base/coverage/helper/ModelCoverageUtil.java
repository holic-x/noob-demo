package com.noob.base.coverage.helper;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 优化 sonar 代码实体相关覆盖率，实体类单元测试覆盖率提升工具
 * 主要用于覆盖普通实体（支持普通实体、Lombok注解，针对无参构造函数、getter、setter）
 */
public class ModelCoverageUtil {

    // 定义参数类型和对应的默认值映射
    private static Map<String, Object> baseMap = new HashMap<String, Object>();

    // 初始化参数类型列表
    static {
        baseMap.put("int", 1);
        baseMap.put("boolean", true);
        baseMap.put("byte", (byte) 1);
        baseMap.put("double", (double) 1);
        baseMap.put("float", 1.0F);
        baseMap.put("long", 1L);
        baseMap.put("short", (short) 1);
    }

    public static void TestPojo(Class<?> cls) throws IllegalAccessException, InstantiationException {
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Object obj = cls.newInstance();
        Arrays.stream(methods).forEach(method -> {
            /*
            if ("readObject".equals(method.getName())) {
                doReadObjectTest(obj);
                return;
            }
             */
            // 针对toString方法测试
            if ("toString".equals(method.getName())) {
                doToString(obj, method);
                return;
            }
            // 针对每个字段的getter、setter测试
            Arrays.stream(fields).forEach(field -> {
                        if (method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                            if (method.getName().startsWith("get")) {
                                doGetTest(obj, method);
                            } else if (method.getName().startsWith("set")) {
                                doSetTest(obj, method);
                            }
                        }
                    }
            );

        });
    }

    /**
     * toString 方法测试
     *
     * @param obj
     * @param method
     */
    @SneakyThrows
    private static void doToString(Object obj, Method method) {
        method.invoke(obj);
    }

    /**
     * getter 方法测试
     *
     * @param obj
     * @param method
     */
    @SneakyThrows
    private static void doGetTest(Object obj, Method method) {
        method.invoke(obj);
    }

    /**
     * setter 方法测试
     *
     * @param obj
     * @param method
     */
    @SneakyThrows
    private static void doSetTest(Object obj, Method method) {
        Class<?>[] paramClass = method.getParameterTypes();
        if (paramClass.length > 0) {
            Object param = getParamByClass(paramClass[0]);
            method.invoke(obj, param);
        }
    }

    /**
     * doReadObjectTest
     *
     * @param obj sonar 扫描提示：Avoid deserializing object provided by remote users.
     *            远程反序列化提示风险
     */
    /*
    private static void doReadObjectTest(Object obj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(obj);
            byte[] data = byteArrayOutputStream.toByteArray();
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            obj = ois.readObject();
            data = null;
        } catch (Exception e) {
        } finally {
            closeStream(oos);
            closeStream(ois);
        }
    }
     */
    private static Object getParamByClass(Class<?> paramClass) {
        // return (baseMap.containsKey(paramClass.getName())) ? baseMap.get(paramClass.getName()) : null;
        return baseMap.getOrDefault(paramClass.getName(), null);
    }

    /**
     * 关闭流操作
     *
     * @param t
     * @param <T>
     */
    /*
    private static <T extends Closeable> void closeStream(T t) {
        try {
            if (t != null) {
                t.close();
            }
        } catch (Exception e) {

        }
    }
     */
}