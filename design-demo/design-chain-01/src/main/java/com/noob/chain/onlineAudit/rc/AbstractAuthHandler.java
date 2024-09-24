package com.noob.chain.onlineAudit.rc;


public abstract class AbstractAuthHandler {

    protected AbstractAuthHandler nextHandler;

    // 设置下一节点
    public void setNextHandler(AbstractAuthHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 定义处理方法
    public abstract void handle(AuthInfo authInfo);

}
