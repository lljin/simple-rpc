package com.lljin.proxy;

import com.lljin.scoket.RpcBIOClient;
import com.lljin.scoket.RpcClient;
import com.lljin.serialize.JdkSerialize;
import com.lljin.serialize.Serialize;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lljin
 * @description 用于生成目标对象, 封装远程调用
 * @date 2020/6/20 11:37
 */
public class RemoteCallProxy<T>{
    private static final Map<Class<?>, Object> proxyInstance = new ConcurrentHashMap<>();
    private RpcClient rpcClient;
    private Serialize serialize = new JdkSerialize();

    private final Class<T> proxyClass;

    public RemoteCallProxy(Class<T> proxyClass) {
        this.proxyClass = proxyClass;
    }

    public RemoteCallProxy(RpcClient rpcClient, Serialize serialize, Class<T> proxyClass) {
        this.rpcClient = rpcClient;
        this.serialize = serialize;
        this.proxyClass = proxyClass;
    }
    @SuppressWarnings("unchecked")
    public T getProxy() {
        T o = (T) proxyInstance.get(proxyClass);
        if (o != null) {
            return o;
        }
        Object proxyObject = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{proxyClass}
                , new RemoteCallInvocationHandler(proxyClass, rpcClient, serialize));
        proxyInstance.put(proxyClass, proxyObject);
        return (T) proxyObject;
    }

    public RpcClient getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public Serialize getSerialize() {
        return serialize;
    }

    public void setSerialize(Serialize serialize) {
        this.serialize = serialize;
    }

    public Class<T> getProxyClass() {
        return proxyClass;
    }
}
