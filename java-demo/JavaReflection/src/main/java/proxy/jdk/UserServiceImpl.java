package proxy.jdk;

/**
 * UserService实现类，实现接口方法
 */
public class UserServiceImpl implements UserService{
    @Override
    public void addUser() {
        System.out.println("添加用户");
    }

    @Override
    public void updateUser(String username) {
        System.out.println("修改用户："+username);
    }
}
