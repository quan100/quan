package cn.javaquan.app.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javaquan.common.base.message.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 分页结果转换.
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
     * Mybatis分页转换为统一的分页结果.
     * @param page mybatis分页
     * @return 统一的分页查询返回结果
     */
    @Mapping(target = "pageNum", source = "current")
    @Mapping(target = "pageSize", source = "size")
    PageResult toPageResult(Page page);

    /**
     * 忽略records数据转换.
     * @param page 统一的分页查询返回结果
     * @return 忽略records数据转换的对象
     */
    @Mapping(target = "records", ignore = true)
    PageResult toPageResult(PageResult page);

}
