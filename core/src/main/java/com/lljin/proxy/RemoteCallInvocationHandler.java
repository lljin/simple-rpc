package com.lljin.proxy;

import com.lljin.scoket.RpcClient;
import com.lljin.serialize.Serialize;
import com.lljin.support.RemoteRequest;
import com.lljin.support.ServerResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lljin
 * @description 远程调用
 * @date 2020/6/20 11:41
 */
public class RemoteCallInvocationHandler implements InvocationHandler {
    private final Class<?> targetClass;
    private final RpcClient rpcClient;
    private final Serialize serialize;

    public RemoteCallInvocationHandler(Class<?> targetClass, RpcClient rpcClient, Serialize serialize) {
        this.targetClass = targetClass;
        this.rpcClient = rpcClient;
        this.serialize = serialize;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RemoteRequest remoteRequest = new RemoteRequest();
        remoteRequest.setClassName(targetClass.getName());
        remoteRequest.setMethodName(method.getName());
        remoteRequest.setArgs(args);
        remoteRequest.setParamTypes(this.resolveParamTypes(method));
        System.out.println(remoteRequest);
        if (method.getDeclaringClass() == Object.class){
            return null;
        }
        byte[] result = rpcClient.remoteCall(serialize.serialize(remoteRequest));
        Object o = serialize.deSerialize(result);
        if (o instanceof ServerResponse){
            String exception = ((ServerResponse) o).getException();
            if (exception != null){
                throw  new RuntimeException("服务端抛出异常-->"+exception);
            }
            return ((ServerResponse) o).getResult();
        }
        return o;
    }

    private String[] resolveParamTypes(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] paramTypes = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            String name = parameterTypes[i].getName();
            paramTypes[i] = name;
        }
        return paramTypes;
    }
}
