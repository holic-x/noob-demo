package com.noob.demo.createObj;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * 创建对象
 */
public class CreateObjTest {


    public static void main(String[] args) {
        CreateObjTest test = new CreateObjTest();
        test.test1();
        test.test2();
        test.test3();
        test.test4();
        test.test5();
        test.test6();
    }

    // 方式1：new
    void test1() {
        User user = new User();
        System.out.println(user);
    }

    // 方式2：克隆一个对象(Object.clone())
    @SneakyThrows
    void test2() {
        Employee emp1 = new Employee();
        Employee emp2 = (Employee) emp1.clone();
        System.out.println(emp1 + "-----" + emp2);
    }

    // 3.1反射机制（通过类派发一个对象）
    @SneakyThrows
    void test3() {
        User user = User.class.newInstance();
        System.out.println(user);
    }

    // 3.2反射机制（动态加载一个对象）
    @SneakyThrows
    void test4() {
        User user = (User) Class.forName("com.noob.demo.createObj.User").newInstance();
        System.out.println(user);
    }

    // 3.3反射机制（通过类的构造函数构造一个对象）
    @SneakyThrows
    void test5() {
        User user = User.class.getConstructor().newInstance();
        System.out.println(user);
    }

    // 4.反序列化一个对象
    @SneakyThrows
    void test6() {
        Admin admin = new Admin();
        admin.setName("俺是一个管理员");
        // 序列化一个管理员(将Java对象转化为字符串序列)
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("com.noob.demo.Admin"));
        objectOutputStream.writeObject(admin);
        objectOutputStream.close();

        // 反序列化（将对应的对象流转化为Java对象）
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("com.noob.demo.Admin"));
        Admin adminObj = (Admin) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(adminObj.getName());
    }
}
