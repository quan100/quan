package cn.javaquan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 权限应用服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@ComponentScan("cn.javaquan")
@SpringBootApplication
public class QuanSecurityApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(QuanSecurityApplication.class, args);
    }

}
