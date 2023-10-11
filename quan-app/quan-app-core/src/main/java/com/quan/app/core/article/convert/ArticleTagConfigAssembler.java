package com.quan.app.core.article.convert;

import com.quan.app.common.module.article.ArticleTagConfigAddCommand;
import com.quan.app.common.module.article.ArticleTagConfigQuery;
import com.quan.app.common.module.article.ArticleTagConfigUpdateCommand;
import com.quan.app.common.util.LocalDateUtils;
import com.quan.app.core.article.entity.ArticleTagConfigPO;
import com.quan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章标签配置参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface ArticleTagConfigAssembler {

    ArticleTagConfigAssembler INSTANCE = Mappers.getMapper(ArticleTagConfigAssembler.class);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ArticleTagConfigPO toQueryPO(ArticleTagConfigQuery query);

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
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "articleId", ignore = true)
    ArticleTagConfigPO toUpdatePO(ArticleTagConfigUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleTagConfigPO toAddPO(ArticleTagConfigAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ArticleTagConfigPO> toAddPOS(List<ArticleTagConfigAddCommand> cmds);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param articleId
     * @param tagId
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleTagConfigPO toAddPO(String articleId, String tagId);
}
