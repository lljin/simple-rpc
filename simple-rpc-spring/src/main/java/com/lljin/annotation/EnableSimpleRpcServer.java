package com.lljin.annotation;

import com.lljin.config.ServerConfig;
import com.lljin.processor.RpcBeanDefiniationRegistrar;
import com.lljin.processor.RpcServiceRegistry;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author lljin
 * @description TODO
 * @date 2020/6/20 14:27
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcServiceRegistry.class, ServerConfig.class})
public @interface EnableSimpleRpcServer {
}
