package com.noob.chain.demo;

/**
 * 责任链客户端测试
 */
public class HandlerClient {
    public static void main(String[] args) {
        // 1.创建对象节点（有哪些对象要执行任务的）
        AHandler aHandler = new AHandler();
        BHandler bHandler = new BHandler();
        CHandler cHandler = new CHandler();

        // 2.构建节点的责任链关系(此处设定为A->B->C)
        aHandler.setNext(bHandler);
        bHandler.setNext(cHandler);

        // 3.执行业务逻辑（启动节点，链路可以从任一节点开始）
        aHandler.handle("链路待处理数据"); // 从A节点开始
        System.out.println("-------");
        cHandler.handle("执行ING");// 从C节点开始

    }
}
