package cn.javaquan.app.core.article.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.entity.ArticleTagConfigPO;

import java.util.List;

/**
 * 文章标签配置
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
public interface ArticleTagConfigRepository extends IService<ArticleTagConfigPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArticleTagConfigPO> page(ArticleTagConfigPO po, BasePage basePage);

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    List<ArticleTagConfigPO> queryByArticleId(String articleId);

    /**
     * 根据分类ID查询
     *
     * @param tagIdList
     * @return
     */
    List<ArticleTagConfigPO> queryByTagIdList(List<String> tagIdList);
}

