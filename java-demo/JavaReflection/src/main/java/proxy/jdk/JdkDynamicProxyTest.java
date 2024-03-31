package proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理测试
 */
public class JdkDynamicProxyTest {


    public static void main(String[] args) {
        // 定义目标对象
        UserServiceImpl impl = new UserServiceImpl();
        // 定义代理对象，装载要代理的目标
        UserProxy userProxy = new UserProxy(impl);

        // 装载代理对象
        UserService userService = (UserService) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), userProxy);

        // 测试代理方法
        userService.addUser();
        userService.updateUser("：hello noob");
    }
}
