package com.lljin.serialize;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author lljin
 * 序列化接口
 */
public interface Serialize {
    /**
     * 序列化
     * @param obj 需要序列化的对象
     * @return 序列化后的二进制数组
     */
    byte[] serialize(Object obj) throws IOException;

    /**
     * 反序列化
     * @param bytes 二进制数组
     * @return 反序列化后的对象
     */
    Object deSerialize(byte[] bytes) throws IOException, ClassNotFoundException;
}
