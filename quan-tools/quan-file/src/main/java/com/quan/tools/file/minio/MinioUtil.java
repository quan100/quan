package com.quan.tools.file.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wangquan
 */
public class MinioUtil {

    private static MinioClient minioClient;
    private static MinioProperties properties;

    public MinioUtil(MinioClient minioClient, MinioProperties properties) {
        MinioUtil.minioClient = minioClient;
        MinioUtil.properties = properties;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public MinioResponse upload(MultipartFile file) {
        return upload(properties.determineDefaultBucketName(), file);
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param file
     * @return
     */
    public MinioResponse upload(String bucketName, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        StringBuffer sb = new StringBuffer();
        sb.append(UUID.randomUUID());
        sb.append(originalFilename);

        String fileName = DigestUtils.md5Hex(sb.toString()) + fileSuffix;
        return MinioResponse.build(fileName, properties.determineDefaultUrl(), uploadBatch(bucketName, fileName, Arrays.asList(file)));
    }

    /**
     * 文件批量上传
     *
     * @param files
     * @return
     */
    public void uploadBatch(List<MultipartFile> files) {
        uploadBatch(properties.determineDefaultBucketName(), null, files);
    }

    /**
     * 文件上传
     *
     * @param bucketName
     * @param fileName
     * @param files
     * @return
     */
    private ObjectWriteResponse uploadBatch(String bucketName, String fileName, List<MultipartFile> files) {
        Assert.notEmpty(files);

        return exceptionHandler(() -> {
            makeBucket(bucketName);

            List<SnowballObject> objects = new ArrayList<>(files.size());
            for (MultipartFile file : files) {
                objects.add(new SnowballObject(
                        StringUtils.hasText(fileName) ? fileName : file.getOriginalFilename(),
                        file.getInputStream(),
                        file.getSize(),
                        null
                ));
            }

            return minioClient.uploadSnowballObjects(
                    UploadSnowballObjectsArgs.builder().bucket(bucketName).objects(objects).build()
            );
        });
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     */
    public void download(String fileName, HttpServletResponse servletResponse) {
        download(properties.determineDefaultBucketName(), fileName, servletResponse);
    }

    /**
     * 文件下载
     *
     * @param bucketName
     * @param fileName   文件名称
     */
    public void download(String bucketName, String fileName, HttpServletResponse servletResponse) {
        InputStream inputStream = download(bucketName, fileName);
        byte[] content;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(512)) {
            byte[] b = new byte[512];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            inputStream.close();
            content = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        servletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        servletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        servletResponse.setStatus(HttpStatus.ACCEPTED.value());
        try (ServletOutputStream outputStream = servletResponse.getOutputStream()) {
            outputStream.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream download(String bucketName, String fileName) {
        return exceptionHandler(() -> {
            StatObjectResponse objectResponse = statObject(bucketName, fileName);
            if (null == objectResponse || objectResponse.size() <= 0) {
                return null;
            }
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        });
    }

    /**
     * 获取预览地址
     *
     * @param fileName
     * @return
     */
    public String preview(String fileName) {
        return preview(properties.determineDefaultBucketName(), fileName);
    }

    /**
     * 获取预览地址
     *
     * @param bucketName
     * @param fileName
     * @return
     */
    public String preview(String bucketName, String fileName) {
        return preview(bucketName, fileName, 20);
    }

    /**
     * 获取预览地址
     *
     * @param bucketName
     * @param fileName
     * @param duration   持续时间，单位：秒
     * @return
     */
    public String preview(String bucketName, String fileName, int duration) {
        return exceptionHandler(() -> minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(fileName)
                        .expiry(duration, TimeUnit.SECONDS)
                        .build())
        );
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    public void remove(String fileName) {
        remove(properties.determineDefaultBucketName(), fileName);
    }

    /**
     * 删除文件
     *
     * @param bucketName
     * @param fileName
     */
    public void remove(String bucketName, String fileName) {
        exceptionHandler(() -> {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .bypassGovernanceMode(true)
                            .build()
            );
            return null;
        });
    }

    /**
     * 判断存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return
     * @throws Exception
     */
    public static boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     * @throws Exception
     */
    public static void makeBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!bucketExists(bucketName)) {
            // Make a new bucket called 'asiatrip'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取对象的元数据
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     * @throws Exception
     */
    public static StatObjectResponse statObject(String bucketName, String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = bucketExists(bucketName);
        if (found) {
            StatObjectResponse response = minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build()
            );
            return response;
        }
        return null;
    }

    private static <V> V exceptionHandler(Consumer<V> consumer) {
        try {
            return consumer.accept();
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e.errorResponse().message());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    private interface Consumer<V> {
        V accept() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    }

}
