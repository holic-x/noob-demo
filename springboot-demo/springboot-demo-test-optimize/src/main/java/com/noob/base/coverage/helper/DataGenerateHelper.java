package com.noob.base.coverage.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

@Slf4j
public class DataGenerateHelper {

    private static final Random random = new Random();

    // 默认值映射数据集：从数据辅助生成工具类中统一维护
    public static final Map<Class<?>, Object> DEFAULT_VALUES;

    /**
     * 初始化默认映射数据集
     * @return
     */
    static {
        Map<Class<?>, Object> map = new HashMap<>();

        // ========== 基本类型和包装类 ==========
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
        map.put(Byte.class, (byte) 1); // new Byte("123")
        map.put(Short.class, (short) 1); // new Short("1")
        map.put(Integer.class, 1); // new Integer("1")
        map.put(Long.class, 1L); // new Long("1")
        map.put(Float.class, 1.0f);// new Float("1.0")
        map.put(Double.class, 1.0); // new Double("1.0")
        map.put(Character.class, 'a'); // new Character('a')

        // ========== 字符串和字符序列 ==========
        map.put(String.class, "test");
        map.put(CharSequence.class, "test");
        map.put(StringBuffer.class, new StringBuffer("test"));
        map.put(StringBuilder.class, new StringBuilder("test"));

        // ========== 集合框架 ==========
        // List
        map.put(List.class, Collections.emptyList());
        map.put(ArrayList.class, new ArrayList<>());
        map.put(LinkedList.class, new LinkedList<>());
        map.put(CopyOnWriteArrayList.class, new CopyOnWriteArrayList<>());

        // Set
        map.put(Set.class, Collections.emptySet());
        map.put(HashSet.class, new HashSet<>());
        map.put(LinkedHashSet.class, new LinkedHashSet<>());
        map.put(TreeSet.class, new TreeSet<>());
        map.put(CopyOnWriteArraySet.class, new CopyOnWriteArraySet<>());

        // Map
        map.put(Map.class, Collections.emptyMap());
        map.put(HashMap.class, new HashMap<>());
        map.put(LinkedHashMap.class, new LinkedHashMap<>());
        map.put(TreeMap.class, new TreeMap<>());
        map.put(ConcurrentHashMap.class, new ConcurrentHashMap<>());
        map.put(WeakHashMap.class, new WeakHashMap<>());

        // Queue/Deque
        map.put(Queue.class, new LinkedList<>());
        map.put(Deque.class, new LinkedList<>());
        map.put(PriorityQueue.class, new PriorityQueue<>());
        map.put(ArrayDeque.class, new ArrayDeque<>());
        map.put(BlockingQueue.class, new LinkedBlockingQueue<>());
        map.put(BlockingDeque.class, new LinkedBlockingDeque<>());
        map.put(TransferQueue.class, new LinkedTransferQueue<>());

        // ========== 数组类型 ==========
        // 基本类型数组
        map.put(boolean[].class, new boolean[0]);
        map.put(byte[].class, new byte[0]);
        map.put(short[].class, new short[0]);
        map.put(int[].class, new int[0]);
        map.put(long[].class, new long[0]);
        map.put(float[].class, new float[0]);
        map.put(double[].class, new double[0]);
        map.put(char[].class, new char[0]);

        // 包装类型数组
        map.put(Boolean[].class, new Boolean[0]);
        map.put(Byte[].class, new Byte[0]);
        map.put(Short[].class, new Short[0]);
        map.put(Integer[].class, new Integer[0]);
        map.put(Long[].class, new Long[0]);
        map.put(Float[].class, new Float[0]);
        map.put(Double[].class, new Double[0]);
        map.put(Character[].class, new Character[0]);

        // 其他常见数组
        map.put(String[].class, new String[0]);
        map.put(Object[].class, new Object[0]);

        // ========== 日期和时间 ==========
        map.put(java.util.Date.class, new java.util.Date());
        map.put(java.sql.Date.class, new java.sql.Date(System.currentTimeMillis()));
        map.put(java.sql.Timestamp.class, new java.sql.Timestamp(System.currentTimeMillis()));
        map.put(java.time.LocalDate.class, java.time.LocalDate.now());
        map.put(java.time.LocalTime.class, java.time.LocalTime.now());
        map.put(java.time.LocalDateTime.class, java.time.LocalDateTime.now());
        map.put(java.time.ZonedDateTime.class, java.time.ZonedDateTime.now());
        map.put(java.time.Instant.class, java.time.Instant.now());
        map.put(java.time.Duration.class, java.time.Duration.ofSeconds(1));
        map.put(java.time.Period.class, java.time.Period.ofDays(1));
        map.put(java.time.Year.class, java.time.Year.now());
        map.put(java.time.YearMonth.class, java.time.YearMonth.now());
        map.put(java.time.MonthDay.class, java.time.MonthDay.now());

        // ========== 数学和货币 ==========
        map.put(java.math.BigDecimal.class, BigDecimal.ONE);
        map.put(java.math.BigInteger.class, BigInteger.ONE);
        map.put(java.util.Currency.class, java.util.Currency.getInstance("USD"));
        map.put(java.util.Random.class, new java.util.Random());

        // ========== IO和NIO ==========
        map.put(java.io.File.class, new java.io.File(""));
        map.put(java.nio.file.Path.class, java.nio.file.Paths.get(""));
        map.put(java.io.InputStream.class, new ByteArrayInputStream(new byte[0]));
        map.put(java.io.OutputStream.class, new ByteArrayOutputStream());
        map.put(java.io.Reader.class, new StringReader(""));
        map.put(java.io.Writer.class, new StringWriter());
        map.put(java.nio.ByteBuffer.class, ByteBuffer.allocate(0));

        // ========== 网络 ==========
        map.put(java.net.URL.class, createSafeURL());
        map.put(java.net.URI.class, createSafeURI());
        map.put(java.net.InetAddress.class, InetAddress.getLoopbackAddress());
        try {
            map.put(java.net.Inet4Address.class, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            System.out.println("java.net.Inet4Address.class 初始化映射失败");
            throw new RuntimeException(e);
        }
        try {
            map.put(java.net.Inet6Address.class, InetAddress.getByName("::1"));
        } catch (UnknownHostException e) {
            System.out.println("java.net.Inet6Address.class 初始化映射失败");
            throw new RuntimeException(e);
        }

        // ========== 并发 ==========
        map.put(java.util.concurrent.atomic.AtomicInteger.class, new AtomicInteger(0));
        map.put(java.util.concurrent.atomic.AtomicLong.class, new AtomicLong(0));
        map.put(java.util.concurrent.atomic.AtomicBoolean.class, new AtomicBoolean(false));
        map.put(java.util.concurrent.atomic.AtomicReference.class, new AtomicReference<>());
        map.put(java.util.concurrent.CountDownLatch.class, new CountDownLatch(0));
        map.put(java.util.concurrent.Semaphore.class, new Semaphore(1));
        map.put(java.util.concurrent.locks.ReentrantLock.class, new ReentrantLock());
        map.put(java.util.concurrent.locks.ReentrantReadWriteLock.class, new ReentrantReadWriteLock());

        // ========== 其他常用类 ==========
        map.put(java.util.UUID.class, UUID.randomUUID());
        map.put(java.util.Optional.class, Optional.empty());
        map.put(java.util.OptionalInt.class, OptionalInt.empty());
        map.put(java.util.OptionalLong.class, OptionalLong.empty());
        map.put(java.util.OptionalDouble.class, OptionalDouble.empty());
        map.put(java.util.regex.Pattern.class, Pattern.compile(""));
        map.put(java.util.Locale.class, Locale.getDefault());
        map.put(java.util.TimeZone.class, TimeZone.getDefault());
        map.put(java.util.Calendar.class, Calendar.getInstance());
        map.put(java.util.zip.ZipFile.class, null); // 需要特殊处理

        // ========== 异常类 ==========
        map.put(Exception.class, new Exception("Test exception"));
        map.put(RuntimeException.class, new RuntimeException("Test runtime exception"));
        map.put(IOException.class, new IOException("Test IO exception"));
        map.put(NullPointerException.class, new NullPointerException("Test NPE"));

        // ========== 枚举处理 ==========
        // 注意：枚举需要特殊处理，这里只放占位符
        map.put(Enum.class, null); // 实际使用时需要动态获取枚举的第一个值

        // 构建数据集
        DEFAULT_VALUES = Collections.unmodifiableMap(map);
    }


    public static Map<Class<?>, Object> initDefaultValues() {
        // 返回构建的数据集
        return DEFAULT_VALUES;
    }

    // ====================== 辅助处理方法 ======================

    private static URL createSafeURL() {
        try {
            return new URL("http://example.com");
        } catch (Exception e) {
            return null;
        }
    }

    private static URI createSafeURI() {
        try {
            return new URI("http://example.com");
        } catch (Exception e) {
            return null;
        }
    }

    // ====================== 增强数据处理兼容方法 ======================

    /**
     * 获取类型的默认值，支持枚举、数组和自定义对象的动态处理
     * 此处所谓宽容版的概念指的是采用日志或者允许返回null的方式处理一些无法兼容的类型处理场景（放行处理，通过观察日志以处理一些暂未兼容的场景）
     * 而严格版的概念则是强校验场景，如果遇到无法兼容的场景则抛出异常（可能导致业务逻辑覆盖处理的中断）
     */
    public static Object getDefaultValue(Class<?> type) {
        // 1. 检查是否在DEFAULT_VALUES中已定义
        if (DEFAULT_VALUES.containsKey(type)) {
            return DEFAULT_VALUES.get(type);
        }

        // 2. 处理枚举类型
        if (type.isEnum()) {
            Object[] enumConstants = type.getEnumConstants();
            return enumConstants != null && enumConstants.length > 0 ? enumConstants[0] : null;
        }

        // 3. 处理数组类型（动态生成）
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }

        // 4. 处理自定义对象/其他对象类型（尝试无参构造）
        if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
            try {
                Constructor<?> constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            } catch (Exception e) {
                log.error("Cannot generate default value for type: {}", type, e);
                // throw new RuntimeException("【当前类型无法兼容处理，需进一步优化方法逻辑】Cannot generate default value for type: " + type, e);
                // 构造失败返回null
                return null;
            }
        }

