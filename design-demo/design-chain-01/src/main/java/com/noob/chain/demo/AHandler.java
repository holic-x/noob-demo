package com.noob.chain.demo;

/**
 * 具体处理类：A
 * 实现相应的业务逻辑并传递给下一个节点进行处理
 */
public class AHandler extends BaseRequestHandler{
    @Override
    public void handle(String request) {
        // A 处理自己的业务逻辑
        System.out.println("A 处理完成");
        // 将请求转发给下一个节点（如果节点存在则执行handler逻辑）
        if(this.next!=null){
            this.next.handle(request);
        }
    }
}
