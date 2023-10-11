package com.quan.app.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * mybatis-plus配置
 *
 * @author wangquan
 * @date 2021/12/29 00:29
 */
@EnableMongoRepositories("com.quan.app.core.**.mapper*")
@Configuration
public class MongoConfig {

}
