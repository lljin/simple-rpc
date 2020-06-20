package com.lljin;

import java.util.List;

/**
 * @author lljin
 * @description 测试类
 * @date 2020/6/20 12:08
 */
public interface TestServiceA {
    List<String> getAddressesBy(String name);

    int getAgeByName(String name);
}
