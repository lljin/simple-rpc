package com.lljin.proxy;

import com.lljin.scoket.RpcClient;
import com.lljin.serialize.Serialize;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author lljin
 * @description
 * @date 2020/6/20 15:13
 */
public class RpcProxyFactoryBean<T> extends RemoteCallProxy<T> implements FactoryBean<T>{


    public RpcProxyFactoryBean(RpcClient rpcClient, Serialize serialize, Class<T> proxyClass) {
        super(rpcClient, serialize, proxyClass);
    }

    public RpcProxyFactoryBean(Class<T> proxyClass) {
        super(proxyClass);
    }

    @Override
    public T getObject() throws Exception {
        return getProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return this.getProxyClass();
    }
}
