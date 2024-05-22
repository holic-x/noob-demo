package com.noob.base.serialize;

import java.io.*;

// 定义传输对象实现Serializable接口
class User implements Externalizable {

    transient private Integer age = null;
    // 其他内容略

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = in.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException { }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { }

}

public class JdkExternalizeDemo {
//    public static void main(String[] args) throws Exception {
//        // 测试基于JDK实现的序列化和反序列化机制
//        final String filename = "/Users/holic-x/Desktop/temp/user.dat";// windows d:/user.dat
//        JdkSerializer.serialize(filename);
//        JdkSerializer.deserialize(filename);
//    }
}
