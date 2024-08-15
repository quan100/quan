package cn.javaquan.app.core.article.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.entity.ArticleTagConfigPO;

import java.util.List;

/**
 * 文章标签配置.
 *
 * @author javaquan
 * @since 1.0.0
 */
public interface ArticleTagConfigRepository extends IService<ArticleTagConfigPO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ArticleTagConfigPO> page(ArticleTagConfigPO po, BasePage basePage);

    /**
     * 根据文章ID查询.
     * @param articleId 文章ID
     * @return 查询结果
     */
    List<ArticleTagConfigPO> queryByArticleId(String articleId);

    /**
     * 根据分类ID查询.
     * @param tagIdList 标签ID
     * @return 查询结果
     */
    List<ArticleTagConfigPO> queryByTagIdList(List<String> tagIdList);

}
