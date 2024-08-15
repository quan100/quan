package cn.javaquan.chat.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 聊天应用服务.
 *
 * @author javaquan
 * @since 1.0.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class QuanChatBffApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(QuanChatBffApplication.class, args);
    }

}
