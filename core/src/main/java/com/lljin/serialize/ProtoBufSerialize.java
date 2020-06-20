package com.lljin.serialize;

import com.lljin.support.RemoteRequest;

/**
 * @author lljin
 * @description 基于protobuf 的序列化 反序列化
 * @date 2020/6/20 8:24
 */
public class ProtoBufSerialize implements Serialize {
    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public Object deSerialize(byte[] bytes) {
        return null;
    }
}
