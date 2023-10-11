package com.quan.app.mobile.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangquan
 */
@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class QuanAppMobileBffApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanAppMobileBffApplication.class, args);
    }
}
