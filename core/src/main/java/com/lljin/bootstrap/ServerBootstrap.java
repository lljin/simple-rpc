package com.lljin.bootstrap;

import com.lljin.scoket.RpcBIOServer;
import com.lljin.serialize.JdkSerialize;
import com.lljin.serialize.Serialize;
import com.lljin.support.RemoteRequest;
import com.lljin.support.RequestRoute;
import com.lljin.support.ServerResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lljin
 * @description 服务器端启动
 * @date 2020/6/20 9:33
 */
@Slf4j
public class ServerBootstrap {
    private final int port;
    private Serialize serialize = new JdkSerialize();

    public ServerBootstrap(int port) {
        this.port = port;
    }

    public void start() {
        try {
            log.info("启动服务端!");
            RpcBIOServer server = new RpcBIOServer(port);
            server.onMessage(clientMessage -> {
                byte[] replay = null;
                RemoteRequest remoteRequest = null;
                try {
                    remoteRequest = (RemoteRequest) serialize.deSerialize(clientMessage.getMessage());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("序列化失败!");
                    e.printStackTrace();
                }

                Object result = null;
                if (remoteRequest != null){
                    try {
                        result = RequestRoute.getInstance().handlerRequest(remoteRequest);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("发起远程调用失败!");
                        e.printStackTrace();
                    }
                }
                if (result != null) {
                    try {
                        replay = serialize.serialize(new ServerResponse(result));
                    } catch (IOException e) {
                        System.out.println("反序列化失败!");
                    }
                }
                try {
                    if (replay == null) {
                        replay = serialize.serialize(new ServerResponse("服务端异常"));
                    }
                    server.replay(replay, clientMessage.getSocket());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("服务端启动失败!");
            e.printStackTrace();
        }
    }

    public void setSerialize(Serialize serialize) {
        this.serialize = serialize;
    }
}
