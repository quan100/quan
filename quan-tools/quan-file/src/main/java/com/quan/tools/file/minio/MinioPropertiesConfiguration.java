package com.quan.tools.file.minio;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "quan.minio", name = "host")
class MinioPropertiesConfiguration {

    @Bean
    MinioClient minioClient(@Nullable MinioProperties properties) {
        return properties.initializeMinioClient();
    }

}
