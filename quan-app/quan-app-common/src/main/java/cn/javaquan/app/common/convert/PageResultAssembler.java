package cn.javaquan.app.common.convert;

import cn.javaquan.common.base.message.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wangquan
 */
@Mapper
public interface PageResultAssembler {

    PageResultAssembler INSTANCE = Mappers.getMapper(PageResultAssembler.class);

    @Mapping(target = "records", ignore = true)
    PageResult toPageResult(PageResult page);

}
