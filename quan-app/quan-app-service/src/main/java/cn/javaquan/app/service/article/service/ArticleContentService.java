package cn.javaquan.app.service.article.service;

import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.service.article.feign.ArticleContentRepositoryFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章内容.
 *
 * @author javaquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class ArticleContentService {

    private final ArticleContentRepositoryFeign articleContentRepositoryFeign;

    /**
     * 根据ID查询.
     * @param articleId 文章id
     * @return 查询结果
     */
    public Result<ArticleContentDTO> details(String articleId) {
        return articleContentRepositoryFeign.details(articleId);
    }

    /**
     * 根据主键更新.
     * @param cmd 更新指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> update(ArticleContentUpdateCommand cmd) {
        return articleContentRepositoryFeign.update(cmd);
    }

    /**
     * 新增.
     * @param cmd 新增指令参数
     * @return 操作是否成功
     */
    public Result<Boolean> save(ArticleContentAddCommand cmd) {
        return articleContentRepositoryFeign.save(cmd);
    }

    /**
     * 删除.
     * @param articleIds 文章id
     * @return 操作是否成功
     */
    public Result<Boolean> deleteByIds(List<String> articleIds) {
        return articleContentRepositoryFeign.deleteByIds(articleIds);
    }

}
