package cn.javaquan.app.core.article.repository;

import cn.javaquan.app.core.article.entity.ArticleContentPO;

import java.util.List;

/**
 * 文章内容.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface ArticleContentRepository {

    /**
     * 根据文章id更新.
     * @param po 文章内容数据
     * @return 更新操作是否成功
     */
    boolean updateByArticleId(ArticleContentPO po);

    /**
     * 根据文章id删除.
     * @param articleIds 文章id
     * @return 操作是否成功
     */
    boolean removeByByArticleIds(List<String> articleIds);

    /**
     * 根据文章id查询.
     * @param articleId 文章id
     * @return 查询结果
     */
    ArticleContentPO getOne(String articleId);

    /**
     * 保存文章内容.
     * @param po 文章内容数据
     * @return 保存操作是否成功
     */
    boolean save(ArticleContentPO po);

}
