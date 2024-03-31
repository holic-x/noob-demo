package proxy.cglib;

public class UserActionCGlibTest {
    public static void main(String[] args) {
        // 定义代理
        UserActionCGlib userActionCGlib = new UserActionCGlib(new UserAction());
        // 获取代理后的目标对象
        UserAction userAction = (UserAction) userActionCGlib.getProxyInstance();
        // 指定目标方法
        userAction.doSomething();
    }
}
