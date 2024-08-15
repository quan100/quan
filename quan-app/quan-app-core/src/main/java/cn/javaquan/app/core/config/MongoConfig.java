package cn.javaquan.app.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Mongo db 配置.
 *
 * @author wangquan
 * @since 1.0.0
 */
@EnableMongoRepositories("cn.javaquan.app.core.**.mapper*")
@Configuration
public class MongoConfig {

}
