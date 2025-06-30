package com.noob.base.coverage.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class 工具类：检索指定包的所有类
 */
public class ClassUtil {
    /**
     * 根据报名获取类信息
     *
     * @param packageName 包名
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static List<Class<?>> getClasses(String packageName)
            throws ClassNotFoundException, IOException {

        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File file = new File(resource.getFile());
            // classes.addAll(findClasses(file, packageName)); 原始版本
            classes.addAll(findClasses(file, packageName, true)); // 实际执行加载class（测试的时候指定为false不进行实际的类加载）
        }
        return classes;
    }

    /**
     * 根据directory、packageName获取类信息
     *
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    /*
    public static List<Class<?>> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file,
                        packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName
                        + "."
                        + file.getName().substring(0,
                        file.getName().length() - 6)));
            }
        }
        return classes;
    }
     */
    public static List<Class<?>> findClasses(File directory, String packageName, boolean loadClasses)
            throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName(), loadClasses));
            } else if (file.getName().endsWith(".class")) {
                if (loadClasses) {
                    classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
                } else {
                    // 模拟类加载，只添加类名
                    classes.add(null); // 或者使用其他方式表示类
                }
            }
        }
        return classes;
    }
}
