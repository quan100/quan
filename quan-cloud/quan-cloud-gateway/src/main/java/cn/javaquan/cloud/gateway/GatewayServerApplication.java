package cn.javaquan.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@ConfigurationPropertiesScan
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServerApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}
