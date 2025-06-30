package com.noob.base.coverage.helper;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.noob.base.coverage.helper.CustomAssertUtil.assertNotNull;

/**
 * 反射辅助方法：用于Coverage覆盖测试
 */
public class InvokeHelperUtil {

    private static final Random random = new Random();

    private static final String BUILDER_IMPL_SUFFIX = "$BuilderImpl";

    public static final Map<Class<?>, Object> DEFAULT_VALUES;

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

        // fix 常见引用类型数据补充
        map.put(List.class, new ArrayList<>()); // tofix：可自定补充常见引用类型数据

        DEFAULT_VALUES = Collections.unmodifiableMap(map);
    }


    // ====================== 核心工具方法 ======================
    public static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            if (targetType.isPrimitive()) {
                throw new IllegalArgumentException("不能给基本类型字段赋null值");
            }
            return null;
        }

        // 类型兼容直接返回
        if (targetType.isInstance(value)) {
            return value;
        }

        // 处理Boolean和boolean互转
        if ((targetType == boolean.class || targetType == Boolean.class)
                && (value instanceof Boolean)) {
            return value;
        }

        // 处理Character和char互转
        if ((targetType == char.class || targetType == Character.class)
                && (value instanceof Character)) {
            return (char) value; // 自动处理装箱/拆箱
        }

        // 数字类型转换
        if (value instanceof Number && (targetType.isPrimitive() || Number.class.isAssignableFrom(targetType))) {
            Number number = (Number) value;
            if (targetType == int.class || targetType == Integer.class) return number.intValue();
            if (targetType == long.class || targetType == Long.class) return number.longValue();
            if (targetType == double.class || targetType == Double.class) return number.doubleValue();
            if (targetType == float.class || targetType == Float.class) return number.floatValue();
            if (targetType == short.class || targetType == Short.class) return number.shortValue();
            if (targetType == byte.class || targetType == Byte.class) return number.byteValue();
        }

        // String转换
        if (targetType == String.class) {
            return value.toString();
        }

        throw new IllegalArgumentException(String.format("无法将 %s 转换为 %s 类型", value.getClass().getSimpleName(), targetType.getSimpleName()));
    }

    public static <T> T createInstance(Class<T> clazz) throws Exception {
        // 1. 尝试无参构造器
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            // 2.尝试Builder模式
            /*
            // sonar scanner error："@Builder"、"@SuperBuilder" is not available at runtime and cannot be seen with reflection.
            if (clazz.isAnnotationPresent(Builder.class) || clazz.isAnnotationPresent(SuperBuilder.class)) {
                return buildAndVerify(invokeBuilderMethod(clazz), clazz);
            }
             */

            // fix sonar scanner error:直接使用Builder模式处理，如果失败则捕获异常跳过尝试下一个策略（尝试Builder模式 (不再检查注解，因为它们是SOURCE级别的)）
            try {
                // 直接尝试调用builder()方法。如果方法不存在，invokeBuilderMethod会抛出异常。
                return buildAndVerify(invokeBuilderMethod(clazz), clazz);
            } catch (Exception | AssertionError builderError) {
                // Builder模式失败或不存在，继续尝试下一个策略
                // AssertionError is caught because invokeBuilderMethod throws it.
            }

            // 3. 尝试全参构造器
            Constructor<T> constructor = findFullArgsConstructor(clazz);
            if (constructor != null) {
                constructor.setAccessible(true);
                return constructor.newInstance(generateParameters(constructor.getParameterTypes()));
            }

            throw new RuntimeException("找不到可用的构造器");
        }
    }


    /*
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        // 1. 优先尝试Builder模式（适用于@Builder/@SuperBuilder）
        if (clazz.isAnnotationPresent(Builder.class) || clazz.isAnnotationPresent(SuperBuilder.class)) {
            try {
                return createViaBuilder(clazz);
            } catch (Exception e) {
                log.warn("Builder模式创建失败，尝试其他方式", e);
            }
        }

        // 2. 尝试无参构造器
        try {
            Constructor<T> noArgCtor = clazz.getDeclaredConstructor();
            noArgCtor.setAccessible(true);
            return noArgCtor.newInstance();
        } catch (NoSuchMethodException e) {
            log.debug("类 {} 没有无参构造器", clazz.getSimpleName());
        }

        // 3. 尝试全参构造器（突破public限制）
        try {
            Constructor<T> fullArgCtor = findFullArgsConstructor(clazz);
            if (fullArgCtor != null) {
                fullArgCtor.setAccessible(true); // 突破public限制
                Object[] params = generateParameters(fullArgCtor.getParameterTypes());
                return fullArgCtor.newInstance(params);
            }
        } catch (Exception e) {
            log.warn("全参构造器调用失败", e);
        }

        // 4. 终极fallback：通过Unsafe API（不推荐但可靠）
        try {
            return createInstanceViaUnsafe(clazz);
        } catch (Exception e) {
            throw new RuntimeException("无法创建实例，请为 " + clazz.getName() +
                    " 添加 @NoArgsConstructor 或 @AllArgsConstructor", e);
        }
    }

    // 通过Builder创建实例
    public static <T> T createViaBuilder(Class<T> clazz) throws Exception {
        Object builder = clazz.getMethod("builder").invoke(null);
        // 自动填充字段默认值
        for (Field field : getAllFields(clazz)) {
            try {
                Optional<Method> setter = findBuilderSetter(builder.getClass(), field);
                if (setter.isPresent()) {
                    Object value = generateSafeValue(field.getType());
                    setter.get().invoke(builder, value);
                }
            } catch (Exception e) {
                log.warn("无法设置Builder字段: {}", field.getName(), e);
            }
        }
        return (T) builder.getClass().getMethod("build").invoke(builder);
    }

    // 通过Unsafe API创建实例（绕过构造器）
    public static <T> T createInstanceViaUnsafe(Class<T> clazz) throws Exception {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            return (T) unsafe.allocateInstance(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unsafe初始化失败", e);
        }
    }

     */

    /*
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        if (clazz.isAnnotationPresent(SuperBuilder.class)) {
            Class<?> enhanced = new ByteBuddy()
                    .subclass(clazz)
                    .defineConstructor(Modifier.PUBLIC)
                    .intercept(MethodCall.invoke(clazz.getDeclaredConstructor()))
                    .make()
                    .load(clazz.getClassLoader())
                    .getLoaded();
            return (T) enhanced.newInstance();
        }
        return clazz.newInstance();
    }

     */


    // ====================== 私有字段支持 ======================

    /**
     * 安全获取字段值（支持私有字段）
     */
    public static Object getFieldValue(Field field, Object target) throws IllegalAccessException {
        boolean wasAccessible = field.isAccessible();
        try {
            field.setAccessible(true);
            return field.get(target);
        } finally {
            field.setAccessible(wasAccessible);
        }
    }

    /**
     * 安全设置字段值（支持私有字段）
     * 设置访问权限，setter处理完成恢复访问权限（用于测试，安全处理）
     */
    public static void setFieldValue(Field field, Object target, Object value) throws IllegalAccessException {
        boolean wasAccessible = field.isAccessible();
        try {
            // 设置访问权限
            field.setAccessible(true);

            // 检查是否为final字段
            if (isFinal(field)) {
                // throw new IllegalArgumentException( String.format("字段 %s 是final的，不允许修改", fieldName));
                return; // 针对final字段，跳过该字段的setter概念，避免触发setter异常
            }

            // 设置值
            field.set(target, convertValue(value, field.getType()));
        } finally {
            // 恢复访问权限
            field.setAccessible(wasAccessible);
        }
    }


    private static boolean isFinal(Field field) {
        return (field.getModifiers() & Modifier.FINAL) != 0;
    }


    /*

    // enhance 安全设置字段值（支持私有、static、final字段）-无效(考虑版本兼容或者木块访问受限导致final set处理失败)
    public static void setFieldValue(Field field, Object target, Object value)
            throws Exception {

        // 保存原始可访问性和修饰符状态
        boolean wasAccessible = field.isAccessible();
        int originalModifiers = field.getModifiers();

        try {
            // 设置访问权限
            field.setAccessible(true);

            // 移除final修饰符
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            // 设置值
            field.set(target, convertValue(value, field.getType()));
        } catch (Exception e){
              // throw new IllegalArgumentException(e.getMessage());
            throw e; // 抛出异常
        }finally {
            try {
                // 恢复原始修饰符
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, originalModifiers);

                // 恢复可访问性
                field.setAccessible(wasAccessible);
            } catch (Exception e) {
                // 恢复失败记录日志，但不影响主流程
                System.err.println("Warning: Failed to restore field state: " + e.getMessage());
            }
        }

    }

     */

    public static <T> T createDifferentInstance(Class<T> clazz) throws Exception {
        T instance = createInstance(clazz);
        List<Field> fields = getAllFields(clazz);
        if (!fields.isEmpty()) {
            Field fieldToModify = fields.get(0);
            Object originalValue = getFieldValue(fieldToModify, instance);
            Object newValue = generateDifferentValue(originalValue, fieldToModify.getType());
            setFieldValue(fieldToModify, instance, newValue);
        }
        return instance;
    }

    /*
    source
    public static Object DataAnnotationCoverageUtil(Object original, Class<?> type) {
        // 处理null值
        if (original == null) {
            if (type == boolean.class || type == Boolean.class) return true;
            if (type == int.class || type == Integer.class) return 1;
            if (type == long.class || type == Long.class) return 1L;
            if (type == String.class) return "test";
            return "non-null";
        }

        // 基本类型和包装类型
        if (type == boolean.class || type == Boolean.class) return !(Boolean) original;
        if (type == byte.class || type == Byte.class) return ((Number) original).byteValue() + 1;
        if (type == short.class || type == Short.class) return ((Number) original).shortValue() + 1;
        if (type == int.class || type == Integer.class) return ((Number) original).intValue() + 1;
        if (type == long.class || type == Long.class) return ((Number) original).longValue() + 1;
        if (type == float.class || type == Float.class) return ((Number) original).floatValue() + 1;
        if (type == double.class || type == Double.class) return ((Number) original).doubleValue() + 1;
        if (type == char.class || type == Character.class) return (char) ((Character) original + 1);

        // 字符串类型
        if (type == String.class) return original + "-modified";

        // 其他引用类型
        return original;
    }

     */


    /**
     * 生成与原值不同的值（支持List等集合类型）
     *
     * @param original 原始值
     * @param type     字段类型（用于泛型推断）
     * @return 不同的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T generateDifferentValue(T original, Class<?> type) {
        if (original == null) {
            return (T) generateNonNullValue(type);
        }

        Class<?> originalClass = original.getClass();

        // 处理基本类型
        if (originalClass.equals(Integer.class)) {
            return (T) (Integer) ((Integer) original + 1);
        } else if (originalClass.equals(Long.class)) {
            return (T) (Long) ((Long) original + 1L);
        } else if (originalClass.equals(Double.class)) {
            return (T) (Double) ((Double) original + 1.0);
        } else if (originalClass.equals(Float.class)) {
            return (T) (Float) ((Float) original + 1.0f);
        } else if (originalClass.equals(Boolean.class)) {
            return (T) (Boolean) !((Boolean) original);
        } else if (originalClass.equals(String.class)) {
            return (T) (original + "_modified");
        } else if (originalClass.equals(Character.class)) {
            return (T) (Character) ((char) (((Character) original) + 1));
        } else if (originalClass.equals(Byte.class)) {
            // 处理 Byte 类型，避免溢出
            byte originalByte = (Byte) original;
            if (originalByte < Byte.MAX_VALUE) { // 防止 +1 后超出 Byte 范围
                return (T) (Byte) (byte) (originalByte + 1);
            } else {
                // 如果已经是最大值，可以返回原值或抛出异常（根据业务需求）
                return original; // 或者 throw new ArithmeticException("Byte overflow");
            }
        } else if (originalClass.equals(Short.class)) {
            short originalShort = (Short) original;
            if (originalShort < Short.MAX_VALUE) { // 检查是否溢出
                return (T) (Short) (short) (originalShort + 1);
            } else {
                return original; // 达到最大值时返回原值（或抛异常）
            }
        }

        // 处理List类型（包括泛型）
        if (List.class.isAssignableFrom(originalClass)) {
            List<?> originalList = (List<?>) original;
            List<Object> newList = new ArrayList<>(originalList);

            if (newList.isEmpty()) {
                // 生成一个泛型元素
                newList.add(generateGenericElement(type, 0));
            } else {
                // 修改策略：50%概率添加元素，50%概率删除元素
                if (random.nextBoolean()) {
                    newList.add(generateGenericElement(type, 0));
                } else {
                    newList.remove(newList.size() - 1);
                }
            }
            return (T) newList;
        }

        // 处理Set类型
        if (Set.class.isAssignableFrom(originalClass)) {
            Set<?> originalSet = (Set<?>) original;
            Set<Object> newSet = new HashSet<>(originalSet);

            if (newSet.isEmpty()) {
                newSet.add(generateGenericElement(type, 0));
            } else {
                if (random.nextBoolean()) {
                    // 添加不重复元素
                    Object newElement;
                    do {
                        newElement = generateGenericElement(type, 0);
                    } while (newSet.contains(newElement));
                    newSet.add(newElement);
                } else {
                    // 移除一个元素
                    newSet.remove(newSet.iterator().next());
                }
            }
            return (T) newSet;
        }

        // 处理数组
        if (originalClass.isArray()) {
            int length = Array.getLength(original);
            Class<?> componentType = originalClass.getComponentType();

            // 创建新数组（长度±1）
            int newLength = length + (random.nextBoolean() ? 1 : -1);
            Object newArray = Array.newInstance(componentType, Math.max(newLength, 1));

            // 复制元素
            System.arraycopy(original, 0, newArray, 0, Math.min(length, newLength));

            // 如果增加了长度，填充新元素
            if (newLength > length) {
                Array.set(newArray, length, generateNonNullValue(componentType));
            }

            return (T) newArray;
        }

        // 默认情况：返回新实例（适用于简单POJO）
        try {
            return (T) originalClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot generate different value for type: " + originalClass, e);
        }
    }

    public static Object generateNonNullValue(Class<?> type) {
        // 基本类型默认值
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == double.class) return 0.0;
        if (type == float.class) return 0f;
        if (type == boolean.class) return false;
        if (type == char.class) return '\0';
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;

        // 常用引用类型
        if (type == String.class) return "default";
        if (type == Integer.class) return 0;
        if (type == Short.class) return new Short("0"); // 此处不能用0，需要返回一个引用类型的数据
        if (type == Float.class) return new Float("0"); // 此处不能用0，需要返回一个引用类型的数据
        if (type == Double.class) return new Double("0"); // 此处不能用0，需要返回一个引用类型的数据
        if (type == Character.class) return new Character('0'); // 此处不能用0，需要返回一个引用类型的数据
        if (type == Long.class) return 0L;
//        if (type == Byte.class) return new Byte[10];
        if (type == Byte.class)
            return new Byte("123"); // Byte 不同 byte[] 类型，需注意兼容问题处理 // 此处如果用"byte"字符串会抛出 java.lang.NumberFormatException: For input string: "byte"
        if (type == Boolean.class) return Boolean.TRUE;// 字符串 & 常用引用类型
        if (type == BigDecimal.class) return BigDecimal.ZERO;
        if (type == BigInteger.class) return BigInteger.ZERO;
        if (type == LocalDate.class) return LocalDate.now();
        if (type == LocalDateTime.class) return LocalDateTime.now();
        if (type == UUID.class) return UUID.randomUUID();

        // 集合类型
        // if (List.class.isAssignableFrom(type) ) return Collections.emptyList();
        if (List.class.isAssignableFrom(type)) {
            // 兼容不同的子类场景，避免类型转化异常导致字段值设置失败
            if (type == ArrayList.class) {
                return new ArrayList<>();
            } else if (type == LinkedList.class) {
                return new LinkedList<>();
            }
            // 默认返回不可变空列表
            return Collections.emptyList();
        }

        if (Set.class.isAssignableFrom(type)) return Collections.emptySet();
        if (Map.class.isAssignableFrom(type)) return Collections.emptyMap();

        // 数组
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }

        // 其他对象类型
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot generate default value for type: " + type, e);
        }
    }

    /**
     * 为泛型集合生成元素（简化版泛型处理）
     */
    private static Object generateGenericElement(Class<?> declaredType, int typeParamIndex) {
        // 这里简化处理，实际应该通过反射获取泛型参数类型
        // 示例仅返回字符串或随机数
        return ThreadLocalRandom.current().nextBoolean() ?
                "elem_" + random.nextInt(100) :
                random.nextInt(1000);
    }


    // -------------------------------------------------------------------------------------

    public static <T> Object invokeBuilderMethod(Class<T> clazz) throws Exception {
        try {
            Method builderMethod = clazz.getMethod("builder");
            Object builder = builderMethod.invoke(null);
            assertNotNull(builder, "builder()方法返回null");
            return builder;
        } catch (NoSuchMethodException e) {
            throw new AssertionError("类标注了@Builder/@SuperBuilder但没有builder()方法", e);
        }
    }

    /*

    tofix 无法兼容@SuperBuilder private 字段修饰的属性处理
    public static <T> T buildAndVerify(Object builder, Class<T> clazz) throws Exception {
        try {
            Method buildMethod = builder.getClass().getMethod("build");
            @SuppressWarnings("unchecked") T instance = (T) buildMethod.invoke(builder);
            assertNotNull(instance, "Builder.build()返回null");
            return instance;
        } catch (Exception e) {
            throw new AssertionError("Builder.build()方法调用失败", e);
        }
    }


     */

