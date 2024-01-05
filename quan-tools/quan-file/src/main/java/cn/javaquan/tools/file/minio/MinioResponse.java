package cn.javaquan.tools.file.minio;

import io.minio.ObjectWriteResponse;

public class MinioResponse {

    private String bucket;
    private String region;
    private String object;

    private String etag;
    private String versionId;

    private String host;
    private String name;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
