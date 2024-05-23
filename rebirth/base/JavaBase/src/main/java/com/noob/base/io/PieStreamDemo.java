package com.noob.base.io;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 分别定义两个线程用于管道流通信测试
 * 此处线程的创建通过实现Runnable接口方式进行构建
 */

// 发送方
 class Send implements Runnable {

     // 发送方：定义输出流
    private PipedOutputStream pos = null;

    // 实例化输出流
    Send(){
        pos = new PipedOutputStream();
    }

    // 得到此线程的管道输出流
    public PipedOutputStream getPos() {
        return pos;
    }

    @Override
    public void run() {
        String msg = "hello world";

        // 此处异常处理并不规范，仅仅为了简化代码，体现逻辑代码实现
        try {
            // 输出数据
            pos.write(msg.getBytes());
            // 关闭输出流
            pos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// 接受方
class Receive implements Runnable {

     // 接收方：定义输入流
    private PipedInputStream pis = null;

    // 初始化输入流
    Receive(){
        pis = new PipedInputStream();
    }

    // 提供获取此线程的输入流的方法
    public PipedInputStream getPis() {
        return pis;
    }

    @Override
    public void run() {

        // 定义每次读取的字节数
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            // 此处异常处理并不规范，仅仅为了简化代码，体现逻辑代码实现
            len = pis.read(buf);

            // 读取完毕，关闭输入流
            pis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 取出读取的内容
        System.out.println("接收的内容：" + new String(buf, 0, len));
    }
}

/**
 * 管道流(可以进行两个线程直接的通信)
 */
public class PieStreamDemo {

    public static void main(String[] args) throws IOException {
        // 分别创建两个线程对象
        Send send = new Send();
        Receive receive = new Receive();

        // 使用connect方法连通管道
        send.getPos().connect(receive.getPis());

        // 分别启动线程（先启动），通过new Thead创建线程并调用start方法启动
        new Thread(send).start();
        new Thread(receive).start();
    }
}
