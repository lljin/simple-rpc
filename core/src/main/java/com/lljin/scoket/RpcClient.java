package com.lljin.scoket;

import com.lljin.support.ClientMessage;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author lljin
 * 客户端接口
 */
public interface RpcClient {
    /**
     * 发起远程调用
     * @param message 发送的消息的字节数组
     * @return 服务器端返回数据
     */
    byte[] remoteCall(byte[] message);
}
