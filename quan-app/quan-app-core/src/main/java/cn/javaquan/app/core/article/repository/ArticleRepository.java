package cn.javaquan.app.core.article.repository;

import cn.javaquan.app.common.module.article.ArticleAddCommand;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.ArticleDTO;
import cn.javaquan.app.common.module.article.ArticleUpdateCommand;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.javaquan.common.base.message.BasePage;
import cn.javaquan.common.base.message.PageResult;
import cn.javaquan.app.core.article.entity.ArticlePO;

import java.util.List;

/**
 * 文章.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface ArticleRepository extends IService<ArticlePO> {

    /**
     * 分页查询.
     * <p>
     * 当有排序参数 sort 时，优先根据sort 升序，然后根据创建时间降序
     * @param po 查询参数
     * @param basePage 分页参数
     * @return 查询结果
     */
    PageResult<ArticlePO> page(ArticlePO po, BasePage basePage);

    /**
     * 根据文章ID查询.
     * @param articleId 文章ID
     * @return 查询结果
     */
    ArticleByCategoryDTO getArticle(String articleId);

    /**
     * 热点分类文章查询.
     * @param articleIds 文章id
     * @return 查询结果
     */
    List<ArticlePO> hotCategoryArticle(List<String> articleIds);

    /**
     * 获取站点地图. 文章部分跳转页面URL.
     * @return 站点地图配置数据
     */
    List<ArticlePO> getSitemaps();

    /**
     * 根据分类查询文章.
     * @param query 查询参数
     * @return 查询参数
     */
    PageResult<ArticleByCategoryDTO> byCategory(OpenArticleQuery query);

    /**
     * 添加文章 同时添加关联信息.
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    ArticlePO saveArticle(ArticleAddCommand cmd);

    /**
     * 添加文章 同时添加关联信息.
     * @param cmd 更新指令参数
     * @return 更新参数
     */
    boolean updateArticle(ArticleUpdateCommand cmd);

    /**
     * 获取文章详情.
     * @param id 文章数据主键
     * @return 文章详情
     */
    ArticleDTO details(Long id);

}
