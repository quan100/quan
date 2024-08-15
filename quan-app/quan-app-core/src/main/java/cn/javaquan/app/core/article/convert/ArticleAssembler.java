package cn.javaquan.app.core.article.convert;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentEvent;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.article.entity.ArticleContentPO;
import cn.javaquan.app.core.article.entity.ArticlePO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章参数转换.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper(imports = { ID.class, LocalDateUtils.class })
public interface ArticleAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    ArticleAssembler INSTANCE = Mappers.getMapper(ArticleAssembler.class);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。
     * @param event 更新参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    ArticleContentPO toUpdatePO(ArticleContentEvent event);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param event 新增参数
     * @return 新增参数
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleContentPO toAddPO(ArticleContentEvent event);

    /**
     * 转换为查询参数.
     * @param query 查询参数
     * @return 查询参数
     */
    ArticlePO toQueryPO(ArticleQuery query);

    /**
     * 转换为更新参数.
     * <p>
     * 更新自动配置更新时间。 更新时不处理删除状态，删除状态交由删除功能处理。 更新时不处理文章ID，业务主键在新增时配置。
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateUser", source = "operateUser")
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "articleId", ignore = true)
    ArticlePO toUpdatePO(ArticleUpdateCommand cmd);

    /**
     * 转换为新增参数.
     * <p>
     * 新增时删除状态默认为正常。
     * @param cmd 新增指令参数
     * @return 新增参数
     */
    @Mapping(target = "articleId", expression = "java(ID.genId())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createUser", source = "operateUser")
    @Mapping(target = "updateUser", source = "operateUser")
    @Mapping(target = "delFlag", constant = "false")
    ArticlePO toAddPO(ArticleAddCommand cmd);

    /**
     * 转换为新增参数.
     * @param cmds 新增参数
     * @return 新增参数
     */
    List<ArticlePO> toAddPOS(List<ArticleAddCommand> cmds);

    /**
     * PO转换为DTO.
     * @param po po
     * @return dto
     */
    ArticleDTO toArticleDTO(ArticlePO po);

    /**
     * 转换为查询实例.
     * @param articleId 文章ID
     * @return 查询实例
     */
    OpenArticleQuery toOpenArticleQuery(String articleId);

}