//    /**
//     * 安全地构建并验证对象
//     *
//     * @param builder 构建器实例
//     * @param clazz 目标类类型
//     * @param <T> 目标类型
//     * @return 构建的实例
//     * @throws RuntimeException 如果构建或验证失败
//     */
//    public static <T> T buildAndVerify(Object builder, Class<T> clazz) {
//        try {
//            // 1. 获取build方法
//            Method buildMethod = findBuildMethod(builder.getClass());
//
//            // 2. 设置可访问性（处理私有字段问题）
//            makeBuilderFieldsAccessible(builder);
//
//            // 3. 调用build方法
//            @SuppressWarnings("unchecked")
//            T instance = (T) buildMethod.invoke(builder);
//
//            // 4. 验证构建的对象
//            if (instance == null) {
//                throw new RuntimeException("Builder produced null instance");
//            }
//
//            return instance;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to build and verify instance of " + clazz.getName(), e);
//        }
//    }
//
//    /**
//     * 查找构建器的build方法
//     */
//    private static Method findBuildMethod(Class<?> builderClass) throws NoSuchMethodException {
//        try {
//            return builderClass.getMethod("build");
//        } catch (NoSuchMethodException e) {
//            // 尝试查找可能被重命名的build方法
//            return Arrays.stream(builderClass.getMethods())
//                    .filter(m -> m.getParameterCount() == 0)
//                    .filter(m -> !m.getReturnType().equals(void.class))
//                    .filter(m -> !m.getReturnType().equals(builderClass))
//                    .findFirst()
//                    .orElseThrow(() -> new NoSuchMethodException("No suitable build method found in builder"));
//        }
//    }
//
//    /**
//     * 使构建器的所有字段可访问（解决private字段问题）
//     */
//    private static void makeBuilderFieldsAccessible(Object builder) {
//        Class<?> current = builder.getClass();
//
//        // 遍历类层次结构中的所有字段
//        while (current != Object.class) {
//            for (Field field : current.getDeclaredFields()) {
//                try {
////                    if (!field.canAccess(builder)) { // JDK 9 安全设置，不兼容8的话直接进行设置
////                        field.setAccessible(true);
////                    }
//                    field.setAccessible(true);
//                } catch (SecurityException e) {
//                    // 忽略无法访问的字段
//                }
//            }
//            current = current.getSuperclass();
//        }
//    }


    /**
     * 增强版构建验证方法，可突破私有Builder限制
     *
     * @param builder 构建器实例
     * @param clazz   目标类类型
     * @param <T>     目标类型
     * @return 构建的实例
     * @throws RuntimeException 如果构建失败
     */
    public static <T> T buildAndVerify(Object builder, Class<T> clazz) {
        try {
            // 1. 尝试直接构建（常规路径）
            try {
                return tryNormalBuild(builder);
            } catch (IllegalAccessException e) {
                // 2. 如果失败，尝试突破私有Builder限制
                return tryBreakThroughBuild(builder, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException("构建 " + clazz.getSimpleName() + " 失败", e);
        }
    }

    /**
     * 常规构建尝试
     */
    protected static <T> T tryNormalBuild(Object builder) throws Exception {
        Method buildMethod = builder.getClass().getMethod("build");
        if (!buildMethod.isAccessible()) {
            buildMethod.setAccessible(true);
        }
        @SuppressWarnings("unchecked")
        T instance = (T) buildMethod.invoke(builder);
        verifyInstance(instance);
        return instance;
    }

    /**
     * 突破私有限制的构建方案
     */
    protected static <T> T tryBreakThroughBuild(Object builder, Class<T> clazz) throws Exception {
        // 1. 获取BuilderImpl类
        Class<?> builderImplClass = getBuilderImplClass(builder);

        // 2. 获取构造方法
        Constructor<?> constructor = builderImplClass.getDeclaredConstructor(builder.getClass());
        constructor.setAccessible(true);

        // 3. 创建BuilderImpl实例
        Object builderImpl = constructor.newInstance(builder);

        // 4. 调用build方法
        Method buildMethod = builderImplClass.getDeclaredMethod("build");
        buildMethod.setAccessible(true);

        @SuppressWarnings("unchecked")
        T instance = (T) buildMethod.invoke(builderImpl);
        verifyInstance(instance);
        return instance;
    }

    /**
     * 获取Builder实现类
     */
    protected static Class<?> getBuilderImplClass(Object builder) throws ClassNotFoundException {
        String builderImplClassName = builder.getClass().getName() + BUILDER_IMPL_SUFFIX;
        return Class.forName(builderImplClassName);
    }

    /**
     * 验证构建结果
     */
    protected static void verifyInstance(Object instance) {
        if (instance == null) {
            throw new RuntimeException("构建器返回了null实例");
        }
    }

    /**
     * 安全获取字段值（用于调试）
     */
    public static Optional<Object> getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return Optional.ofNullable(field.get(obj));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    // ========================----- // ========================----- // ========================-----

    public static Optional<Method> findBuilderSetter(Class<?> builderClass, Field field) {
        String fieldName = field.getName();
        return Arrays.stream(builderClass.getMethods()).filter(m -> m.getParameterCount() == 1).filter(m -> m.getName().equals(fieldName) || m.getName().equalsIgnoreCase("set" + fieldName)).findFirst();
    }


    // ====================== 反射辅助方法 ======================
    public static Object generateSafeValue(Class<?> type) {
        if (type == String.class) return "";
        if (type == boolean.class) return false;
        if (Number.class.isAssignableFrom(type)) return 0;
        // 其他类型...
        return null;
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    public static Field findFieldByName(Class<?> clazz, String name) {
        return getAllFields(clazz).stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    public static Optional<Method> findGetter(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String getterName = "get" + capitalize(fieldName);
        String booleanGetterName = "is" + capitalize(fieldName);

        return Arrays.stream(clazz.getMethods()).filter(m -> m.getParameterCount() == 0).filter(m -> {
            String name = m.getName();
            return name.equals(getterName) || (field.getType() == boolean.class && name.equals(booleanGetterName));
        }).findFirst();
    }

    public static Optional<Method> findSetter(Class<?> clazz, Field field) {
        String fieldName = field.getName();
        String setterName = "set" + capitalize(fieldName);

        return Arrays.stream(clazz.getMethods()).filter(m -> m.getParameterCount() == 1).filter(m -> m.getName().equals(setterName)).filter(m -> m.getParameterTypes()[0].isAssignableFrom(field.getType())).findFirst();
    }

    // tofix：优先选择参数最多的构造器（存在潜在问题，但如果说只是为了@Data场景覆盖也是一个临时解决方案，实际上应该要参数一一匹配校验）
    public static <T> Constructor<T> findFullArgsConstructor(Class<T> clazz) {
        @SuppressWarnings("unchecked") Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        return Arrays.stream(constructors).max(Comparator.comparingInt(Constructor::getParameterCount)).orElse(null);
    }

    public static Object[] generateParameters(Class<?>[] paramTypes) {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = DEFAULT_VALUES.getOrDefault(paramTypes[i], null);
        }
        return params;
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // 获取类中所有字段名称，封装为List<String>数据列表
    public static List<String> getAllFieldNames(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

}
