package com.lljin.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lljin
 * @description 请求路由
 * @date 2020/6/20 9:34
 */
public class RequestRoute {
    private static volatile RequestRoute INSTANCE;
    private static final Map<String, Route> routeMap = new ConcurrentHashMap<>();

    public static RequestRoute getInstance(){
        if (INSTANCE == null){
            synchronized (RequestRoute.class){
                if (INSTANCE == null){
                    INSTANCE = new RequestRoute();
                }
            }
        }
        return INSTANCE;
    }

    public void register(Object object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        Route route;
        for (Class<?> anInterface : interfaces) {
             route = new Route();
            route.exposedBean = object;
            String name = anInterface.getName();
            route.aClass = anInterface;
            route.methods = anInterface.getMethods();
            routeMap.put(name,route);
        }
    }

    public Object handlerRequest(RemoteRequest remoteRequest) throws InvocationTargetException, IllegalAccessException {
        Route route = routeMap.get(remoteRequest.getClassName());
        Method[] methods = route.methods;
        for (Method method : methods) {
            if (method.getName().equals(remoteRequest.getMethodName()) && isParamTypesMatch(method, remoteRequest.getParamTypes())) {
                return method.invoke(route.exposedBean, remoteRequest.getArgs());
            }
        }
        throw new RuntimeException("找不到对应方法");
    }

    private boolean isParamTypesMatch(Method method, String[] paramTypes) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != paramTypes.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].getName().equals(paramTypes[i])) {
                return false;
            }
        }
        return true;
    }

    private static class Route {
        Class<?> aClass;
        Object exposedBean;
        Method[] methods;
    }
}
