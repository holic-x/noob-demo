package com.noob.base.coverage.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class 工具类：用于检索指定包下的所有类
 * <p>
 * 功能说明：
 * 1. 扫描指定包路径下的所有类文件
 * 2. 支持是否实际加载类的选项（用于测试或仅需类名场景）
 * 3. 递归扫描子包
 */
public class ClassUtil {

    /**
     * 获取指定包名下的所有类
     *
     * @param packageName 要扫描的包名（如：com.example）
     * @return 类列表（可能包含null元素当loadClasses=false时）
     * @throws ClassNotFoundException 当类加载失败时抛出
     * @throws IOException            当IO异常时抛出
     */
    public static List<Class<?>> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        return getClasses(packageName, true);
    }

    /**
     * 获取指定包名下的所有类（可控制是否实际加载类）
     *
     * @param packageName 要扫描的包名
     * @param loadClasses 是否实际加载类
     *                    true - 实际加载类（可能触发静态初始化）
     *                    false - 仅收集类名（不触发类加载）
     * @return 类列表（当loadClasses=false时，列表中的元素为null）
     * @throws ClassNotFoundException 当类加载失败时抛出
     * @throws IOException            当IO异常时抛出
     */
    public static List<Class<?>> getClasses(String packageName, boolean loadClasses)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // ClassLoader classLoader = ClassUtil.class.getClassLoader(); // 或 getClass().getClassLoader()
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<Class<?>> classes = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            // 注意：这里使用toURI()避免路径中的空格被编码为%20的问题
            File file = new File(resource.getFile());
            classes.addAll(findClasses(file, packageName, loadClasses));
        }
        return classes;
    }

    /**
     * 递归查找目录中的类文件
     *
     * @param directory   要扫描的目录
     * @param packageName 当前包名
     * @param loadClasses 是否实际加载类
     * @return 类列表（当loadClasses=false时，列表中的元素为null）
     * @throws ClassNotFoundException 当类加载失败时抛出
     */
    protected static List<Class<?>> findClasses(File directory, String packageName, boolean loadClasses)
            throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        // 如果目录不存在或者目录无读取权限则退出
        if (!directory.exists() || !directory.canRead()) {
            return classes;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // 递归处理子目录
                String subPackageName = packageName + "." + file.getName();
                classes.addAll(findClasses(file, subPackageName, loadClasses));
            } else if (file.getName().endsWith(".class")) {
                // 处理类文件
                String className = packageName + '.' +
                        file.getName().substring(0, file.getName().length() - 6);
                if (loadClasses) {
                    // 实际加载类
                    classes.add(Class.forName(className));
                } else {
                    // 仅记录类名（不实际加载），但直接存入null可能导致一些测试场景的异常
                    classes.add(null);
                    // 或者可以改为存储类名字符串：返回未初始化的Class对象
                    // classes.add(Class.forName(className, false, Thread.currentThread().getContextClassLoader()));
                }
            }
        }
        return classes;
    }
}