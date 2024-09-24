package com.noob.chain.demo;


/**
 * 基础请求处理器:实现接口，定义公共的处理逻辑以及下一个节点的设定
 */
public abstract class BaseRequestHandler implements RequestHandler{

    protected RequestHandler next;

    // 设置下一个节点
    public void setNext(RequestHandler next) {
        this.next = next;
    }

}
