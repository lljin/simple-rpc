package com.lljin.processor;

import com.lljin.annotation.RpcService;
import com.lljin.support.RequestRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author lljin
 * @description 用于注册服务
 * @date 2020/6/20 15:43
 */
@Slf4j
public class RpcServiceRegistry implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ( bean.getClass().isAnnotationPresent(RpcService.class)){
            RequestRoute.getInstance().register(bean);
            log.info("regist a bean to route-{}",beanName);
        }
        return bean;
    }
}
