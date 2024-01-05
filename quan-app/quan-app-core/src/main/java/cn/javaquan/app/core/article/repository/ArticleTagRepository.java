package cn.javaquan.app.core.article.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.article.entity.ArticleTagPO;

import java.util.List;

/**
 * 文章标签
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:39
 */
public interface ArticleTagRepository extends IService<ArticleTagPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArticleTagPO> page(ArticleTagPO po, BasePage basePage);

    /**
     * 文章标签更新
     *
     * @param articleId 文章ID
     * @param tagIdList 标签ID列表
     * @return
     */
    boolean articleTagUpdate(String articleId, List<String> tagIdList);
}

