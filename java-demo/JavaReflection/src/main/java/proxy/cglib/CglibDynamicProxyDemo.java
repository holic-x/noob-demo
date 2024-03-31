package proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 基于CGLIB实现动态代理
 */
public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        final RealTarget realSubject = new RealTarget();
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before action at: " + new Date());
                Object result = method.invoke(realSubject, args);
                System.out.println("After action at: " + new Date());
                return result;
            }
        };

        RealTarget proxySubject = (RealTarget) Enhancer.create(realSubject.getClass(), methodInterceptor);
        proxySubject.doAction();
    }
}

/**
 * 要代理的目标对象
 */
class RealTarget {
    public void doAction() {
        System.out.println("Action performed.");
    }
}
