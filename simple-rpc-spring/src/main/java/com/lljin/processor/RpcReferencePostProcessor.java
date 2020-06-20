package com.lljin.processor;

import com.lljin.annotation.RpcReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;

/**
 * @author lljin
 * @description 自动注入
 * @date 2020/6/20 15:09
 */
public class RpcReferencePostProcessor implements BeanPostProcessor, ApplicationContextAware {
    ApplicationContext context;
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            /*if (field.isAnnotationPresent(RpcReference.class)){
                Object bean1 = context.getBean(field.getType());
                field.setAccessible(true);
                try {
                    field.set(bean,bean1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }*/
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
