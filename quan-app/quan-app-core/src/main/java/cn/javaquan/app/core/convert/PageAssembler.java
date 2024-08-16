package cn.javaquan.app.core.convert;

import cn.javaquan.common.base.message.BasePage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 分页数据转换器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface PageAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    PageAssembler INSTANCE = Mappers.getMapper(PageAssembler.class);

    /**
     * 忽略records数据转换.
     * @param page 分页查询参数
     * @return 忽略records数据转换的对象
     */
    @Mapping(target = "records", ignore = true)
    Page toPage(Page page);

    /**
     * 分页查询参数转换.
     * @param basePage 分页查询参数
     * @return 分页查询参数
     */
    @Mapping(target = "current", source = "pageNum")
    @Mapping(target = "size", source = "pageSize")
    Page toPage(BasePage basePage);

}
