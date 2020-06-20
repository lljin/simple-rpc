package com.lljin.bootstrap;

import com.lljin.scoket.RpcBIOClient;
import com.lljin.scoket.RpcClient;

/**
 * @author lljin
 * @description 客户端启动
 * @date 2020/6/20 9:33
 */
public class ClientBootstrap {
    private final String host;
    private final int port;
    private RpcClient rpcClient;

    public ClientBootstrap(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RpcClient start() {
        RpcClient client = new RpcBIOClient(host,port);
        this.rpcClient = client;
        return client;
    }

    public RpcClient getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }
}
