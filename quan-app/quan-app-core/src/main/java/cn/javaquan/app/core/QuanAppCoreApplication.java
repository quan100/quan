package cn.javaquan.app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基础设施应用.
 *
 * @author wangquan
 * @since 1.0.0
 */
@SpringBootApplication
public class QuanAppCoreApplication {

    /**
     * 使用默认设置从指定的源运行SpringApplication.
     * @param args 默认参数
     */
    public static void main(String[] args) {
        SpringApplication.run(QuanAppCoreApplication.class, args);
    }

}
