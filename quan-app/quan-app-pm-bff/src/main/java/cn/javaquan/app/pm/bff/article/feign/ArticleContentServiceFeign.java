package cn.javaquan.app.pm.bff.article.feign;

import cn.javaquan.app.pm.bff.article.feign.fallback.ArticleContentServiceFallback;
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
@FeignClient(value = "${quan.app.feign.service.name}", url = "${quan.app.feign.service.url:}", fallbackFactory = ArticleContentServiceFallback.class)
public interface ArticleContentServiceFeign {

    /**
     * 根据ID查询
     *
     * @param articleId
     * @return
     */
    @GetMapping("/service/article/content/details")
    Result<ArticleContentDTO> details(@RequestParam(value = "articleId") String articleId);

    /**
     * 根据主键更新
     *
     * @param cmd
     * @return
     */
    @PutMapping("/service/article/content/update")
    Result<Boolean> update(@RequestBody ArticleContentUpdateCommand cmd);

    /**
     * 新增
     *
     * @param cmd
     * @return
     */
    @PostMapping("/service/article/content/save")
    Result<Boolean> save(@RequestBody ArticleContentAddCommand cmd);

    /**
     * 删除
     *
     * @param articleIds
     * @return
     */
    @DeleteMapping("/service/article/content/deleteByIds")
    Result<Boolean> deleteByIds(@RequestBody List<String> articleIds);

}
