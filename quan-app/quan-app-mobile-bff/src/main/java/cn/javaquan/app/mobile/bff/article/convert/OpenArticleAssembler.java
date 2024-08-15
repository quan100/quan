package cn.javaquan.app.mobile.bff.article.convert;

import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleCategoryVO;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentVO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleQuery;
import cn.javaquan.app.common.module.article.ArticleTagDTO;
import cn.javaquan.app.common.module.article.ArticleTagVO;
import cn.javaquan.app.common.module.article.ArticleVO;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章开放接口参数转换器.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Mapper
public interface OpenArticleAssembler {

    /**
     * 返回给定映射器类型的实例.
     */
    OpenArticleAssembler INSTANCE = Mappers.getMapper(OpenArticleAssembler.class);

    /**
     * 转换为文章查询参数.
     * @param query 文章查询参数
     * @return 转换后的参数
     */
    @Mapping(target = "status", constant = "0")
    @Mapping(target = "delFlag", constant = "false")
    ArticleQuery toArticleQuery(OpenArticleQuery query);

    /**
     * 转换为文章VO.
     * @param dto 文章数据传输对象
     * @return vo
     */
    @Mapping(target = "authorAccounts",
            expression = "java(toAuthorAccounts(dto.getAuthorAccountsPublic(), dto.getAuthorAccounts()))")
    ArticleVO toArticleVo(ArticleDTO dto);

    /**
     * 转换为文章VO.
     * @param dto 文章数据传输对象
     * @return vo
     */
    @Mapping(target = "authorAccounts",
            expression = "java(toAuthorAccounts(dto.getAuthorAccountsPublic(), dto.getAuthorAccounts()))")
    ArticleVO toArticleVo(ArticleByCategoryDTO dto);

    /**
     * 转换作者账号.
     * <p>
     * 当作者账号公开时，才会返回作者账号信息
     * @param authorAccountsPublic 作者账号是否公开
     * @param authorAccounts 作者账号
     * @return 作者账号
     */
    @Named("toAuthorAccounts")
    default String toAuthorAccounts(Boolean authorAccountsPublic, String authorAccounts) {
        if (Validate.defaultValue(authorAccountsPublic)) {
            return authorAccounts;
        }
        return null;
    }

    /**
     * 转换为文章VO列表.
     * @param dtos 文章数据传输对象列表
     * @return vo
     */
    List<ArticleVO> toArticleVoList(List<ArticleDTO> dtos);

    /**
     * 转换为文章内容VO.
     * @param dto 文章内容数据传输对象
     * @return vo
     */
    ArticleContentVO toArticleContentVO(ArticleContentDTO dto);

    // region 文章分类

    /**
     * 转换为文章分类返回结果vo.
     * @param result 文章分类返回结果
     * @return vo
     */
    @Mapping(target = "data", expression = "java(toPageResult(result.getData()))")
    Result<PageResult<ArticleCategoryVO>> toResult(Result<PageResult<ArticleCategoryDTO>> result);

    /**
     * 转换为分页结果.
     * @param pageResult 分页结果
     * @return vo
     */
    @Mapping(target = "records", expression = "java(toArticleCategoryVOList(pageResult.getRecords()))")
    PageResult<ArticleCategoryVO> toPageResult(PageResult<ArticleCategoryDTO> pageResult);

    /**
     * 转换为文章分类vo.
     * @param dto 文章分类数据传输对象
     * @return vo
     */
    ArticleCategoryVO toArticleCategoryVO(ArticleCategoryDTO dto);

    /**
     * 转换为文章分类vo.
     * @param dtoList 文章分类数据传输对象列表
     * @return vo
     */
    List<ArticleCategoryVO> toArticleCategoryVOList(List<ArticleCategoryDTO> dtoList);
    // endregion ./文章分类

    // region 文章标签

    /**
     * 转换为文章标签返回结果vo.
     * @param result 文章标签返回结果
     * @return vo
     */
    @Mapping(target = "data", expression = "java(toTagPageResult(result.getData()))")
    Result<PageResult<ArticleTagVO>> toTagResult(Result<PageResult<ArticleTagDTO>> result);

    /**
     * 转换为分页结果.
     * @param pageResult 分页结果
     * @return 分页结果
     */
    @Mapping(target = "records", expression = "java(toArticleTagVOList(pageResult.getRecords()))")
    PageResult<ArticleTagVO> toTagPageResult(PageResult<ArticleTagDTO> pageResult);

    /**
     * 转换为文章标签vo.
     * @param dto 文章标签数据传输对象
     * @return vo
     */
    ArticleTagVO toArticleTagVO(ArticleTagDTO dto);

    /**
     * 转换为文章标签vo.
     * @param dtoList 文章标签数据传输对象列表
     * @return vo
     */
    List<ArticleTagVO> toArticleTagVOList(List<ArticleTagDTO> dtoList);
    // endregion ./文章标签

}
