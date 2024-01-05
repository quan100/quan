package cn.javaquan.app.pm.bff.file.controller;

import cn.javaquan.app.pm.bff.file.convert.FileAssembler;
import cn.javaquan.app.common.module.file.FileVO;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.tools.file.minio.MinioResponse;
import cn.javaquan.tools.file.minio.MinioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 文件
 *
 * @author JavaQuan
 * @version 2.2.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final MinioUtil minioUtil;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public Result<FileVO> upload(@RequestPart MultipartFile file) {
        MinioResponse response = minioUtil.upload(file);
        return Result.success(FileAssembler.INSTANCE.toFileVO(response));
    }

    /**
     * 文件上传
     * 自定义目录上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/custom/upload")
    public Result<FileVO> customUpload(@RequestParam String bucketName, @RequestPart MultipartFile file) {
        MinioResponse response = minioUtil.upload(bucketName, file);
        return Result.success(FileAssembler.INSTANCE.toFileVO(response));
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @return
     */
    @GetMapping(value = "/download")
    public void download(@RequestParam String fileName, HttpServletResponse servletResponse) {
        minioUtil.download(fileName, servletResponse);
    }

    /**
     * 文件删除
     *
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String fileName) {
        minioUtil.remove(fileName);
        return Result.success();
    }

    /**
     * 文件预览
     * <p>
     * 有效期内可见，默认：20秒
     */
    @GetMapping(value = "/preview")
    public String preview(@RequestParam String fileName) {
        return minioUtil.preview(fileName);
    }

}
