package com.lljin.support;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author lljin
 * @description 用于封装远程通信的参数
 * @date 2020/6/18 0:34
 */
@Data
public class RemoteRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] args;
    private String[] paramTypes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(String[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    @Override
    public String toString() {
        return "RemoteRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                '}';
    }
}
