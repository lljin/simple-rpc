package com.lljin.support;

import java.io.Serializable;

/**
 * @author lljin
 * @description server端返回信息
 * @date 2020/6/20 10:23
 */
public class ServerResponse implements Serializable {
    Object result;
    String exception;

    public ServerResponse(String exception) {
        this.exception = exception;
    }

    public ServerResponse(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