        // 5. 其余未兼容的场景默认返回null或者抛出异常以进一步确认兼容度
        // throw new RuntimeException("【当前类型无法兼容处理，需进一步优化方法逻辑】Cannot generate default value for type: " + type);
        return null;
    }

    /**
     * generateNonNullValue 严格版校验处理 (to clear)
     */
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
     * 生成与原值不同的值（兼容所有 getDefaultValue 支持的类型）
     *
     * @param original 原始值（可为null）
     * @param type     目标类型（当original为null时使用）
     * @return 必定返回与原值不同的非null值（无法生成时抛出异常）
     */
    public static <T> T generateDifferentValue(T original, Class<?> type) {
        Class<?> targetType = original != null ? original.getClass() : type;

        // 1. 处理null原始值
        if (original == null) {
            T defaultValue = (T) getDefaultValue(targetType);
            return generateDifferentValue(defaultValue, targetType); // 递归生成差异值
        }

        // 2. 基本类型及包装类
        if (targetType == Integer.class || targetType == int.class) {
            return (T) (Integer) ((Integer) original + 1);
        } else if (targetType == Long.class || targetType == long.class) {
            return (T) (Long) ((Long) original + 1L);
        } else if (targetType == Double.class || targetType == double.class) {
            return (T) (Double) ((Double) original + 1.0);
        } else if (targetType == Float.class || targetType == float.class) {
            return (T) (Float) ((Float) original + 1.0f);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            return (T) (Boolean) !((Boolean) original);
        } else if (targetType == Character.class || targetType == char.class) {
            return (T) (Character) ((char) (((Character) original) + 1));
        } else if (targetType == Byte.class || targetType == byte.class) {
            return (T) (Byte) (byte) (((Byte) original) + 1);

            /**
             * 兼容完善处理：可处理max场景，避免溢出问题
             */
            /*
            // 处理 Byte 类型，避免溢出
            byte originalByte = (Byte) original;
            if (originalByte < Byte.MAX_VALUE) { // 防止 +1 后超出 Byte 范围
                return (T) (Byte) (byte) (originalByte + 1);
            } else {
                // 如果已经是最大值，可以返回原值或抛出异常（根据业务需求）
                return original; // 或者 throw new ArithmeticException("Byte overflow");
            }
             */

        } else if (targetType == Short.class || targetType == short.class) {
            return (T) (Short) (short) (((Short) original) + 1);

            /**
             * 兼容完善处理：max 场景兼容，避免溢出问题
             */
            /*
            short originalShort = (Short) original;
            if (originalShort < Short.MAX_VALUE) { // 检查是否溢出
                return (T) (Short) (short) (originalShort + 1);
            } else {
                return original; // 达到最大值时返回原值（或抛异常）
            }
             */
        }

        // 3. 字符串类型
        if (targetType == String.class) {
            return (T) (original + "_modified");
        }

        // 4. 枚举类型（切换到下一个枚举值）
        if (targetType.isEnum()) {
            Object[] constants = targetType.getEnumConstants();
            int index = (Arrays.asList(constants).indexOf(original) + 1) % constants.length;
            return (T) constants[index];
        }

        // 5. 数组类型（长度±1或修改首个元素）
        if (targetType.isArray()) {
            Class<?> componentType = original.getClass().getComponentType();
            // 获取原有数组长度
            int length = Array.getLength(original);
            // 构建新数组（新数组长度随机 +/- 1）
            int newLength = length + (random.nextBoolean() ? 1 : -1);
            Object newArray = Array.newInstance(targetType.getComponentType(), Math.max(newLength, 1));

            // 元素复制
            System.arraycopy(original, 0, newArray, 0, Math.min(length, newLength));

            // 如果增加了长度，填充新元素
            if (newLength > length) {
                Array.set(newArray, length, generateNonNullValue(componentType));
            }

            // 返回构建的新数组
            return (T) newArray;
        }

        // 6. 集合类型（List/Set/Queue）
        if (Collection.class.isAssignableFrom(targetType)) {
            Collection<?> originalCollection = (Collection<?>) original;
            Collection<Object> newCollection = createNewCollection(targetType);
            newCollection.addAll(originalCollection);

            // 此处设定如果集合为空则添加元素，如果集合不为空则移除元素
            if (newCollection.isEmpty()) {
                newCollection.add(generateNonNullValue(targetType.getComponentType()));
            } else {
                newCollection.remove(newCollection.iterator().next());
            }

            /**
             * 方案2：可以设定一个策略，例如集合为空的场景可生成一个泛型元素，如果集合不为空则通过概率调配来决定元素的增删
             */
            /*
            if (newCollection.isEmpty()) {
                // 生成一个泛型元素
                newCollection.add(generateGenericElement(type, 0));
            } else {
                // 修改策略：50%概率添加元素，50%概率删除元素
                if (random.nextBoolean()) {
                    newCollection.add(generateGenericElement(type, 0));
                } else {
                    newCollection.remove(newCollection.size() - 1);
                }
            }
             */

            // 返回更新后的集合
            return (T) newCollection;
        }

        // 7. Map类型（修改或添加条目）
        if (Map.class.isAssignableFrom(targetType)) {
            Map<?, ?> originalMap = (Map<?, ?>) original;
            Map<Object, Object> newMap = createNewMap(targetType);
            newMap.putAll(originalMap);

            if (newMap.isEmpty()) {
                newMap.put("key", generateNonNullValue(targetType.getComponentType()));
            } else {
                Map.Entry<?, ?> entry = newMap.entrySet().iterator().next();
                newMap.put(entry.getKey(), generateDifferentValue(entry.getValue(), entry.getValue().getClass()));
            }
            return (T) newMap;
        }

        // 8. 日期时间类型（JSR-310）
        if (original instanceof Temporal) {
            if (original instanceof LocalDate) {
                return (T) ((LocalDate) original).plusDays(1);
            } else if (original instanceof LocalDateTime) {
                return (T) ((LocalDateTime) original).plusHours(1);
            }
            // 其他时间类型处理...
        }

        // 9. 其他情况：自定义POJO/适用于简单的POJO对象 （反射生成新实例并修改首个字段）
        try {
            Object newInstance = targetType.getDeclaredConstructor().newInstance();
            Field[] fields = targetType.getDeclaredFields();
            if (fields.length > 0) {
                Field firstField = fields[0];
                firstField.setAccessible(true);
                Object fieldValue = firstField.get(original);
                firstField.set(newInstance, generateDifferentValue(fieldValue, firstField.getType()));
            }
            return (T) newInstance;
        } catch (Exception e) {
            // 如果处理异常则抛出RuntimeException，自行兼容处理
            throw new IllegalStateException("Failed to generate different value for type: " + targetType, e);
        }
    }

    // --------------------------------------------------------- 辅助方法 ------------------------------------------------------------

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

    // 根据不同Collection集合类型创建默认集合数据
    private static Collection<Object> createNewCollection(Class<?> collectionType) {
        // List 类型处理
        if (List.class.isAssignableFrom(collectionType)) {
            // return new ArrayList<>(); // return Collections.emptyList();
            // 兼容不同的子类场景，避免类型转化异常导致字段值设置失败
            if (collectionType == ArrayList.class) {
                return new ArrayList<>();
            } else if (collectionType == LinkedList.class) {
                return new LinkedList<>();
            }
            // 默认返回不可变空列表
            return Collections.emptyList();
        } else if (Set.class.isAssignableFrom(collectionType)) {
            // 兼容不同的子类场景，避免类型转化异常导致字段值设置失败


            // 默认返回不可变空列表
            return Collections.emptySet(); // return new HashSet<>();
        }

        // 其他集合类型，默认处理为List场景
        return new ArrayList<>(); // 默认List
    }

    // 根据不同的Map集合类型创建默认集合数据
    private static Map<Object, Object> createNewMap(Class<?> mapType) {
        if (HashMap.class.isAssignableFrom(mapType)) {
            return new HashMap<>();
        } else if (LinkedHashMap.class.isAssignableFrom(mapType)) {
            return new LinkedHashMap<>();
        }
        // return new HashMap<>(); // 默认HashMap

        // 默认返回不可变空列表
        return Collections.emptyMap();
    }

}
