package com.lljin;

import com.lljin.bootstrap.ServerBootstrap;
import com.lljin.support.RequestRoute;

/**
 * @author lljin
 * @description TODO
 * @date 2020/6/20 12:13
 */
public class ServiceProvider {
    public static void main(String[] args) {
        RequestRoute.getInstance().register(new TestServiceAImpl());
        RequestRoute.getInstance().register(new TestServiceBImpl());
        ServerBootstrap bootstrap = new ServerBootstrap(8080);
        bootstrap.start();
    }
}
