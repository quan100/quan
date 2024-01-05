package cn.javaquan.app.service.article.feign;

import cn.javaquan.app.service.article.feign.fallback.ArticleContentRepositoryFallback;
import cn.javaquan.app.common.module.article.ArticleContentAddCommand;
import cn.javaquan.app.common.module.article.ArticleContentDTO;
import cn.javaquan.app.common.module.article.ArticleContentUpdateCommand;
import cn.javaquan.common.base.message.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章内容
 *
 * @author JavaQuan
 * @version 1.0.0
 * @date 2023-04-14 16:43:32
 */
@FeignClient(value = "${quan.app.feign.core.name}", url = "${quan.app.feign.core.url:}", fallbackFactory = ArticleContentRepositoryFallback.class)
public interface ArticleContentRepositoryFeign {

    /**
     * 根据ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("/core/article/content/details")
    Result<ArticleContentDTO> details(@RequestParam(value = "articleId") String articleId);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/core/article/content/update")
    Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/core/article/content/save")
    Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd);

    /**
     * 删除
     *
     * @param articleIds
     * @return
     */
    @DeleteMapping("/core/article/content/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<String> articleIds);

}
