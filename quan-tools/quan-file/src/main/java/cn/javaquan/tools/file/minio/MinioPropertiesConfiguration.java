package cn.javaquan.tools.file.minio;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置.
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "quan.minio", name = "host")
class MinioPropertiesConfiguration {

    @Bean
    MinioClient minioClient(MinioProperties properties) {
        return properties.initializeMinioClient();
    }

}
