package com.noob.rpc.proxy.cglib;

/**
 * CGLib 动态代理
 */
public class CGLibProxyDemo {

    public static void main(String[] args) {
        // 创建代理
        CGLibProxy proxy = new CGLibProxy();

        // 生成Target的代理对象
        Target target = (Target)proxy.getProxy(Target.class);

        // 调用代理对象的method()方法
        String result = target.operation("test");
        System.out.println(result);

        /**
         * 输出：
         * 前置处理......
         * test
         * 后置处理......
         * do sth:test
         */
    }
}
