package com.lljin;

/**
 * @author lljin
 * @description TODO
 * @date 2020/6/20 12:19
 */
public class TestServiceBImpl implements TestServiceB {
    @Override
    public int plus(int a, int b) {
        return a+b;
    }

    @Override
    public Integer getAgeByName(String name) {
        return 180;
    }
}
