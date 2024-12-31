package com.noob.rpc.proxy.jdk;

import com.noob.rpc.proxy.designer.Subject;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK 动态代理：将生成的代理类写入文件并输出
 */
public class JdkProxySourceClassExport {

    public static void writeClassToDisk(String path) {
        // 动态代理生成类信息
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy4", new Class[]{Subject.class});
        // 写入本地磁盘
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 动态生成代理类并输出到指定的目录文件
        JdkProxySourceClassExport.writeClassToDisk("D:/$Proxy4.class");
    }
}
