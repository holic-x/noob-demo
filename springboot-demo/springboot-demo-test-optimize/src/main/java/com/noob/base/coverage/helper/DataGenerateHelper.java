package com.noob.base.coverage.helper;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

public class DataGenerateHelper {

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
        map.put(Byte.class, (byte) 1);
        map.put(Short.class, (short) 1);
        map.put(Integer.class, 1);
        map.put(Long.class, 1L);
        map.put(Float.class, 1.0f);
        map.put(Double.class, 1.0);
        map.put(Character.class, 'a');

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

        // 4. 处理自定义对象（尝试无参构造）
        if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
            try {
                Constructor<?> constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor.newInstance();
            } catch (Exception e) {
                // 构造失败返回null
                return null;
            }
        }

        // 5. 默认返回null
        return null;
    }


}
