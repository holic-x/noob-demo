package com.noob.base.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 定义公共接口，声明方法
interface Item{
    void hello(String name);
    String bye();
}

// 定义类实现接口
 class RealItem implements Item{

    @Override
    public void hello(String name) {
        System.out.println("hello " + name);
    }

    @Override
    public String bye() {
        System.out.println("bye");
        return "bye";
    }
}

// 定义动态代理类（实现InvocationHandler接口）
class DynamicProxy implements InvocationHandler {

    // 定义要代理的真实对象
    private Item item;

    // 初始化代理对象
    public DynamicProxy(Item item) {
        this.item = item;
    }

    // 代理方法定义
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前后可以自定义一些操作
        System.out.println("before proxy");
        System.out.println("call method" + method);
        // 代理真实对象(调代理对象调用真实的方法时，会自动跳转到代理对象关联的handler对象的invoke方法进行调用)
        Object obj = method.invoke(item, args);
        System.out.println("after proxy");
        // 最终返回代理结果
        return obj;
    }
}

/**
 * 动态代理
 */
public class DynamicProxyDemo {

    public static void main(String[] args) {
        // 要代理的真实对象
        RealItem realItem = new RealItem();
        // 传入要代理的对象，通过该真实对象来调用方法
        InvocationHandler handler = new DynamicProxy(realItem);
        // 通过反射创建代理对象
        Item item = (Item)Proxy.newProxyInstance(handler.getClass().getClassLoader(),realItem.getClass().getInterfaces(),handler );
        System.out.println(item.getClass().getName());
        item.hello("noob");
        item.bye();
    }
}
