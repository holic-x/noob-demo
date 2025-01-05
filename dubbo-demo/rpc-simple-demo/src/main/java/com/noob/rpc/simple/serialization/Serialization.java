package com.noob.rpc.simple.serialization;

import java.io.IOException;

/**
 * 序列化接口定义
 */
public interface Serialization {
    // 序列化
    <T> byte[] serialize(T obj) throws IOException;

    // 反序列化
    <T> T deserialize(byte[] data, Class<T> clz) throws IOException;
}