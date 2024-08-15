package cn.javaquan.tools.file.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.RemoveObjectArgs;
import io.minio.SnowballObject;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadSnowballObjectsArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
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
 * minio 文件服务工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public class MinioUtil {

    private static MinioClient minioClient;

    private static MinioProperties properties;

    /**
     * 构造函数.
     * <p>
     * 提供静态的实例注入
     * @param minioClient minio客户端
     * @param properties minio 配置信息
     */
    public MinioUtil(MinioClient minioClient, MinioProperties properties) {
        MinioUtil.minioClient = minioClient;
        MinioUtil.properties = properties;
    }

    /**
     * 文件上传.
     * @param file 文件数据对象
     * @return 上传成功返回的文件信息对象
     */
    public MinioResponse upload(MultipartFile file) {
        return upload(properties.determineDefaultBucketName(), file);
    }

    /**
     * 文件上传.
     * @param bucketName 存储桶名称
     * @param file 文件数据对象
     * @return 上传成功返回的文件信息对象
     */
    public MinioResponse upload(String bucketName, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        StringBuffer sb = new StringBuffer();
        sb.append(UUID.randomUUID());
        sb.append(originalFilename);

        String fileName = DigestUtils.md5Hex(sb.toString()) + fileSuffix;
        return MinioResponse.build(fileName, properties.determineDefaultUrl(),
                uploadBatch(bucketName, fileName, Arrays.asList(file)));
    }

    /**
     * 文件批量上传.
     * @param files 文件列表
     */
    public void uploadBatch(List<MultipartFile> files) {
        uploadBatch(properties.determineDefaultBucketName(), null, files);
    }

    /**
     * 文件上传.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     * @param files 文件列表
     * @return 响应信息
     */
    private ObjectWriteResponse uploadBatch(String bucketName, String fileName, List<MultipartFile> files) {
        Assert.notEmpty(files, "文件列表不可为空！");

        return exceptionHandler(() -> {
            makeBucket(bucketName);

            List<SnowballObject> objects = new ArrayList<>(files.size());
            for (MultipartFile file : files) {
                objects.add(new SnowballObject(StringUtils.hasText(fileName) ? fileName : file.getOriginalFilename(),
                        file.getInputStream(), file.getSize(), null));
            }

            return minioClient
                .uploadSnowballObjects(UploadSnowballObjectsArgs.builder().bucket(bucketName).objects(objects).build());
        });
    }

    /**
     * 文件下载.
     * @param fileName 文件名称
     * @param servletResponse http servlet响应信息
     */
    public void download(String fileName, HttpServletResponse servletResponse) {
        download(properties.determineDefaultBucketName(), fileName, servletResponse);
    }

    /**
     * 文件下载.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     * @param servletResponse http servlet响应信息
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
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        servletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        servletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        servletResponse.setStatus(HttpStatus.ACCEPTED.value());
        try (ServletOutputStream outputStream = servletResponse.getOutputStream()) {
            outputStream.write(content);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 文件下载.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     * @return 文件输入流
     */
    public InputStream download(String bucketName, String fileName) {
        return exceptionHandler(() -> {
            StatObjectResponse objectResponse = statObject(bucketName, fileName);
            if (null == objectResponse || objectResponse.size() <= 0) {
                return null;
            }
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        });
    }

    /**
     * 获取预览地址.
     * @param fileName 文件名称
     * @return 预览地址
     */
    public String preview(String fileName) {
        return preview(properties.determineDefaultBucketName(), fileName);
    }

    /**
     * 获取预览地址.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     * @return 预览地址
     */
    public String preview(String bucketName, String fileName) {
        return preview(bucketName, fileName, 20);
    }

    /**
     * 获取预览地址.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     * @param duration 持续时间，单位：秒
     * @return 预览地址
     */
    public String preview(String bucketName, String fileName, int duration) {
        return exceptionHandler(() -> minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
            .method(Method.GET)
            .bucket(bucketName)
            .object(fileName)
            .expiry(duration, TimeUnit.SECONDS)
            .build()));
    }

    /**
     * 删除文件.
     * @param fileName 文件名称
     */
    public void remove(String fileName) {
        remove(properties.determineDefaultBucketName(), fileName);
    }

    /**
     * 删除文件.
     * @param bucketName 存储桶名称
     * @param fileName 文件名称
     */
    public void remove(String bucketName, String fileName) {
        exceptionHandler(() -> {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(fileName).bypassGovernanceMode(true).build());
            return null;
        });
    }

    /**
     * 判断存储桶是否存在.
     * @param bucketName 存储桶名称
     * @return 存储桶是否存在
     * @throws ServerException 服务器异常
     * @throws InsufficientDataException 数据不足异常
     * @throws ErrorResponseException 错误响应异常
     * @throws IOException io异常
     * @throws NoSuchAlgorithmException 无此算法异常
     * @throws InvalidKeyException 无效密钥异常
     * @throws InvalidResponseException 无效响应异常
     * @throws XmlParserException xml 解析器异常
     * @throws InternalException 内部异常
     */
    public static boolean bucketExists(String bucketName) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建存储桶.
     * @param bucketName 存储桶名称
     * @throws ServerException 服务器异常
     * @throws InsufficientDataException 数据不足异常
     * @throws ErrorResponseException 错误响应异常
     * @throws IOException io异常
     * @throws NoSuchAlgorithmException 无此算法异常
     * @throws InvalidKeyException 无效密钥异常
     * @throws InvalidResponseException 无效响应异常
     * @throws XmlParserException xml 解析器异常
     * @throws InternalException 内部异常
     */
    public static void makeBucket(String bucketName) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        if (!bucketExists(bucketName)) {
            // Make a new bucket called 'asiatrip'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取对象的元数据.
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return 对象的元数据
     * @throws ServerException 服务器异常
     * @throws InsufficientDataException 数据不足异常
     * @throws ErrorResponseException 错误响应异常
     * @throws IOException io异常
     * @throws NoSuchAlgorithmException 无此算法异常
     * @throws InvalidKeyException 无效密钥异常
     * @throws InvalidResponseException 无效响应异常
     * @throws XmlParserException xml 解析器异常
     * @throws InternalException 内部异常
     */
    public static StatObjectResponse statObject(String bucketName, String objectName) throws ServerException,
            InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = bucketExists(bucketName);
        if (found) {
            StatObjectResponse response = minioClient
                .statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return response;
        }
        return null;
    }

    /**
     * 异常处理执行器.
     * <p>
     * 当执行器抛出异常时，将异常包装成运行时异常抛出.
     * @param consumer 执行函数
     * @param <V> 约定返回值类型
     * @return 执行结果
     */
    private static <V> V exceptionHandler(Consumer<V> consumer) {
        try {
            return consumer.accept();
        }
        catch (ServerException | InsufficientDataException | NoSuchAlgorithmException | InvalidKeyException
                | IOException | InvalidResponseException | XmlParserException | InternalException ex) {
            throw new RuntimeException(ex);
        }
        catch (ErrorResponseException ex) {
            throw new RuntimeException(ex.errorResponse().message());
        }
    }

    /**
     * 提供需捕获异常的函数式接口.
     *
     * @param <V> 返回值类型
     */
    @FunctionalInterface
    private interface Consumer<V> {

        V accept() throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
                NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
                InternalException;

    }

}
