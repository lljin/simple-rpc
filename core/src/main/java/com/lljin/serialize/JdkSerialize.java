package com.lljin.serialize;

import com.lljin.support.RemoteRequest;

import java.io.*;

/**
 * @author lljin
 * @description 基于jdk实现的序列化反序列化
 * @date 2020/6/20 8:31
 */
public class JdkSerialize implements Serialize {
    @Override
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(obj);
        return out.toByteArray();
    }

    @Override
    public Object deSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        return objectInputStream.readObject();
    }
}
