package cn.javaquan.app.common.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * QuanAppCommon 配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
@ComponentScan(basePackages = { "cn.javaquan.app.common.exception.handler", "cn.javaquan.app.common.consumer" })
@AutoConfiguration
public class QuanAppCommonAutoConfiguration {

}
