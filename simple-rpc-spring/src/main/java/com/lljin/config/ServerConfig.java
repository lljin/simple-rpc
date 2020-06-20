package com.lljin.config;

import com.lljin.bootstrap.ServerBootstrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lljin
 * @description 配置类
 * @date 2020/6/20 14:47
 */
@Configuration
public class ServerConfig {

    @Bean(initMethod = "start")
    ServerBootstrap serverBootstrap(@Value("${rpc.server.port}") int port) {
        return new ServerBootstrap(port);
    }
}
