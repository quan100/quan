package cn.javaquan.tools.file.minio;

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

    private static final String DEFAULT_BUCKET_NAME = "default";

    /**
     * S3服务的URL.
     * <p>
     * 接受端点作为字符串、URL或okhttp3。HttpUrl对象，并可选择接受端口号和标志，以启用安全（TLS）连接。
     * <p>
     * 端点作为字符串可以格式化如下：
     * <p>
     * https://s3.amazonaws.com https://play.min.io https://play.min.io:9000 localhost
     * play.min.io
     * </p>
     */
    private String host;

    /**
     * 用于对外访问的文件地址.
     */
    private String url;

    /**
     * 访问密钥.
     * <p>
     * S3服务中帐户的访问密钥（又称用户ID）
     */
    private String accessKey;

    /**
     * 密钥.
     * <p>
     * S3服务中帐户的密钥（又称密码）
     */
    private String secretKey;

    /**
     * 默认存储桶名称.
     */
    private String defaultBucketName;

    /**
     * 获取S3服务URL.
     * @return s3服务url
     */
    public String getHost() {
        return this.host;
    }

    /**
     * 设置S3服务URL.
     * @param host s3服务url
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取访问密钥.
     * @return 访问密钥
     */
    public String getAccessKey() {
        return this.accessKey;
    }

    /**
     * 设置访问密钥.
     * @param accessKey 访问密钥
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * 获取密钥.
     * @return 密钥
     */
    public String getSecretKey() {
        return this.secretKey;
    }

    /**
     * 设置密钥.
     * @param secretKey 密钥
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取默认存储桶名称.
     * @return 默认存储桶名称
     */
    public String getDefaultBucketName() {
        return this.defaultBucketName;
    }

    /**
     * 设置默认存储桶名称.
     * @param defaultBucketName 默认存储桶名称
     */
    public void setDefaultBucketName(String defaultBucketName) {
        this.defaultBucketName = defaultBucketName;
    }

    /**
     * 确定默认存储桶名称.
     * @return 默认存储桶名称
     */
    public String determineDefaultBucketName() {
        if (StringUtils.hasText(this.defaultBucketName)) {
            return this.defaultBucketName;
        }
        return DEFAULT_BUCKET_NAME;
    }

    /**
     * 获取对外访问的文件地址.
     * @return 对外访问的文件地址
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置对外访问的文件地址.
     * @param url 对外访问的文件地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 确定对外访问的文件地址.
     * @return 对外访问的文件地址
     */
    public String determineDefaultUrl() {
        if (StringUtils.hasText(this.url)) {
            return this.url;
        }
        return this.host;
    }

    /**
     * 初始化 minio client 服务.
     * @return minio client服务
     */
    public MinioClient initializeMinioClient() {
        return MinioClient.builder().endpoint(this.host).credentials(this.accessKey, this.secretKey).build();
    }

}
