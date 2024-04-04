package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * JDK动态代理实现Demo
 */
public class JdkDynamicProxyDemo {

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(), // 类加载器
                new Class[]{Subject.class},     // 被代理类实现的接口
                new SimpleInvocationHandler(realSubject) // 调用处理器
        );
        proxySubject.doAction();
    }

    static class SimpleInvocationHandler implements InvocationHandler {
        private Object target;

        public SimpleInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before action at: " + new Date());
            Object result = method.invoke(target, args);
            System.out.println("After action at: " + new Date());
            return result;
        }
    }
}

interface Subject {
    void doAction();
}

class RealSubject implements Subject {
    @Override
    public void doAction() {
        System.out.println("Action performed.");
    }
}