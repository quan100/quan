package cn.javaquan.app.common.convert;

import cn.javaquan.common.base.message.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 分页参数转换器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Mapper
public interface PageResultAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    PageResultAssembler INSTANCE = Mappers.getMapper(PageResultAssembler.class);

    /**
     * 分页参数转换，忽略分页数据.
     * @param page 待转换的分页参数
     * @param <T> 约定的返回值类型
     * @return 转换后的分页参数，忽略分页数据
     */
    @Mapping(target = "result", ignore = true)
    default <T> PageResult<T> toIgnoreDataPageResult(PageResult<?> page) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageSize(page.getPageSize());
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPages(page.getPages());
        pageResult.setTotal(pageResult.getTotal());
        return pageResult;
    }

}
