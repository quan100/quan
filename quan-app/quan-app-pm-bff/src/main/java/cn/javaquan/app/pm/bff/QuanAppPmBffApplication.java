package cn.javaquan.app.pm.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangquan
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class QuanAppPmBffApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanAppPmBffApplication.class, args);
    }
}
