package cn.javaquan.app.mobile.bff;

import cn.javaquan.app.common.autoconfigure.EnableJavaQuanConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 移动端BFF服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EnableJavaQuanConfigurer
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class QuanAppMobileBffApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(QuanAppMobileBffApplication.class, args);
    }

}
