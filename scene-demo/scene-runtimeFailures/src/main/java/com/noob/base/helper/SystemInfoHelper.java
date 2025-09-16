package com.noob.base.helper;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 计算机配置信息获取辅助工具类
 */
public class SystemInfoHelper {

    public static void main(String[] args) {
        System.out.println("======= 系统信息 =======");

        // 获取CPU核心数
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU逻辑核心数: " + coreCount);

        // 获取操作系统信息
        Properties props = System.getProperties();
        System.out.println("\n======= 操作系统信息 =======");
        System.out.println("操作系统名称: " + props.getProperty("os.name"));
        System.out.println("操作系统版本: " + props.getProperty("os.version"));
        System.out.println("操作系统架构: " + props.getProperty("os.arch"));

        // 获取Java环境信息
        System.out.println("\n======= Java环境信息 =======");
        System.out.println("Java版本: " + props.getProperty("java.version"));
        System.out.println("Java安装路径: " + props.getProperty("java.home"));
        System.out.println("Java供应商: " + props.getProperty("java.vendor"));

        // 获取内存信息
        System.out.println("\n======= 内存信息 =======");
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024); // MB
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);     // MB
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);   // MB
        System.out.println("JVM总内存: " + totalMemory + " MB");
        System.out.println("JVM最大可用内存: " + maxMemory + " MB");
        System.out.println("JVM空闲内存: " + freeMemory + " MB");

        // 获取网络信息
        try {
            System.out.println("\n======= 网络信息 =======");
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("主机名: " + localHost.getHostName());
            System.out.println("本地IP地址: " + localHost.getHostAddress());

            // 获取所有网络接口信息
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                System.out.println("\n网络接口: " + ni.getDisplayName());

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    System.out.println("  IP地址: " + addr.getHostAddress());
                }
            }
        } catch (UnknownHostException | SocketException e) {
            System.out.println("获取网络信息出错: " + e.getMessage());
        }

        // 获取用户信息
        System.out.println("\n======= 用户信息 =======");
        System.out.println("用户名: " + props.getProperty("user.name"));
        System.out.println("用户主目录: " + props.getProperty("user.home"));
        System.out.println("用户当前工作目录: " + props.getProperty("user.dir"));
    }

}
