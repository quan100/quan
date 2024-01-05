package cn.javaquan.app.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Mapping(target = "pageNum", source = "current")
    @Mapping(target = "pageSize", source = "size")
    PageResult toPageResult(Page page);

    @Mapping(target = "records", ignore = true)
    PageResult toPageResult(PageResult page);
}
