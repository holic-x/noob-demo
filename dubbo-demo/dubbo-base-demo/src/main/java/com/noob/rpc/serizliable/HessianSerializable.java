package com.noob.rpc.serizliable;

import com.noob.rpc.serizliable.model.Person;

import java.io.*;

/**
 * 原生JDK序列化方式
 */
public class HessianSerializable {

    // 序列化
    public static void serialize()throws Exception{
        // 1.定义对象
        Person person = new Person("001","小红");

        // 2.借助ObjectOutputStream输出为二进制流
        FileOutputStream fos = new FileOutputStream(new File("D:\\Desktop\\test\\rpc-person.dat"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();

        // 关闭文件流
        oos.close();
        fos.close();
    }

    // 反序列化
    public static void unserialize()throws Exception{

        // 1.读取二进制流文件
        FileInputStream fis = new FileInputStream(new File("D:\\Desktop\\test\\rpc-person.dat"));
        // 2.反序列化
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person person = (Person) ois.readObject();
        System.out.println(person);

    }

    public static void main(String[] args) throws Exception {
        HessianSerializable.serialize();
        HessianSerializable.unserialize();

    }


}
