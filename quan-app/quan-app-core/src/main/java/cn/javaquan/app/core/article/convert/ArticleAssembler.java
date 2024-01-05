package cn.javaquan.app.core.article.convert;

import cn.javaquan.app.common.module.article.*;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.core.article.entity.ArticleContentPO;
import cn.javaquan.app.core.article.entity.ArticlePO;
import cn.javaquan.tools.id.ID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章参数转换
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@Mapper(imports = {ID.class, LocalDateUtils.class})
public interface ArticleAssembler {

    ArticleAssembler INSTANCE = Mappers.getMapper(ArticleAssembler.class);

    /**
     * 转换为更新参数
     * <p>
     * 更新自动配置更新时间。
     * 更新时不处理删除状态，删除状态交由删除功能处理。
     *
     * @param event
     * @return
     */
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "delFlag", ignore = true)
    ArticleContentPO toUpdatePO(ArticleContentEvent event);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param event
     * @return
     */
    @Mapping(target = "delFlag", constant = "false")
    ArticleContentPO toAddPO(ArticleContentEvent event);

    /**
     * 转换为查询参数
     *
     * @param query
     * @return
     */
    ArticlePO toQueryPO(ArticleQuery query);

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
    @Mapping(target = "updateUser", source = "operateUser")
    @Mapping(target = "delFlag", ignore = true)
    @Mapping(target = "articleId", ignore = true)
    ArticlePO toUpdatePO(ArticleUpdateCommand cmd);

    /**
     * 转换为新增参数
     * <p>
     * 新增时删除状态默认为正常。
     *
     * @param cmd
     * @return
     */
    @Mapping(target = "articleId", expression = "java(ID.genId())")
    @Mapping(target = "createTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "updateTime", expression = "java(LocalDateUtils.now())")
    @Mapping(target = "createUser", source = "operateUser")
    @Mapping(target = "updateUser", source = "operateUser")
    @Mapping(target = "delFlag", constant = "false")
    ArticlePO toAddPO(ArticleAddCommand cmd);

    /**
     * 转换为新增参数
     *
     * @param cmds
     * @return
     */
    List<ArticlePO> toAddPOS(List<ArticleAddCommand> cmds);

    ArticleDTO toArticleDTO(ArticlePO po);
}
