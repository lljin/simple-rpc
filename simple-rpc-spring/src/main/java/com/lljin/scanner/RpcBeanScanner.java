package com.lljin.scanner;

import com.lljin.proxy.RpcProxyFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @author lljin
 * @description 注册扫描器
 * @date 2020/6/20 14:39
 */
public class RpcBeanScanner extends ClassPathBeanDefinitionScanner {
    public RpcBeanScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            beanDefinition.setBeanClass(RpcProxyFactoryBean.class);
            beanDefinition.getPropertyValues().addPropertyValue("rpcClient",new RuntimeBeanReference("rpcClient"));
            beanDefinition.getPropertyValues().addPropertyValue("serialize",new RuntimeBeanReference("serialize"));
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            beanDefinition.setDependsOn("rpcClient");
        }
        return beanDefinitionHolders;
    }
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            return false;
        }
    }

    public void registerFilters(){
        this.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
    }
}
