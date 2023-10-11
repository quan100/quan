package com.quan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangquan
 */
@ComponentScan("com.quan")
@SpringBootApplication
public class QuanSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanSecurityApplication.class, args);
    }
}
