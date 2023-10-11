package com.quan.tools.file.minio;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Configuration properties for minio support.
 *
 * @author javaquan
 * @since 2.2.0
 */
@ConfigurationProperties(prefix = "quan.minio")
public class MinioProperties {

    private final static String DEFAULT_BUCKET_NAME = "default";

    /**
     * S3服务的URL
     * <p>
     * 接受端点作为字符串、URL或okhttp3。HttpUrl对象，并可选择接受端口号和标志，以启用安全（TLS）连接。
     * <p>
     * 端点作为字符串可以格式化如下：
     * <p>
     * https://s3.amazonaws.com
     * https://play.min.io
     * https://play.min.io:9000
     * localhost
     * play.min.io
     * </p>
     */
    private String host;

    /**
     * 用于对外访问的文件地址
     */
    private String url;

    /**
     * 访问密钥
     * <p>
     * S3服务中帐户的访问密钥（又称用户ID）
     */
    private String accessKey;

    /**
     * 密钥
     * <p>
     * S3服务中帐户的密钥（又称密码）
     */
    private String secretKey;

    /**
     * 默认存储桶名称
     */
    private String defaultBucketName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDefaultBucketName() {
        return defaultBucketName;
    }

    public void setDefaultBucketName(String defaultBucketName) {
        this.defaultBucketName = defaultBucketName;
    }

    public String determineDefaultBucketName() {
        if (StringUtils.hasText(this.defaultBucketName)) {
            return this.defaultBucketName;
        }
        return DEFAULT_BUCKET_NAME;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String determineDefaultUrl() {
        if (StringUtils.hasText(this.url)) {
            return this.url;
        }
        return this.host;
    }

    public MinioClient initializeMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(host)
                .credentials(accessKey, secretKey)
                .build();
        return minioClient;
    }

}
