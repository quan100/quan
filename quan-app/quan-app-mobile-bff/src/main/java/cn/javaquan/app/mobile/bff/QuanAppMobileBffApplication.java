package cn.javaquan.app.mobile.bff;

import cn.javaquan.app.common.autoconfigure.EnableJavaQuanConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangquan
 */
@EnableJavaQuanConfigurer
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class QuanAppMobileBffApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuanAppMobileBffApplication.class, args);
    }
}
