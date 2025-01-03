package com.noob.rpc.simple.serialization;

/**
 * 对象工厂方法：根据指定类型返回序列化机制
 */
public class SerializationFactory {

    public static Serialization get(byte type) {
        switch (type & 0x7) {
            case 0x0:
                return new HessianSerialization();
            default:
                return new HessianSerialization();
        }
    }
}
