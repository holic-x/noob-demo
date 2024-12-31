package com.noob.rpc.proxy.designer;

// 真正的执行对象
public class RealSubject implements Subject {
    @Override
    public void operation() {
        System.out.println("i am real subject ......");
    }
}
