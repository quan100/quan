package cn.javaquan.app.pm.bff.file.convert;

import cn.javaquan.app.common.module.file.FileVO;
import cn.javaquan.tools.file.minio.MinioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.File;

/**
 * 文件模块参数转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface FileAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    FileAssembler INSTANCE = Mappers.getMapper(FileAssembler.class);

    /**
     * 转换MinioResponse为FileVO.
     * @param response {@link MinioResponse}
     * @return {@link FileVO}
     */
    @Mapping(target = "url", expression = "java(toUrl(response))")
    FileVO toFileVO(MinioResponse response);

    /**
     * 文件url参数转换.
     * @param response {@link MinioResponse}
     * @return 文件链接
     */
    @Named("toUrl")
    default String toUrl(MinioResponse response) {
        StringBuffer sb = new StringBuffer();
        sb.append(response.getHost());
        sb.append(File.separator);
        sb.append(response.getBucket());
        sb.append(File.separator);
        sb.append(response.getName());
        return sb.toString();
    }

}
