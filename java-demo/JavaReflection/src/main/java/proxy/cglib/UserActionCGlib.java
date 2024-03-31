package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 定义UserAction代理类，基于CGLIB实现，实现MethodInterceptor
 */
public class UserActionCGlib implements MethodInterceptor {

    // 定义代理目标
    private Object target;

    // 构造函数初始化代理目标
    public UserActionCGlib(Object target) {
        this.target = target;
    }

    // 返回一个代理对象: 是 target 对象的代理对象
    public Object getProxyInstance() {
        // 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类对象，即代理对象
        return enhancer.create();
    }

    // 实现代理方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("===CGLIB增强 before===");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("===CGLIB增强 after===");
        return result;
    }
}
