package com.noob.base.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class 工具类
 */
public class ClassUtil {
    /**
     * 根据包名获取其下的类信息
     * @param packageName
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
            classes.addAll(findClasses(file, packageName));
        }
        return classes;
    }

    /**
     * 根据directory、packageName获取其下的类信息
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName)
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
}
