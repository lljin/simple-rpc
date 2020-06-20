package com.lljin;

import com.lljin.bootstrap.ClientBootstrap;
import com.lljin.proxy.RemoteCallProxy;
import com.lljin.scoket.RpcClient;
import com.lljin.serialize.JdkSerialize;
import com.lljin.serialize.Serialize;

import java.util.List;

/**
 * @author lljin
 * @description TODO
 * @date 2020/6/20 12:20
 */
public class ServiceConsumer {
    public static void main(String[] args) {
        RpcClient rpcClient = new ClientBootstrap("localhost",8080).start();
        Serialize serialize = new JdkSerialize();
        RemoteCallProxy<TestServiceA> remoteCallProxy = new RemoteCallProxy<>(rpcClient,serialize,TestServiceA.class);
        TestServiceA serviceA = remoteCallProxy.getProxy();
        List<String> address = serviceA.getAddressesBy("lljin");
        System.out.println(address);
        int age = serviceA.getAgeByName("lljin");
        System.out.println(age);
        RemoteCallProxy<TestServiceB> remoteCallProxy1 = new RemoteCallProxy<>(rpcClient,serialize,TestServiceB.class);
        TestServiceB serviceB = remoteCallProxy1.getProxy();
        int plus = serviceB.plus(1, 2);
        System.out.println(plus);
    }
}
