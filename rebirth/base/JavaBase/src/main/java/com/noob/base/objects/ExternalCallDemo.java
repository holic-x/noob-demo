package com.noob.base.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 外部调用示例：通过Java唤起外部可执行命令
 */
public class ExternalCallDemo {


    // Runtime.exec()
    public void exam_runtime() throws Exception {
        // 1.执行命令
        Process process = Runtime.getRuntime().exec("notepad.exe");

        // 2.等待命令执行完成
        int exitCode = process.waitFor();

        // 3.检查执行结果(exitCode == 0 则说明执行成功)
        System.out.println("Exit Code: " + exitCode);
    }

    // ProcessBuilder
    public void exam_processbuilder() throws Exception {
        // 创建ProcessBuilder并设置命令
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "dir");
        builder.redirectErrorStream(true); // 合并错误流到输出流
        builder.directory(new File("D:\\")); // 设置工作目录

        // 启动进程
        Process process = builder.start();

        // 读取进程的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK")); // 使用GBK编码兼容windows环境，避免控制台输出中文乱码
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令完成
        int exitCode = process.waitFor();
        System.out.println("Exit Code: " + exitCode);
    }


    public static void main(String[] args) throws Exception {
        ExternalCallDemo demo = new ExternalCallDemo();
        // demo.exam_runtime();
        demo.exam_processbuilder();
    }


}
