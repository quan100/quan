package cn.javaquan.tools.file.minio;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 文件上传服务自动配置.
 *
 * @author javaquan
 * @since 2.2.0
 */
@AutoConfiguration
@EnableConfigurationProperties(MinioProperties.class)
@Import({ MinioPropertiesConfiguration.class })
public class MinioAutoConfiguration {

    /**
     * Minio配置.
     */
    @Configuration(proxyBeanMethods = false)
    @Conditional(MinioCondition.class)
    @ConditionalOnClass({ MinioClient.class })
    protected static class MinioConfiguration {

        @Bean
        MinioUtil minioUtil(MinioClient minioClient, MinioProperties properties) {
            return new MinioUtil(minioClient, properties);
        }

    }

    static class MinioCondition extends AnyNestedCondition {

        MinioCondition() {
            super(ConfigurationPhase.PARSE_CONFIGURATION);
        }

        @ConditionalOnProperty(prefix = "quan.minio", name = "host")
        static class HostProperty {

        }

    }

}
