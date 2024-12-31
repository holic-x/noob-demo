package com.noob.rpc.proxy.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Javassist 动态代理
 */
public class JavassistDemo2 {

    public static void main(String[] args) throws Exception {
        ProxyFactory factory = new ProxyFactory();

        // 指定父类，ProxyFactory会动态生成继承该父类的子类
        factory.setSuperclass(JavassistTemplate.class);
        // 设置过滤器，判断哪些方法调用需要被拦截
        factory.setFilter(new MethodFilter() {
            public boolean isHandled(Method m) {
                if (m.getName().equals("execute")) {
                    return true;
                }
                return false;
            }
        });

        // 设置拦截处理
        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                System.out.println("前置处理");
                Object result = proceed.invoke(self, args);
                System.out.println("执行结果:" + result);
                System.out.println("后置处理");
                return result;
            }
        });

        // 创建JavassistTemplate的代理类，并创建代理对象
        Class<?> c = factory.createClass();
        JavassistTemplate javassistTemplate = (JavassistTemplate) c.newInstance();
        javassistTemplate.execute(); // 执行execute()方法，会被拦截
        System.out.println(javassistTemplate.getProp());
    }

}
