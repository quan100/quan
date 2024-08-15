package cn.javaquan.app.pm.bff.file.controller;

import cn.javaquan.app.pm.bff.file.convert.FileAssembler;
import cn.javaquan.app.common.module.file.FileVO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.file.minio.MinioResponse;
import cn.javaquan.tools.file.minio.MinioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件管理接口.
 *
 * @author javaquan
 * @since 2.2.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final MinioUtil minioUtil;

    /**
     * 文件上传.
     * @param file 文件数据对象
     * @return 上传成功返回的文件信息对象
     */
    @PostMapping("/upload")
    public Result<FileVO> upload(@RequestPart MultipartFile file) {
        MinioResponse response = minioUtil.upload(file);
        return Result.success(FileAssembler.INSTANCE.toFileVO(response));
    }

    /**
     * 文件上传 自定义目录上传.
     * @param bucketName 存储桶名称
     * @param file 文件数据对象
     * @return 上传成功返回的文件信息对象
     */
    @PostMapping("/custom/upload")
    public Result<FileVO> customUpload(@RequestParam String bucketName, @RequestPart MultipartFile file) {
        MinioResponse response = minioUtil.upload(bucketName, file);
        return Result.success(FileAssembler.INSTANCE.toFileVO(response));
    }

    /**
     * 文件下载.
     * @param fileName 文件名称
     * @param servletResponse http servlet响应信息
     */
    @GetMapping("/download")
    public void download(@RequestParam String fileName, HttpServletResponse servletResponse) {
        minioUtil.download(fileName, servletResponse);
    }

    /**
     * 文件删除.
     * @param fileName 文件名称
     * @return 操作是否成功
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String fileName) {
        minioUtil.remove(fileName);
        return Result.success();
    }

    /**
     * 文件预览.
     * <p>
     * 有效期内可见，默认：20秒
     * @param fileName 文件名称
     * @return 预览文件的链接
     */
    @GetMapping("/preview")
    public String preview(@RequestParam String fileName) {
        return minioUtil.preview(fileName);
    }

}
