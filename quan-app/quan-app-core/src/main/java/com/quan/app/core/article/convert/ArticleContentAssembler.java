package com.quan.app.core.article.convert;

import com.quan.app.common.module.article.ArticleContentAddCommand;
import com.quan.app.common.module.article.ArticleContentQuery;
import com.quan.app.common.module.article.ArticleContentUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.article.entity.ArticleContentPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章内容参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface ArticleContentAssembler {

    ArticleContentAssembler INSTANCE = Mappers.getMapper(ArticleContentAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ArticleContentPO toQueryPO(ArticleContentQuery query);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     * 更新时不处理文章ID，业务主键在新增时配置。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    ArticleContentPO toUpdatePO(ArticleContentUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleContentPO toAddPO(ArticleContentAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ArticleContentPO> toAddPOS(List<ArticleContentAddCommand> cmds);
}
