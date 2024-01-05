package cn.javaquan.app.core.article.repository;

import cn.javaquan.common.base.message.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.app.core.article.entity.ArticleCategoryPO;

import java.util.List;

/**
 * 文章分类
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-04 10:38:40
 */
public interface ArticleCategoryRepository extends IService<ArticleCategoryPO> {

    /**
     * 分页查询
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     *
     * @param po
     * @param basePage
     * @return
     */
    PageResult<ArticleCategoryPO> page(ArticleCategoryPO po, BasePage basePage);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(List<Long> ids);

    boolean articleCategoryUpdate(String articleId, List<String> categoryIdList);
}

