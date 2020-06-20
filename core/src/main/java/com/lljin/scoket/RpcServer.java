package com.lljin.scoket;

import com.lljin.support.ClientMessage;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * @author lljin
 * 远程通信接口
 */
public interface RpcServer {
    /**
     * 用于注册监听客户端消息, 客户端来消息后会调用回调函数
     * @param consumer 回调逻辑
     */
    void onMessage(Consumer<ClientMessage> consumer);

    /**
     * 回复客户端消息
     * @param message 要回复数据的字节数组
     * @param destination 回复的目标对象, 可以是socket 或者socketchannel
     */
    void replay(byte[] message, Object destination) throws IOException;
}
