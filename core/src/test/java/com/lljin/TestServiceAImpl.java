package com.lljin;

import java.util.Arrays;
import java.util.List;

/**
 * @author lljin
 * @description TODO
 * @date 2020/6/20 12:12
 */
public class TestServiceAImpl implements TestServiceA {
    @Override
    public List<String> getAddressesBy(String name) {
        return Arrays.asList("123123123","安徽省");
    }

    @Override
    public int getAgeByName(String name) {
        return 18;
    }
}
