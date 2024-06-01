package com.noob.base.serialize;


import java.io.*;

// 定义传输对象实现Serializable接口
class Person implements Serializable {
    // 性别枚举定义
    enum Sex{
        MALE,FEMALE
    }
    private static final long serialVersionUID = 1L;
    private String name = null;
    private Integer age = null;
    private transient Sex sex;

    public Person() { }

    public Person(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", sex=" + sex + '}';
    }
}

// 基于JDK实现序列化和反序列化（序列化解析器）
class JdkSerializer{
    // 序列化方法定义
    public static void serialize(String filename) throws IOException {
        File f = new File(filename); // 定义保存路径
        OutputStream out = new FileOutputStream(f); // 文件输出流
        ObjectOutputStream oos = new ObjectOutputStream(out); // 对象输出流
        oos.writeObject(new Person("Jack", 30, Person.Sex.MALE)); // 保存对象
        oos.close();
        out.close();
    }

    // 反序列化方法定义
    public static void deserialize(String filename) throws IOException, ClassNotFoundException {
        File f = new File(filename); // 定义保存路径
        InputStream in = new FileInputStream(f); // 文件输入流
        ObjectInputStream ois = new ObjectInputStream(in); // 对象输入流
        Object obj = ois.readObject(); // 读取对象
        ois.close();
        in.close();
        System.out.println(obj);
    }
}

/**
 * JDK序列化demo
 */
public class JdkSerializeDemo {

    public static void main(String[] args) throws Exception {
        // 测试基于JDK实现的序列化和反序列化机制
        final String filename = "/Users/holic-x/Desktop/temp/text.dat";// windows d:/text.dat
        JdkSerializer.serialize(filename);
        JdkSerializer.deserialize(filename);
    }

}
