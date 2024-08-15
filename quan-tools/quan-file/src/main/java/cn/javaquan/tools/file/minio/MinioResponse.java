package cn.javaquan.tools.file.minio;

import io.minio.ObjectWriteResponse;

/**
 * Minio 文件服务操作响应数据.
 *
 * @author javaquan
 * @since 1.0.0
 */
public class MinioResponse {

    /**
     * 桶名称.
     */
    private String bucket;

    /**
     * 区域.
     */
    private String region;

    /**
     * 对象名称.
     */
    private String object;

    /**
     * etag.
     */
    private String etag;

    /**
     * 版本.
     */
    private String versionId;

    /**
     * 域名.
     */
    private String host;

    /**
     * 文件名称.
     */
    private String name;

    /**
     * 获取桶名称.
     * @return 桶名称
     */
    public String getBucket() {
        return this.bucket;
    }

    /**
     * 设置桶名称.
     * @param bucket 桶名称
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * 获取区域.
     * @return 区域
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * 设置区域.
     * @param region 区域
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取对象名称.
     * @return 对象名称
     */
    public String getObject() {
        return this.object;
    }

    /**
     * 设置对象名称.
     * @param object 对象名称
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * 获取etag.
     * @return etag
     */
    public String getEtag() {
        return this.etag;
    }

    /**
     * 设置etag.
     * @param etag etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * 获取版本.
     * @return 版本
     */
    public String getVersionId() {
        return this.versionId;
    }

    /**
     * 设置版本.
     * @param versionId 版本
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * 获取域名.
     * @return 域名
     */
    public String getHost() {
        return this.host;
    }

    /**
     * 设置域名.
     * @param host 域名
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取文件名称.
     * @return 文件名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置文件名称.
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 构建响应数据.
     * @param name 文件名称
     * @param host 域名
     * @param response 响应数据
     * @return 响应数据
     */
    public static MinioResponse build(String name, String host, ObjectWriteResponse response) {
        MinioResponse minioResponse = new MinioResponse();
        minioResponse.setBucket(response.bucket());
        minioResponse.setRegion(response.region());
        minioResponse.setObject(response.object());
        minioResponse.setEtag(response.etag());
        minioResponse.setVersionId(response.versionId());
        minioResponse.setName(name);
        minioResponse.setHost(host);
        return minioResponse;
    }

}
