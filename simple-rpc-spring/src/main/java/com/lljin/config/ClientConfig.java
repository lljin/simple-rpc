package com.lljin.config;

import com.lljin.bootstrap.ClientBootstrap;
import com.lljin.scoket.RpcClient;
import com.lljin.serialize.JdkSerialize;
import com.lljin.serialize.Serialize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author lljin
 * @description 配置类
 * @date 2020/6/20 14:47
 */
@Configuration
public class ClientConfig {
    @Value("${rpc.server.host}")
    private String host;
    @Value("${rpc.server.port}")
    private int port;

    @Bean
    RpcClient rpcClient() {
        return new ClientBootstrap(host, port).start();
    }

    @Bean
    Serialize serialize(){
        return new JdkSerialize();
    }
}
