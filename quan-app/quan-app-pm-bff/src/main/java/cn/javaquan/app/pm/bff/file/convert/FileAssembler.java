package cn.javaquan.app.pm.bff.file.convert;

import cn.javaquan.app.common.module.file.FileVO;
import cn.javaquan.tools.file.minio.MinioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.File;

/**
 * @author wangquan
 */
@Mapper
public interface FileAssembler {

    FileAssembler INSTANCE = Mappers.getMapper(FileAssembler.class);

    @Mapping(target = "url", expression = "java(toUrl(response))")
    FileVO toFileVO(MinioResponse response);

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
