package cn.javaquan.app.common.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * im聊天sdk配置
 *
 * @author javaquan
 * @since 1.0.0
 */
@ComponentScan(basePackages = {"cn.javaquan.app.common"})
@AutoConfiguration
public class QuanAppCommonAutoConfiguration {

//    @ComponentScan
//    @Configuration(proxyBeanMethods = false)
//    protected static class QuanAppCommonConfiguration {
//
//    }

}
