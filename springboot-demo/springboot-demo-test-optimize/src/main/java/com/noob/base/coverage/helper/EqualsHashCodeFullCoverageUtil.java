package com.noob.base.coverage.helper;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

import static com.noob.base.coverage.helper.CustomAssertUtil.assertEquals;
import static com.noob.base.coverage.helper.CustomAssertUtil.assertNotEquals;

// todo
public class EqualsHashCodeFullCoverageUtil<T> {

    private final Supplier<T> instanceSupplier;
    private final List<String> significantFields;
    private final List<String> excludedFields;

    public EqualsHashCodeFullCoverageUtil(Supplier<T> instanceSupplier) {
        this(instanceSupplier, new ArrayList<>(), new ArrayList<>());
    }

    public EqualsHashCodeFullCoverageUtil(Supplier<T> instanceSupplier,
                                          List<String> significantFields,
                                          List<String> excludedFields) {
        this.instanceSupplier = Objects.requireNonNull(instanceSupplier);
        this.significantFields = Objects.requireNonNull(significantFields);
        this.excludedFields = Objects.requireNonNull(excludedFields);
    }
    /**
     * 执行全面的equals和hashCode测试
     */
    public void test() throws Exception {
        testReflexivity();
        testSymmetry();
        testTransitivity();
        testConsistency();
        testNonNullity();
        testHashCodeContract();
        testFieldByField();
        testEdgeCases();
    }

    private void testReflexivity() {
        T x = instanceSupplier.get();
        assertEquals(x, x,
        "自反性失败: 对象应该等于自身");
    }

    private void testSymmetry() throws Exception {
        T x = createInstanceWithDefaultValues();
        T y = createInstanceWithDefaultValues();

        assertEquals(x, y,
        "对称性失败(1)");
        assertEquals(y, x,
        "对称性失败(2)");

        // 修改一个显著字段并测试不对称性
        if (!significantFields.isEmpty()) {
            String fieldName = significantFields.get(0);
            modifyField(y, fieldName);
            assertNotEquals(x, y,
            "对称性失败(3)");
            assertNotEquals(y, x,
            "对称性失败(4)");
        }
    }

    private void testTransitivity() throws Exception {
        if (significantFields.isEmpty()) return;

        String fieldName = significantFields.get(0);

        T x = createInstanceWithDefaultValues();
        T y = createInstanceWithDefaultValues();
        T z = createInstanceWithDefaultValues();

        // x = y = z
        assertEquals(x, y,
        "传递性失败(1)");
        assertEquals(y, z,
        "传递性失败(2)");
        assertEquals(x, z,
        "传递性失败(3)");

        // 修改y，使x != y = z
        modifyField(y, fieldName);
        modifyField(z, fieldName);
        assertNotEquals(x, y,
        "传递性失败(4)");
        assertEquals(y, z,
        "传递性失败(5)");
        assertNotEquals(x, z,
        "传递性失败(6)");
    }

    private void testConsistency() throws Exception {
        T x = createInstanceWithDefaultValues();
        T y = createInstanceWithDefaultValues();

        // 多次调用结果应该一致
        for (int i = 0; i < 5; i++) {
            assertEquals(x, y,
            "一致性失败(equals) - 迭代 " + i);
            assertEquals(x.hashCode(), y.hashCode(),
            "一致性失败(hashCode) - 迭代 " + i);
        }

        if (!significantFields.isEmpty()) {
            String fieldName = significantFields.get(0);
            modifyField(y, fieldName);

            for (int i = 0; i < 5; i++) {
                assertNotEquals(x, y,
                "一致性失败(不等) - 迭代 " + i);
            }
        }
    }

    private void testNonNullity() {
        /*
        T x = instanceSupplier.get();
        assertNotEquals(x, null, "非null性失败: 对象不应等于null");
         */
        // 版本增强：增强NULL值比较
        T x = instanceSupplier.get();
        // 测试对象与null比较
        assertNotEquals(x,
        null,
        "非null性失败: 对象不应等于null");
        // 测试null与对象比较
        assertNotEquals(null, x,
        "非null性失败: null不应等于对象");
    }

