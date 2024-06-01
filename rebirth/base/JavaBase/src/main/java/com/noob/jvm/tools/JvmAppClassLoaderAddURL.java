package com.noob.jvm.tools;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) throws Exception {
        String appPath = "file:/e:/test/";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("Hello"); // 效果等同于Class.forName("Hello").newInstance()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
