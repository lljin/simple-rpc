package com.lljin.support;

import lombok.Data;

import java.net.Socket;
import java.util.Arrays;

/**
 * @author lljin
 * @description 用于保存客户端socket 与 消息的映射
 * @date 2020/6/20 10:10
 */
@Data
public class ClientMessage {
    private Object socket;
    private byte[] message;

    public ClientMessage(Socket socket, byte[] message) {
        this.socket = socket;
        this.message = message;
    }

    public Object getSocket() {
        return socket;
    }

    public void setSocket(Object socket) {
        this.socket = socket;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClientMessage{" +
                "socket=" + socket +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