    private void testHashCodeContract() throws Exception {
        T x = createInstanceWithDefaultValues();
        T y = createInstanceWithDefaultValues();

        // 如果两个对象相等，hashCode必须相等
        if (x.equals(y)) {
            assertEquals(x.hashCode(), y.hashCode(),
            "hashCode契约失败: 相等对象必须有相同hashCode");
        }
        // 反向不成立: hashCode相同不一定对象相等
        // 但我们可以测试修改一个字段是否影响hashCode
        if (!significantFields.isEmpty()) {
            String fieldName = significantFields.get(0);
            T z = createInstanceWithDefaultValues();
            modifyField(z, fieldName);

            // 如果字段是hashCode计算的一部分，hashCode应该改变
            if (isFieldIncludedInHashCode(fieldName)) {
                assertNotEquals(x.hashCode(), z.hashCode(),
                "hashCode应包含字段: " + fieldName);
            }
        }
    }

    private void testFieldByField() throws Exception {
        if (significantFields.isEmpty()) {
            // 如果没有指定显著字段，测试所有非排除字段
            Field[] fields = instanceSupplier.get().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!excludedFields.contains(field.getName()) &&
                        !Modifier.isStatic(field.getModifiers())) {
                    significantFields.add(field.getName());
                }
            }
        }

        for (String fieldName : significantFields) {
            if (excludedFields.contains(fieldName)) continue;

            T x = createInstanceWithDefaultValues();
            T y = createInstanceWithDefaultValues();

            // 修改一个字段，对象应该不相等
            modifyField(y, fieldName);
            assertNotEquals(x, y,
            "字段 " + fieldName + " 应该影响equals");

            // 如果字段包含在hashCode中，hashCode应该改变
            if (isFieldIncludedInHashCode(fieldName)) {
                assertNotEquals(x.hashCode(), y.hashCode(),
                "字段 " + fieldName + " 应该影响hashCode");
            }
        }
    }

    private void testEdgeCases() throws Exception {
        T x = instanceSupplier.get();

        // 测试与不同类型比较
        assertNotEquals(x,
        "完全不同的类型",
        "类型检查失败");

        // 测试null字段
        if (!significantFields.isEmpty()) {
            String fieldName = significantFields.get(0);
            T withNull = createInstanceWithDefaultValues();
            setField(withNull, fieldName, null);

            T withNonNull = createInstanceWithDefaultValues();
            setField(withNonNull, fieldName, getNonNullValueForField(fieldName));

            // 测试null与non-null比较
            assertNotEquals(withNull, withNonNull,
            "null与non-null比较失败");

            // 测试两个null比较
            T withNull2 = createInstanceWithDefaultValues();
            setField(withNull2, fieldName, null);
            assertEquals(withNull, withNull2,
            "两个null字段比较失败");
        }
    }

    private T createInstanceWithDefaultValues() throws Exception {
        T instance = instanceSupplier.get();
        // 初始化所有字段为非null值
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers()) &&
                    !excludedFields.contains(field.getName())) {
                setField(instance, field.getName(), getNonNullValueForField(field.getName()));
            }
        }
        return instance;
    }

    private void modifyField(T instance, String fieldName) throws Exception {
        Object currentValue = getField(instance, fieldName);
        Object newValue = getModifiedValue(currentValue, instance.getClass().getDeclaredField(fieldName).getType());
        setField(instance, fieldName, newValue);
    }
    /*
    private Object getModifiedValue(Object currentValue, Class<?> fieldType) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return currentValue == null ? 1 : ((Integer) currentValue) + 1;
        } else if (fieldType == long.class || fieldType == Long.class) {
            return currentValue == null ? 1L : ((Long) currentValue) + 1;
        } else if (fieldType == String.class) {
            return currentValue == null ? "modified" : currentValue + "_modified";
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return currentValue == null ? true : !((Boolean) currentValue);
        }

        // tofix 补充（补充List<>\String类型）
        else if (fieldType == List.class) {
            return currentValue == null ? new ArrayList<>() : Arrays.asList("modified_data");
        }else if (fieldType == String[].class) {
            return currentValue == null ? new String[10] : new String[]{"modified_data"};
        }

        else {
            // 对于其他类型，尝试创建新实例
            try {
                return fieldType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return "modified_" + System.currentTimeMillis();
            }
        }
    }

    private Object getNonNullValueForField(String fieldName) throws Exception {
        Field field = instanceSupplier.get().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();

        if (type == int.class || type == Integer.class) return 1;
        if (type == long.class || type == Long.class) return 1L;
        if (type == double.class || type == Double.class) return 1.0;
        if (type == float.class || type == Float.class) return 1.0f;
        if (type == boolean.class || type == Boolean.class) return true;
        if (type == String.class) return "test";
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants.length > 0 ? constants[0] : null;
        }

        // tofix 补充列表、数组类型验证(兼容方法)
        if (type == List.class) return new ArrayList<>();
        if (type == String[].class) return new String[10];

        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

     */
    // ============================================================


    protected Object getModifiedValue(Object original, Class<?> type) {
        // 处理集合类型
        if (List.class.isAssignableFrom(type)) {
            if (original == null) {
                return Collections.singletonList("modified");
            }
            List<?> originalList = (List<?>) original;
            if (originalList.isEmpty()) {
                return Collections.singletonList("modified");
            }
            List<Object> modified = new ArrayList<>(originalList);
            modified.add("modified_" + System.currentTimeMillis());
            return modified;
        } else if (Set.class.isAssignableFrom(type)) {
            if (original == null) {
                return Collections.singleton("modified");
            }
            Set<?> originalSet = (Set<?>) original;
            if (originalSet.isEmpty()) {
                return Collections.singleton("modified");
            }
            Set<Object> modified = new HashSet<>(originalSet);
            modified.add("modified_" + System.currentTimeMillis());
            return modified;
        } else if (Map.class.isAssignableFrom(type)) {
            if (original == null) {
                return Collections.singletonMap("modified",
                "value");
            }
            Map<?, ?> originalMap = (Map<?, ?>) original;
            if (originalMap.isEmpty()) {
                return Collections.singletonMap("modified",
                "value");
            }
            Map<Object, Object> modified = new HashMap<>(originalMap);
            modified.put("modified_key_" + System.currentTimeMillis(),
            "modified_value");
            return modified;
        }
        // 处理数组类型
        else if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            if (original == null) {
                return Array.newInstance(componentType,
                1);
            }
            int length = Array.getLength(original);
            Object modified = Array.newInstance(componentType, length + 1);
            System.arraycopy(original,
            0, modified,
            0, length);
            Array.set(modified, length, getDefaultValue(componentType));
            return modified;
        }
        // 原始类型和基本对象类型
        else if (type == int.class || type == Integer.class) {
            return original == null ? 1 : ((Number) original).intValue() + 1;
        } else if (type == long.class || type == Long.class) {
            return original == null ? 1L : ((Number) original).longValue() + 1;
        } else if (type == double.class || type == Double.class) {
            return original == null ? 1.0 : ((Number) original).doubleValue() + 1.0;
        } else if (type == float.class || type == Float.class) {
            return original == null ? 1.0f : ((Number) original).floatValue() + 1.0f;
        } else if (type == boolean.class || type == Boolean.class) {
            // return original == null ? true : !((Boolean) original); // 调整兼容适配
            // 处理null情况：对于boolean基本类型返回false，对于Boolean对象返回Boolean.TRUE
            if (original == null) {
                return type == boolean.class ? false : Boolean.TRUE;
            }
            // 处理Boolean对象
            if (original instanceof Boolean) {
                return !(Boolean) original;
            }
            /*
            // 处理字符串"true"/"false"（可选扩展）
            if (original instanceof String) {
                String s = ((String) original).toLowerCase();
                if ("true".equals(s)) return type == boolean.class ? false : Boolean.FALSE;
                if ("false".equals(s)) return type == boolean.class ? true : Boolean.TRUE;
            }
             */
            // 其他类型无法转换
            throw new IllegalArgumentException("Cannot convert to boolean: " + original.getClass());
        } else if (type == byte.class || type == Byte.class) {
            // return original == null ? new Byte("0") : ((Number) original).byteValue() + 1;
            return original == null ? new Byte("0") : (byte) ((byte) original + 1);
        } else if (type == short.class || type == Short.class) {
            return original == null ? Short.valueOf((short) 0) : (short) (((Number) original).shortValue() + 1); // fix 类型转化，补充兼容Short
        } else if (type == char.class || type == Character.class) {
            // return original == null ? Character.valueOf('\0') : Character.valueOf((char) (c + 1)); // fix 类型转化，补充兼容Character
            // 处理null情况：返回默认字符'\0'（空字符）
            if (original == null) {
                return type == char.class ? '\0' : Character.valueOf('\0');
            }
            // 处理Character对象
            if (original instanceof Character) {
                char c = (Character) original;
                return type == char.class ? (char) (c + 1) : Character.valueOf((char) (c + 1));
            }
            // 处理数字类型（如Integer 65对应'A'）
            if (original instanceof Number) {
                char c = (char) ((Number) original).intValue();
                return type == char.class ? (char) (c + 1) : Character.valueOf((char) (c + 1));
            }
            // 处理字符串（第一个字符）
            if (original instanceof String && !((String) original).isEmpty()) {
                char c = ((String) original).charAt(0);
                return type == char.class ? (char) (c + 1) : Character.valueOf((char) (c + 1));
            }
            // 其他类型无法转换
            throw new IllegalArgumentException("Unsupported conversion to char: " + original.getClass());
        } else if (type == String.class) {
            return original == null ? "modified": original + "_modified";
        } else if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            if (constants == null || constants.length == 0) return null;
            return original == null ? constants[
                0
            ] :
                    constants[(Arrays.asList(constants).indexOf(original) + 1) % constants.length
            ];
        }
        // 扩展补充其他常用类型的处理，兼容（tofix：需确保在原基础上修改，否则无法覆盖equals、hashCode概念，此处是临时修复方案）
        // 处理 java.util.Date
        else if (type == Date.class) {
            return new Date(); // 当前时间
        }
        // 处理 java.sql.Date
        else if (type == java.sql.Date.class) {
            return new java.sql.Date(System.currentTimeMillis());
        }
        // 处理 java.util.Calendar
        else if (type == Calendar.class) {
            return Calendar.getInstance();
        }
        // 处理Java 8+时间类型
        else if (type == java.time.LocalDate.class) {
            return java.time.LocalDate.now();
        } else if (type == java.time.LocalDateTime.class) {
            return java.time.LocalDateTime.now();
        } else if (type == java.time.LocalTime.class) {
            return java.time.LocalTime.now();
        }
        // 处理其他常用Java类型
        else if (type == BigDecimal.class) {
            return BigDecimal.ZERO;
        } else if (type == UUID.class) {
            return UUID.randomUUID();
        }
        // 其他对象类型（不满足上述条件的类型会进入到此分支）
        else {
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return "modified_" + System.currentTimeMillis();
            }
        }
    }
    /*
    private Object getDefaultValue(Class<?> type) {
        if (type == int.class || type == Integer.class) return 0;
        if (type == long.class || type == Long.class) return 0L;
        if (type == double.class || type == Double.class) return 0.0;
        if (type == float.class || type == Float.class) return 0.0f;
        if (type == boolean.class || type == Boolean.class) return false;
        if (type == char.class || type == Character.class) return '\0';
        if (type == byte.class || type == Byte.class) return (byte)0;
        if (type == short.class || type == Short.class) return (short)0;
        return null;
    }
     */

    protected Object getNonNullValueForField(String fieldName) throws Exception {
        // Field field = getField(fieldName);
        Field field = instanceSupplier.get().getClass().getDeclaredField(fieldName);
        Class<?> type = field.getType();

        // 处理集合类型
        if (List.class.isAssignableFrom(type)) {
            // return Collections.singletonList(getDefaultValueForGenericType(field));
            // 兼容不同的子类场景，避免类型转化异常导致字段值设置失败
            if (type == ArrayList.class) {
                // return new ArrayList<>();
                ArrayList list = new ArrayList();
                list.add(getDefaultValueForGenericType(field));
                return list;
            }
            /*
            else if (type == LinkedList.class) {
                LinkedList list = new LinkedList<>();
                list.add(getDefaultValueForGenericType(field));
                return list;
            }
             */
            // 默认返回不可变空列表
            return Collections.emptyList();
        } else if (Set.class.isAssignableFrom(type)) {
            return Collections.singleton(getDefaultValueForGenericType(field));
        } else if (Map.class.isAssignableFrom(type)) {
            return Collections.singletonMap(
                    "defaultKey",
                    getDefaultValueForGenericType(field,
            1)
            );
        }
        // 处理数组类型
        else if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            Object array = Array.newInstance(componentType,
            1);
            Array.set(array,
            0, getDefaultValue(componentType));
            return array;
        }
        // 处理 java.util.Date
        else if (type == Date.class) {
            return new Date(); // 当前时间
        }
        // 处理 java.sql.Date
        else if (type == java.sql.Date.class) {
            return new java.sql.Date(System.currentTimeMillis());
        }
        // 处理 java.util.Calendar
        else if (type == Calendar.class) {
            return Calendar.getInstance();
        }
        // 处理Java 8+时间类型
        else if (type == java.time.LocalDate.class) {
            return java.time.LocalDate.now();
        } else if (type == java.time.LocalDateTime.class) {
            return java.time.LocalDateTime.now();
        } else if (type == java.time.LocalTime.class) {
            return java.time.LocalTime.now();
        }
        // 处理其他常用Java类型
        else if (type == BigDecimal.class) {
            return BigDecimal.ONE;
        } else if (type == UUID.class) {
            return UUID.randomUUID();
        }
        // 处理原始类型和包装类型
        else if (type == int.class || type == Integer.class) return 1;
        else if (type == long.class || type == Long.class) return 1L;
        else if (type == double.class || type == Double.class) return 1.0;
        else if (type == float.class || type == Float.class) return 1.0f;
        else if (type == boolean.class || type == Boolean.class) return true;
        else if (type == char.class || type == Character.class) return 'a';
        else if (type == byte.class || type == Byte.class) return (byte) 1;
        else if (type == short.class || type == Short.class) return (short) 1;
        else if (type == String.class) return "default";

            // 处理枚举类型
        else if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants.length > 0 ? constants[
                0
            ] : null;
        }
        /* tofix
        // 处理嵌套对象类型
        if (!type.isPrimitive() && !type.isEnum() && !type.isArray()
                && !Collection.class.isAssignableFrom(type)
                && !Map.class.isAssignableFrom(type)) {
            try {
                Object instance = type.getDeclaredConstructor().newInstance();
                // 递归初始化嵌套对象的字段
                for (Field f : type.getDeclaredFields()) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true);
                        f.set(instance, getNonNullValueForField(f.getName()));
                    }
                }
                return instance;
            } catch (Exception e) {
                // 如果无法实例化，返回一个带标记的对象
                return "nonNull_" + type.getSimpleName() + "_" + System.currentTimeMillis();
            }
        }
         */
        // 自定义对象类型
        else {
            try {
                // 尝试通过无参构造函数创建实例
                Object instance = type.getDeclaredConstructor().newInstance();

                // 递归初始化所有字段
                for (Field f : type.getDeclaredFields()) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true);
                        // f.set(instance, getNonNullValueForField(f.getName())); 此处不能直接用getNonNullValueForField，否则又会回到当前父类的set导致异常

                        Object newValue = InvokeHelperUtil.generateNonNullValue(f.getType());
                        InvokeHelperUtil.setFieldValue(f, instance, newValue);
                    }
                }
                return instance;
            } catch (Exception e) {
                // 如果无法实例化，返回一个带标记的对象
                System.out.println("实例化异常，请确认参数类型等配置是否兼容");
                return "nonNull_" + type.getSimpleName() + "_" + System.currentTimeMillis();
            }
        }
    }
    /**
     * 获取泛型类型的默认值（用于集合元素）
     */
    private Object getDefaultValueForGenericType(Field field) {
        return getDefaultValueForGenericType(field,
        0);
    }
    /**
     * @param position 0表示获取元素类型，1表示获取Map的value类型
     */
    protected Object getDefaultValueForGenericType(Field field, int position) {
        try {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                Type[] typeArgs = ((ParameterizedType) genericType).getActualTypeArguments();
                if (typeArgs.length > position) {
                    if (typeArgs[position
                    ] instanceof Class) {
                        return getDefaultValue((Class<?>) typeArgs[position
                        ]);
                    }
                }
            }
            return "defaultValue";
        } catch (Exception e) {
            return "defaultValue";
        }
    }
    /**
     * 获取基本类型的默认值
     */
    protected Object getDefaultValue(Class<?> type) {
        if (type == int.class || type == Integer.class) return 0;
        if (type == long.class || type == Long.class) return 0L;
        if (type == double.class || type == Double.class) return 0.0;
        if (type == float.class || type == Float.class) return 0.0f;
        if (type == boolean.class || type == Boolean.class) return false;
        if (type == char.class || type == Character.class) return '\0';
        if (type == byte.class || type == Byte.class) return (byte) 0;
        if (type == short.class || type == Short.class) return (short) 0;
        if (type == String.class) return "";
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants != null && constants.length > 0 ? constants[
                0
            ] : null;
        }
        return null;
    }
    // ============================================================


    protected boolean isFieldIncludedInHashCode(String fieldName) {
        // 如果没有指定显著字段，假设所有非排除字段都包含在hashCode中
        return significantFields.isEmpty() || significantFields.contains(fieldName);
    }
    // 反射辅助方法
    protected Object getField(T instance, String fieldName) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

    private void setField(T instance, String fieldName, Object value) throws Exception {
        /*
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
         */
        // fix 兼容final字段处理，不设置final字段值
        Field field = instance.getClass().getDeclaredField(fieldName);
        if (isFinal(field)) {
            return; // 跳过当前字段(例如序列化的serialVersionUID字段)
        }
        // 兼容boolean类型不能被设置为null的场景（例如一些基本类型不能任意设置，需要手动过滤，避免类型转化异常）
        // 跳过 boolean 基本类型被设置为null的场景，或者自行手动设置
        if (field.getType() == boolean.class) {
            return;
        }
        /*
        // 处理其他可接受 null 的字段
        if (field.getType().isPrimitive()) {
            // 处理其他基本类型（byte/short/int等）
        } else {
            // 处理对象类型（可设置为 null）
            field.setAccessible(true);
            field.set(targetObj, null);
        }
         */
        // 其他类型处理
        field.setAccessible(true);
        field.set(instance, value);
    }
    // 判断是否为final字段
    private static boolean isFinal(Field field) {
        return (field.getModifiers() & Modifier.FINAL) != 0;
    }
}