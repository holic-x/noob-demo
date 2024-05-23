package com.noob.base.io;


import java.io.*;

// 定义对象（实现序列化接口并设定版本）
class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}


/**
 * 对象字节流（一般用于序列化场景，例如基于JDK提供的字节流实现对象和二进制数据的序列化）
 */
public class ObjectStreamDemo {

    private final static String FILEPATH = "testRoot/io/personObj.dat";

    // 序列化：将对象=》二进制数据（写入文件）
    public static void writeObject(String filePath, Person person) throws IOException {
        // 1.绑定文件
         File file = new File(filePath);
         // 2.将文件绑定到输出流
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
         // 3.写入文件内容(将对象信息写入文件)
        oos.writeObject(person);
        // 4.写入完毕，关闭流
        oos.close();
    }

    // 反序列化：将二进制数据=》对象
    public static Person readObject(String filePath) throws Exception {
        // 1.绑定文件
        File file = new File(filePath);
        // 2.将文件绑定到输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 3.读取文件内容
        Object obj = ois.readObject();
        // 4.读取完毕，关闭流
        ois.close();
        // 返回读取的对象信息
         return (Person) obj;
    }

    public static void main(String[] args) throws Exception {
        // 测试序列化和反序列化
         Person person = new Person("noob");
         // 写入对象到文件
        writeObject(FILEPATH,person);
        // 从文件读取对象信息
        Person readObj = readObject(FILEPATH);
        System.out.println(readObj);
    }

}
