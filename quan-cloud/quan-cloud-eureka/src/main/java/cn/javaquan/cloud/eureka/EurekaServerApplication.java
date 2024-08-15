/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the MIT License;
 * you may not use this file except in compliance with the License.
 */

package cn.javaquan.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka 应用服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
