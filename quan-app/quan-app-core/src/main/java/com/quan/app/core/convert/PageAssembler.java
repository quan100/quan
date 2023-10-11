package com.quan.app.core.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quan.common.base.message.BasePage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PageAssembler {

    PageAssembler INSTANCE = Mappers.getMapper(PageAssembler.class);

    @Mapping(target = "records", ignore = true)
    Page toPage(Page page);

    @Mapping(target = "current", source = "pageNum")
    @Mapping(target = "size", source = "pageSize")
    Page toPage(BasePage basePage);

}
