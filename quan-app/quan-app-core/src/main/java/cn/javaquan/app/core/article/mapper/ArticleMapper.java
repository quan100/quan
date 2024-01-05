package cn.javaquan.app.core.article.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.javaquan.app.common.module.article.ArticleByCategoryDTO;
import cn.javaquan.app.common.module.article.OpenArticleQuery;
import cn.javaquan.app.core.article.entity.ArticlePO;
import org.apache.ibatis.annotations.Param;

/**
 * 文章
 *
 * @author wangquan
 * @version 1.0.0
 * @date 2023-01-03 21:31:47
 */
public interface ArticleMapper extends BaseMapper<ArticlePO> {

    /**
     * 后台管理用户中心查询用户优惠券
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<ArticleByCategoryDTO> byCategory(IPage<ArticleByCategoryDTO> page, @Param(Constants.WRAPPER) Wrapper<OpenArticleQuery> wrapper);

}
