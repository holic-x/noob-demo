package com.noob.rpc.simple.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 基于Hessian的序列化机制实现
 */
public class HessianSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(os);
        hessianOutput.writeObject(obj);
        return os.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clz) throws IOException {
        Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(data));
        return (T) input.readObject(clz);
    }
}