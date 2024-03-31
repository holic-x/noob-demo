package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 构建一个UserProxy代理类，实现InvocationHandler的invoke方法
 */
public class UserProxy implements InvocationHandler {

    // 定义代理对象
    private Object target;

    // 创建构造函数初始化代理对象
    public UserProxy(Object target){
        this.target = target;
    }

    // 实现代理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 自定义业务逻辑实现方法增强
        System.out.println("JDK代理增强......before......");

        // 调用代理对象方法
        Object res = method.invoke(target,args);

        System.out.println("JDK代理增强......after......");

        // 返回代理对象
        return res;
    }
}
