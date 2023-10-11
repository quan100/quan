package com.quan.app.core.article.convert;

import com.quan.app.common.module.article.ArticleCategoryAddCommand;
import com.quan.app.common.module.article.ArticleCategoryQuery;
import com.quan.app.common.module.article.ArticleCategoryUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.article.entity.ArticleCategoryPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章分类参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface ArticleCategoryAssembler {

    ArticleCategoryAssembler INSTANCE = Mappers.getMapper(ArticleCategoryAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ArticleCategoryPO toQueryPO(ArticleCategoryQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    ArticleCategoryPO toUpdatePO(ArticleCategoryUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "categoryId", expression = "java(ID.genId())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", constant = "false")
    ArticleCategoryPO toAddPO(ArticleCategoryAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ArticleCategoryPO> toAddPOS(List<ArticleCategoryAddCommand> cmds);
}
