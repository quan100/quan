package cn.javaquan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangquan
 */
@ComponentScan("cn.javaquan")
@SpringBootApplication
public class QuanSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanSecurityApplication.class, args);
    }
}
