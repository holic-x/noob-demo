package com.noob.classloader;

import java.io.IOException;
import java.io.InputStream;

// 类加载器Demo
public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {

        // 1.自定义类加载器
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                    throw new ClassNotFoundException(name,e);
                }
            }
        };

        // 2.利用自定义类加载器加载MyUser类，生成一个对象实例selfLoadUser
//        Object selfLoadUser = classLoader.loadClass("MyUser").newInstance(); //  MyUser (wrong name: com/noob/classloader/MyUser)
        Object selfLoadUser = classLoader.loadClass("com.noob.classloader.MyUser").newInstance();
        System.out.println(selfLoadUser.getClass());

        // 3.判断selfLoadUser是否为MyUser的实例（此处的MyUser是系统加载器加载的）
        System.out.println(selfLoadUser instanceof MyUser);
    }

}
