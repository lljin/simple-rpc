package com.lljin.annotation;

import com.lljin.config.ClientConfig;
import com.lljin.config.ServerConfig;
import com.lljin.processor.RpcBeanDefiniationRegistrar;
import com.lljin.processor.RpcReferencePostProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author lljin
 * @description 开启远程调用自动注入
 * @date 2020/6/20 14:27
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ClientConfig.class,RpcReferencePostProcessor.class, RpcBeanDefiniationRegistrar.class})
public @interface EnableSimpleRpcClient {
    @AliasFor("basePackage")
    String value() default "";

    String basePackage()default "";
}
