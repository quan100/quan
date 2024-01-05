package cn.javaquan.app.mobile.bff.article.convert;

import cn.javaquan.app.common.module.article.*;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.common.base.message.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OpenArticleAssembler {
    OpenArticleAssembler INSTANCE = Mappers.getMapper(OpenArticleAssembler.class);

    @Mapping(target = "status", constant = "0")
    @Mapping(target = "delFlag", constant = "false")
    ArticleQuery toArticleQuery(OpenArticleQuery query);

    @Mapping(target = "authorAccounts", expression = "java(toAuthorAccounts(dto))")
    ArticleVO toArticleVo(ArticleDTO dto);

    @Named("toAuthorAccounts")
    default String toAuthorAccounts(ArticleDTO dto) {
        if (Validate.defaultValue(dto.getAuthorAccountsPublic())) {
            return dto.getAuthorAccounts();
        }
        return null;
    }

    List<ArticleVO> toArticleVoList(List<ArticleDTO> dtos);

    ArticleContentVO toArticleContentVO(ArticleContentDTO dto);

    // region 文章分类
    @Mapping(target = "data", expression = "java(toPageResult(result.getData()))")
    Result<PageResult<ArticleCategoryVO>> toResult(Result<PageResult<ArticleCategoryDTO>> result);

    @Mapping(target = "records", expression = "java(toArticleCategoryVOList(pageResult.getRecords()))")
    PageResult<ArticleCategoryVO> toPageResult(PageResult<ArticleCategoryDTO> pageResult);

    ArticleCategoryVO toArticleCategoryVO(ArticleCategoryDTO dto);

    List<ArticleCategoryVO> toArticleCategoryVOList(List<ArticleCategoryDTO> dtoList);
    // endregion ./文章分类

    // region 文章标签
    @Mapping(target = "data", expression = "java(toTagPageResult(result.getData()))")
    Result<PageResult<ArticleTagVO>> toTagResult(Result<PageResult<ArticleTagDTO>> result);

    @Mapping(target = "records", expression = "java(toArticleTagVOList(pageResult.getRecords()))")
    PageResult<ArticleTagVO> toTagPageResult(PageResult<ArticleTagDTO> pageResult);

    ArticleTagVO toArticleTagVO(ArticleTagDTO dto);

    List<ArticleTagVO> toArticleTagVOList(List<ArticleTagDTO> dtoList);
    // endregion ./文章标签
}
