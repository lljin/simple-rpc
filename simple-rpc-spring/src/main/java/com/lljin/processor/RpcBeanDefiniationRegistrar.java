package com.lljin.processor;

import com.lljin.annotation.EnableSimpleRpcClient;
import com.lljin.scanner.RpcBeanScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author lljin
 * @description 扫描指定包下的类
 * @date 2020/6/20 14:26
 */

public class RpcBeanDefiniationRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableSimpleRpcClient.class.getName());
        String basePackage = (String) annotationAttributes.get("basePackage");
        RpcBeanScanner rpcBeanScanner = new RpcBeanScanner(registry);
        rpcBeanScanner.registerFilters();
        int scan = rpcBeanScanner.scan(basePackage);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    }
}
